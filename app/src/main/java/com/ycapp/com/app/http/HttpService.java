package com.ycapp.com.app.http;

import com.ycapp.com.app.bean.BaseBean;
import com.ycapp.com.app.bean.BetRecord;
import com.ycapp.com.app.bean.LoginResult;
import com.ycapp.com.app.bean.LotteryBean;
import com.ycapp.com.app.http.requestparam.BetParam;
import com.ycapp.com.app.http.requestparam.LotteryParm;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by 18044075 on 2018/5/10.
 */
public interface HttpService {
    /*登录*/
    @POST("tools/_ajax/login")
    Observable<LoginResult> login(@Body RequestParams  parama);

    /*获取往期开奖记录*/
    @POST("tools/_ajax/cache/lotteryOpenCache")
    Observable<LotteryBean> lotteryOpenCache(@Body LotteryParm parama);

   /*投注*/
    @POST("tools/_ajax/JSK3/betSingle")
    Observable<BaseBean> betSingle(@Body BetParam parama);

    /*获取投注记录*/
    @POST("tools/_ajax/getBetting")
    Observable<BetRecord> getBetting();



}
