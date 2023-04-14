package com.example.accountbook1.model.response;

import com.google.gson.Gson;

public class ErrorResponse {
    int errorId;
    String errorMassage;

    public ErrorResponse(int errorId, String errorMassage) {
        this.errorId = errorId;
        this.errorMassage = errorMassage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
