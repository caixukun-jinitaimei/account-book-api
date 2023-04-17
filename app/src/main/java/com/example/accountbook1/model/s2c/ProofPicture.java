package com.example.accountbook1.model.s2c;

import com.google.gson.Gson;

public class ProofPicture {
    private String date;
    private String time;
    private int type;
    private String label;
    private String GoodsName;
    private String GoodsShops;
    private String GoodsPrice;

    public ProofPicture(String date, String time, int type, String label, String GoodsName, String GoodsShops, String GoodsPrice){
        this.date = date;
        this.time = time;
        this.type = type;
        this.label = label;
        this.GoodsName = GoodsName;
        this.GoodsShops = GoodsShops;
        this.GoodsPrice = GoodsPrice;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
