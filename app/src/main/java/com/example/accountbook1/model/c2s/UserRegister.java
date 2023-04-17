package com.example.accountbook1.model.c2s;

public class UserRegister {
    private String username;
    private String password;
    private String sex;
    private String job;
    private String income;
    private String target_management;
    private String age;

    public UserRegister(String username, String password, String sex, String job, String income, String target_management, String age) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.job = job;
        this.income = income;
        this.target_management = target_management;
        this.age = age;
    }
}
