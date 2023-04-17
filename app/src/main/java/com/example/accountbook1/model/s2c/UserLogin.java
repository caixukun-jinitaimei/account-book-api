package com.example.accountbook1.model.s2c;

import com.example.accountbook1.model.client.User;
import com.google.gson.Gson;

public class UserLogin extends User {
    private String username;
    private String password;

    public UserLogin(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
