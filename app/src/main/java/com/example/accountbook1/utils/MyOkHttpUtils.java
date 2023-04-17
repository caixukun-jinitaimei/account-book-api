package com.example.accountbook1.utils;

import com.example.accountbook1.view.RegisterActivity;
import com.google.gson.Gson;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyOkHttpUtils {
    private static final Gson gson = new Gson();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static final OkHttpClient client = new OkHttpClient();

    public static void POST(String url,Object bodyObject, Callback callback){

        RequestBody body = RequestBody.create(MyOkHttpUtils.JSON, gson.toJson(bodyObject));
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        MyOkHttpUtils.client.newCall(request).enqueue(callback);
    }
}
