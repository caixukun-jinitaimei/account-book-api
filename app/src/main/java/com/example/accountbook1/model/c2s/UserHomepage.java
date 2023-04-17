package com.example.accountbook1.model.c2s;

public class UserHomepage {
    private String jobStr;
    private String incomeStr;
    private String targetManagementStr;
    private String ageStr;
    private String sex;
    public UserHomepage(String jobStr,String incomeStr,String targetManagementStr,String ageStr,String sex){
        this.jobStr =jobStr;
        this.incomeStr = incomeStr;
        this.targetManagementStr = targetManagementStr;
        this.ageStr = ageStr;
        this.sex = sex;
    }
}
