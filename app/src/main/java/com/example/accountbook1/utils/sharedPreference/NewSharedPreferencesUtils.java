package com.example.accountbook1.utils.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.accountbook1.utils.GsonUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class NewSharedPreferencesUtils {
    private static final String GlobalField = "GlobalField";
    private static final Gson gson = GsonUtils.gson;
    public static <T> T get(Context context,String field,String name,Class<T> tClass){
        SharedPreferences sharedPreferences = context.getSharedPreferences(field, Context.MODE_PRIVATE);
        String jsonStr = sharedPreferences.getString(name, null);
        T t = gson.fromJson(jsonStr, tClass);
        System.out.println("get:"+name+","+jsonStr);

        return t;
    }
    public static <T> T get(Context context,String name,Class<T> tClass){
        return get(context, GlobalField,name, tClass);
    }

    public static <T> void set(Context context,String field,String name,T t){
        SharedPreferences sharedPreferences = context.getSharedPreferences(field, Context.MODE_PRIVATE);
        String jsonStr = gson.toJson(t);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(name,jsonStr);
        edit.apply();
        System.out.println("set:"+name+","+jsonStr);

    }
    public static <T> void set(Context context,String name,T t){
        set(context, GlobalField,name, t);
    }

    @Deprecated
    public static <T> void update(Context context,String field,String name,T t){
        SharedPreferences sharedPreferences = context.getSharedPreferences(field, Context.MODE_PRIVATE);
        String jsonStr = gson.toJson(t);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(name);
        edit.putString(name,jsonStr);
        edit.apply();
        System.out.println("update:"+name+","+jsonStr);

    }
    @Deprecated
    public static <T> void update(Context context,String name,T t){
        update(context, GlobalField, name, t);
    }
    public static void remove(Context context,String field,String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences(field, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(name);
        edit.apply();
        System.out.println("remove:"+name);

    }

    public static void remove(Context context,String name){
        remove(context,GlobalField,name);
    }
}
