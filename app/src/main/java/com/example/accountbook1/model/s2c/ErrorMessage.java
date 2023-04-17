package com.example.accountbook1.model.s2c;


public class ErrorMessage {
    int errorId;
    String errorMassage;

    public ErrorMessage(int errorId, String errorMassage) {
        this.errorId = errorId;
        this.errorMassage = errorMassage;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "errorId=" + errorId +
                ", errorMassage='" + errorMassage + '\'' +
                '}';
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }
}
