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
import com.ycapp.com.app.bean.LotteryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LotteryQuickAdapter extends RecyclerView.Adapter<LotteryQuickAdapter.MyViewHolder> {

    private Context mContext;
    List<LotteryBean.DataBean.BackDataBean.LotteryOpenBean> lotteryList =new ArrayList<>();

    public LotteryQuickAdapter(Context context, @Nullable List<LotteryBean.DataBean.BackDataBean.LotteryOpenBean> data) {
        mContext = context;
        lotteryList = data;
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick itemClick) {
        onItemClick = itemClick;
    }

    public void setLotteryList(List<LotteryBean.DataBean.BackDataBean.LotteryOpenBean> lotteryList) {
        this.lotteryList = lotteryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder viewHolder =
                new MyViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.lottery_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_no.setText(lotteryList.get(position).getIssueNo());
        holder.open_num.setText(lotteryList.get(position).getLotteryOpen());
        holder.total.setText(lotteryList.get(position).getCount()+"");
        if("da".equals(lotteryList.get(position).getDaxiao())){
            holder.big_or_small.setText("大");
        }else {
            holder.big_or_small.setText("小");
        }
        if("dan".equals(lotteryList.get(position).getDanshuang())){
            holder.single_or_double.setText("单");
        }else {
            holder.single_or_double.setText("双");
        }


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
