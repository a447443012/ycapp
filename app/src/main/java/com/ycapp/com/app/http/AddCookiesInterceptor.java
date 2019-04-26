package com.ycapp.com.app.http;

import android.util.Log;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 18044075 on 2018/6/19.
 */
public class AddCookiesInterceptor implements Interceptor{
    public  int reTryCount = 2;
    public boolean isWait = true;
    public AddCookiesInterceptor() {
        super();
    }
    /**
     * 自动登录任务ID
     */
    private static final int TASK_AUTO_LOGIN = 6;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder().addHeader("Content-Type", "application/json; charset=UTF-8");
        long t1 = System.nanoTime();//请求发起的时间
        Request request = builder.build();
        //加密
        //使用@Field添加参数
        if (("POST".equals(request.method()) ||"PUT".equals(request.method()) )) {//post请求有参数时
            try {
                Map<String, String> map = new HashMap<>();

                //重新拼接url
                HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
                httpUrlBuilder.addQueryParameter("signature", "test11111111");
                Map baseParam = BaseRequest.getInstance().options;
                if(baseParam!=null){
                    Iterator it = baseParam.keySet().iterator();
                    //拼接参数
                    while (it.hasNext()) {
                        String key = it.next().toString();
                        String value = baseParam.get(key).toString();
                        httpUrlBuilder.addQueryParameter(key, value);
                    }
                }
                Request.Builder requestBuilder = request.newBuilder();
                requestBuilder.url(httpUrlBuilder.build());
                request = requestBuilder.build();
            }catch (Exception e){

            }
        }
        long t2 = System.nanoTime();//收到响应的时间
        Log.i("liu",String.format("发送请求 %s on %s%n%s",
                request.url(), request.toString(), request.headers()));
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String responseStr = String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers());

        Log.i("liu",responseStr);
        return response;
    }

}
