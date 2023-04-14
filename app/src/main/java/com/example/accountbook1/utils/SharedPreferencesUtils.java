package com.example.accountbook1.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.accountbook1.model.client.User;
import com.example.accountbook1.model.client.Record;

public class SharedPreferencesUtils {

    /**
     * 保存用户名密码
     *
     * @param context
     * @param user
     * @return
     */
    public static boolean saveUserInfo(Context context, User user) {
        try {
            //1.通过Context对象创建一个SharedPreference对象
            //name:sharedpreference文件的名称    mode:文件的操作模式
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            //2.通过sharedPreferences对象获取一个Editor对象
            Editor editor = sharedPreferences.edit();
            //3.往Editor中添加数据
            editor.putInt("userId", user.getUserId());
            editor.putString("username", user.getUsername());
            editor.putString("password", user.getPassword());
            editor.putString("sex", user.getSex());
            editor.putString("job", user.getJob() + "");
            editor.putString("income", user.getIncome() + "");
            //4.提交Editor对象
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取用户名密码
     *
     * @param context
     * @return
     */
    public static Map<String, String> getUserInfo(Context context) {
        HashMap<String, String> hashMap;
        try {
            //1.通过Context对象创建一个SharedPreference对象
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            //2.通过sharedPreference获取存放的数据
            //key:存放数据时的key   defValue: 默认值,根据业务需求来写
            int userId = sharedPreferences.getInt("userId", 0);
            String password = sharedPreferences.getString("password", "");
            String username = sharedPreferences.getString("username", "");
            String sex = sharedPreferences.getString("sex", "男");
            String job = sharedPreferences.getString("job", "");
            String income = sharedPreferences.getString("income", "");

            hashMap = new HashMap<String, String>();
            hashMap.put("userId", userId + "");
            hashMap.put("password", password);
            hashMap.put("username", username);
            hashMap.put("sex", sex);
            hashMap.put("job", job);
            hashMap.put("weight", income);
            return hashMap;

        } catch (Exception e) {
            e.printStackTrace();
            hashMap = null;
        }
        return hashMap;
    }


    public static boolean saveRecordInfo(Context context, Record record) {
        try {
            //1.通过Context对象创建一个SharedPreference对象
            //name:sharedpreference文件的名称    mode:文件的操作模式
            SharedPreferences sharedPreferences = context.getSharedPreferences("recordInfo", Context.MODE_PRIVATE);
            //2.通过sharedPreferences对象获取一个Editor对象
            Editor editor = sharedPreferences.edit();
            //3.往Editor中添加数据
            editor.putInt("type", record.getType());
            editor.putString("date", record.getDate());
            editor.putString("time", record.getTime());
            editor.putString("label", record.getLabel());
            editor.putString("GoodsName", record.getGoodsName());
            editor.putString("GoodsShops", record.getGoodsShops());
            editor.putString("GoodsPrice",record.getGoodsPrice());
            //4.提交Editor对象
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取用户名密码
     *
     * @param context
     * @return
     */
    public static Map<String, String> getRecordInfo(Context context) {
        HashMap<String, String> hashMap;
        try {
            //1.通过Context对象创建一个SharedPreference对象
            SharedPreferences sharedPreferences = context.getSharedPreferences("recordInfo", Context.MODE_PRIVATE);
            //2.通过sharedPreference获取存放的数据
            //key:存放数据时的key   defValue: 默认值,根据业务需求来写
            int type = sharedPreferences.getInt("type", 0);
            String date = sharedPreferences.getString("date", "");
            String time = sharedPreferences.getString("time", "");
            String label = sharedPreferences.getString("label", "");
            String GoodsName = sharedPreferences.getString("GoodsName", "");
            String GoodsShops = sharedPreferences.getString("GoodsShops", "");
            String GoodsPrice = sharedPreferences.getString("GoodsPrice","");

            hashMap = new HashMap<String, String>();
            hashMap.put("type", type + "");
            hashMap.put("date", date);
            hashMap.put("time", time);
            hashMap.put("label", label);
            hashMap.put("GoodsName", GoodsName);
            hashMap.put("GoodsShops",GoodsShops);
            hashMap.put("GoodsPrice",GoodsPrice);
            return hashMap;

        } catch (Exception e) {
            e.printStackTrace();
            hashMap = null;
        }
        return hashMap;
    }

    /**
     * 存储服务器信息
     *
     * @param context
     * @param ip
     * @param port
     * @return
     */
    public static boolean saveIPConfig(Context context, String ip, String port) {
        try {
            SharedPreferences preferences = context.getSharedPreferences("serverConnect", Context.MODE_PRIVATE);
            Editor edit = preferences.edit();
            edit.putString("ip", ip);
            edit.putString("port", port);
            edit.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取服务器配置信息
     *
     * @param context
     * @return
     */
    public static Map<String, String> getIPConfig(Context context) {
        Map<String, String> map = new HashMap<String, String>();
        SharedPreferences preferences = context.getSharedPreferences("serverConnect", Context.MODE_PRIVATE);
        map.put("ip", preferences.getString("ip", ""));
        map.put("port", preferences.getString("port", ""));
        return map;
    }
}
