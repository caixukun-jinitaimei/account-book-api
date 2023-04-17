package com.example.accountbook1.model.s2c;

import com.google.gson.Gson;

public class UserRegister {
    private String username;
    private String password;
    private String sex;
    private String job;
    private String income;
    private String target_management;
    private String age;

    public UserRegister(String username, String password, String sex, String job, String income, String target_management, String age){
        this.username = username;
        this.password =password;
        this.sex = sex;
        this.job = job;
        this.income = income;
        this.target_management = target_management;
        this.age = age;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
