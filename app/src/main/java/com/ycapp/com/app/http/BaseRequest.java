package com.ycapp.com.app.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 18044075 on 2018/6/13.
 */
public class BaseRequest {
    private static BaseRequest instance;
    private BaseRequest(){

    }
    public static BaseRequest getInstance() {
        if(instance == null)
            instance = new BaseRequest();
        return instance;
    }

    public Map<String,String> options = new HashMap<>();
}
