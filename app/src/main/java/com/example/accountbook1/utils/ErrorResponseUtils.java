package com.example.accountbook1.utils;

import com.example.accountbook1.model.s2c.ErrorMessage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ErrorResponseUtils {
    private static final Gson gson = new Gson();
    public static ErrorMessage parseError(String response){
        try{
            return gson.fromJson(response, ErrorMessage.class);
        }catch (JsonSyntaxException e){
            return null;
        }


    }

}
