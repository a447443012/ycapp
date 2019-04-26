package com.ycapp.com.app.http;


import android.support.annotation.NonNull;
import android.util.Log;

import com.ycapp.com.app.MyApplication;
import com.ycapp.com.app.R;
import com.ycapp.com.app.bean.BaseBean;
import com.ycapp.com.app.bean.BetRecord;
import com.ycapp.com.app.bean.LoginResult;
import com.ycapp.com.app.bean.LotteryBean;
import com.ycapp.com.app.http.requestparam.BetParam;
import com.ycapp.com.app.http.requestparam.LotteryParm;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 18044075 on 2018/5/29.
 */
public class HttpHelper {
    private static final int TIME_OUT = 6;
    private Retrofit retrofit;
    private HttpService httpService;
    OkHttpClient client;
    private static volatile HttpHelper instance = null;

    private HttpHelper() {
        OkHttpClient.Builder builder = setVerifyBook();

        client =builder
//                .addInterceptor(new AddCookiesInterceptor())//统一处理get形式的请求数据
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(HttpsTrustManager.createSSLSocketFactory())
                .hostnameVerifier(new HttpsTrustManager.TrustAllHostnameVerifier())
                .cookieJar(new CookiesManager())
                .build();

//        https://m.yccp005.com/tools/_ajax/cache/lotterySetting
//        https://m.yccp005.com/tools/_ajax/login
//        https://m.yccp009.com/tools/_ajax/login
//        https://m.4444yccp.com
        retrofit = new Retrofit.Builder()
                .baseUrl("https://m.4444yccp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpService = retrofit.create(HttpService.class);


    }

    @NonNull
    private OkHttpClient.Builder setVerifyBook() {
        /**
         * 构造函数私有化
         * 并在构造函数中进行retrofit的初始化
         */
        OkHttpClient.Builder builder =new OkHttpClient.Builder();

        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String certificateAlias = Integer.toString(0);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.
                    generateCertificate(MyApplication.getInstance().getApplicationContext().getResources().openRawResource(R.raw.test)));
            SSLContext sslContext = SSLContext.getInstance("TLS");
            final TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (Exception ex) {

        }
        return builder;
    }

    public  static  HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null)
                    instance = new HttpHelper();
            }
        }
        return instance;
    }

    public void login(Observer<LoginResult> observer, String account, String password) {
        RequestParams params =new RequestParams();
        params.isdefaultLogin =true;
        params.loginName = account;
        params.loginPwd =password;
        params.validateDate =System.currentTimeMillis()+"";
        params.validCode ="";


        httpService.login(params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void lotteryOpenCache(Observer<LotteryBean> observer) {
        LotteryParm params =new LotteryParm();
        List<String> list = new ArrayList<>();
        list.add("JSK3");
        params.requirement =list;

        httpService.lotteryOpenCache(params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBetting(Observer<BetRecord> observer) {
        httpService.getBetting()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void submit(Observer<BaseBean> observer,String issue,String daxiao,String money) {
        BetParam params =new BetParam();
String item="{\"methodid\":\"K3002001001\",\"nums\":1,\"rebate\":\"0.00\",\"times\":"+money+",\"money\":"+money+",\"playId\":[\"K3002001010\"],\"mode\":1,\"issueNo\":"+issue+",\"codes\":\""+daxiao+"\"}";
        Log.e("liu",item);
        List<String> list = new ArrayList<>();
        list.add(item);
        params.setAccountId(MyApplication.accountId);
        params.setClientTime(System.currentTimeMillis());
        params.setGameId("JSK3");
        params.setIssue(issue);
        params.setItem(list);

        httpService.betSingle(params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }





}
