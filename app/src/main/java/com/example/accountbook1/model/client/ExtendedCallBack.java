package com.example.accountbook1.model.client;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ExtendedCallBack implements Callback {
    String Tag;
    @Override
    public void onFailure(Call call, IOException e) {
        String message = "网络链接出错！";
        Log.d(Tag,message);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
}
