package com.ycapp.com.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ycapp.com.app.R;
import com.ycapp.com.app.adapter.LotteryQuickAdapter;
import com.ycapp.com.app.adapter.MyBetQuickAdapter;
import com.ycapp.com.app.bean.BaseBean;
import com.ycapp.com.app.bean.BetRecord;
import com.ycapp.com.app.bean.LotteryBean;
import com.ycapp.com.app.http.HttpHelper;
import com.ycapp.com.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A login screen that offers login via email/password.
 */
public class LotteryOpenActivity extends AppCompatActivity {

    private List<LotteryBean.DataBean.BackDataBean.LotteryOpenBean> lotteryList = new ArrayList<>();
    private LotteryQuickAdapter lotteryQuickAdapter;
    private boolean startTask =false;
    private  List<BetRecord.DataBean.GetBettingListBean> myBets;
    //自动投大还是小
    String daxiao ="大";
    //刷新时间间隔
    int delayTime =30000;
    //从多少钱开始
    int startMoney = 1;
    EditText moneyEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);
        // Set up the login form.
        RecyclerView recyclerView = findViewById(R.id.goods_ry);
        moneyEdit = findViewById(R.id.money);
        final Button submit = findViewById(R.id.submit);
        final Button stop = findViewById(R.id.stop);
        final Button my_bet = findViewById(R.id.my_bet);
        lotteryQuickAdapter = new LotteryQuickAdapter(this, lotteryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lotteryQuickAdapter);
        getLotteryList();

        moneyEdit.setText(startMoney+"");


        final Handler handler=new Handler();
        final  Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                getLotteryList();
                handler.postDelayed(this, delayTime);
            }
        };

        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask =true;
                handler.removeCallbacks(runnable);
                handler.post(runnable);

            }
        });

        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask =false;
                handler.removeCallbacks(runnable);
                startMoney= 1;
                moneyEdit.setText(startMoney+"");

            }
        });

        my_bet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyBet(myBets);
            }
        });


    }

    private void getLotteryList() {
        HttpHelper.getInstance().lotteryOpenCache(new Observer<LotteryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LotteryBean lotteryBean) {
                if(lotteryBean!=null &&lotteryBean.getData().getBackData().getLotteryOpen()!=null){
                    lotteryList =lotteryBean.getData().getBackData().getLotteryOpen();
                    lotteryQuickAdapter.setLotteryList(lotteryList);
                    lotteryQuickAdapter.notifyDataSetChanged();
                    if(startTask){
                        getBetting();
                    }

                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void getBetting() {
        HttpHelper.getInstance().getBetting(new Observer<BetRecord>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BetRecord betRecord) {
                Log.e("Liu",betRecord.getData().getGetBettingList().get(0).getAwardResult());
                if(betRecord!=null && betRecord.getData().getGetBettingList()!=null){

                    if(betRecord.getData().getGetBettingList().size() ==0){
                        submit(daxiao,"1");
                    }else {
                        myBets =betRecord.getData().getGetBettingList();
                        BetRecord.DataBean.GetBettingListBean lastBet = betRecord.getData().getGetBettingList().get(0);
                        if("JSK3".equals(lastBet.getGameId())){
                            long nowIssue =Long.valueOf(lotteryList.get(0).getIssueNo())+1;
                            long betIssue =Long.valueOf(lastBet.getPeriodNo());
                            if(betIssue < nowIssue){
                                //上期投注已经有结果,计算下期怎么投注
                                Log.e("liu","上期投注已经有结果"+lastBet.getStatusStr()+",计算下期怎么投注");
                                if(lastBet.getStatusStr().equals("已派彩")){
                                    if(!TextUtils.isEmpty( moneyEdit.getText().toString())){
                                        startMoney = Integer.parseInt( moneyEdit.getText().toString());
                                    }
                                    submit(daxiao,startMoney+"");
                                }else  if(lastBet.getStatusStr().equals("未中奖")){
                                    String money = getNextMoney(lastBet);
                                    submit(daxiao,money);
                                }

                            }else {
                                //已经下注等待开奖'
                                if(lastBet.getStatusStr().equals("已撤单")){
                                    Log.e("liu","已撤单");
                                   ToastUtils.toastShort("上单已撤单，请手动去app下初始单");

                                }else {
                                    ToastUtils.toastShort("已经下注"+lastBet.getBetAmount()+"元，请等待开奖");
                                    Log.e("liu","已经下注"+lastBet.getBetAmount()+"元等待开奖");
                                }

                            }




                        }else {
                            submit(daxiao,"1");
                        }


                    }


                }


            }

            @NonNull
            private String getNextMoney(BetRecord.DataBean.GetBettingListBean lastBet) {
                String money;
                if(!TextUtils.isEmpty( moneyEdit.getText().toString())){
                    startMoney = Integer.parseInt( moneyEdit.getText().toString());
                }


                //投注的策略  已经投注大于3000从1块钱开始
                if(lastBet.getBetAmount()<=3000){
                    money =startMoney*lastBet.getBetAmount()*3+"";
                    if(lastBet.getBetAmount()*3>3300){
                        money =startMoney+"";
                    }
                }else {
                    money =startMoney+"";
                }
                return money;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void submit(String daxiao,String money) {
        String issue =Long.valueOf(lotteryList.get(0).getIssueNo())+1+"";

        HttpHelper.getInstance().submit(new Observer<BaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                Toast.makeText(LotteryOpenActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.toastShort(e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        },issue,daxiao,money);

    }



    private void showMyBet(final List<BetRecord.DataBean.GetBettingListBean> data) {
        getBetting();
        if(data ==null){
            return;
        }

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_add_electrical, null);
        TextView tvCancel = view.findViewById(R.id.btn_cancel);
        TextView tvTitle = view.findViewById(R.id.title);
        tvTitle.setText("投注记录");
        RecyclerView recyclerView = view.findViewById(R.id.choose_info);
        MyBetQuickAdapter  myBetQuickAdapter = new MyBetQuickAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myBetQuickAdapter);


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);

        dialog.show();


    }

}

