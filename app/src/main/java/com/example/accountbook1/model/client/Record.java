package com.example.accountbook1.model.client;

public class Record {
    private String date;
    private String time;
    private int type;
    private String label;
    private String GoodsName;
    private String GoodsShops;
    private String GoodsPrice;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsShops() {return GoodsShops; }

    public void setGoodsShops(String goodsShops) {GoodsShops = goodsShops;}

    public String getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        GoodsPrice = goodsPrice;
    }
}

