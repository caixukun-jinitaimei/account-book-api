package com.example.accountbook1.model.request;

public class UserRecord {
    private String date;
    private String time;
    private int type;
    private String label;
    private String GoodsName;
    private String GoodsShops;
    private String GoodsPrice;

    public UserRecord(String date,String time,int type,String label,String GoodsName,String GoodsShops,String GoodsPrice){
        this.date = date;
        this.time = time;
        this.type = type;
        this.label = label;
        this.GoodsName = GoodsName;
        this.GoodsShops = GoodsShops;
        this.GoodsPrice = GoodsPrice;
    }
}
