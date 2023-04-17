package com.example.accountbook1.model.s2c;

public class SuccessMessage {
    int successId;
    String successMassage;

    public int getSuccessId() {
        return successId;
    }

    public void setSuccessId(int successId) {
        this.successId = successId;
    }

    public String getSuccessMassage() {
        return successMassage;
    }

    public void setSuccessMassage(String successMassage) {
        this.successMassage = successMassage;
    }

    @Override
    public String toString() {
        return "SuccessMessage{" +
                "successId=" + successId +
                ", successMassage='" + successMassage + '\'' +
                '}';
    }

    public SuccessMessage(int successId, String successMassage) {
        this.successId = successId;
        this.successMassage = successMassage;
    }
}
