package com.example.accountbook1.utils;

import com.example.accountbook1.model.stc.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ErrorResponseUtils {
    private static final Gson gson = new Gson();
    public static ErrorResponse parseError(String response){
        try{
            return gson.fromJson(response, ErrorResponse.class);
        }catch (JsonSyntaxException e){
            return null;
        }


    }

}
