package com.coolweather.app.util;

/**
 * Created by codingBoy on 16/5/3.
 */
public interface HttpCallbackListener
{
    void onFinish(String response);
    void onError(Exception e);
}
