package com.ycapp.com.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycapp.com.app.R;
import com.ycapp.com.app.bean.BetRecord;
import com.ycapp.com.app.bean.LotteryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyBetQuickAdapter extends RecyclerView.Adapter<MyBetQuickAdapter.MyViewHolder> {

    private Context mContext;
    List<BetRecord.DataBean.GetBettingListBean> lotteryList =new ArrayList<>();

    public MyBetQuickAdapter(Context context, @Nullable List<BetRecord.DataBean.GetBettingListBean> data) {
        mContext = context;
        lotteryList = data;
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick itemClick) {
        onItemClick = itemClick;
    }

    public void setLotteryList(List<BetRecord.DataBean.GetBettingListBean> lotteryList) {
        this.lotteryList = lotteryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder viewHolder =
                new MyViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.bet_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String periodNo =lotteryList.get(position).getPeriodNo();
        holder.tv_no.setText(periodNo.substring(4,periodNo.length()));
        holder.open_num.setText("投注 "+lotteryList.get(position).getBetContent()+lotteryList.get(position).getBetAmount()+"元");
        holder.total.setText("获奖 "+lotteryList.get(position).getBetAward()+"元");
        holder.big_or_small.setText(lotteryList.get(position).getStatusStr()+"");


    }

    @Override
    public int getItemCount() {
        return lotteryList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_no)
        TextView tv_no;
        @BindView(R.id.open_num)
        TextView open_num;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.big_or_small)
        TextView big_or_small;
        @BindView(R.id.single_or_double)
        TextView single_or_double;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void onClick(String s);

    }
}
