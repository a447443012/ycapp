package com.ycapp.com.app.http;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * 自动管理Cookies
 */
public final class CookiesManager implements CookieJar {
    List<Cookie> list = new ArrayList<>();
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null&&cookies.size()>0) {
            for (Cookie item : cookies) {
                if(!TextUtils.isEmpty(item.value()))
                    list.add(item);
            }

        }
    }

    @Override
    public List<Cookie> loadForRequest(final HttpUrl url) {
            return list;
    }
}
