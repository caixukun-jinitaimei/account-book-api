package com.example.accountbook1.model.server.json;

public class JsonRecordTemplate {
    private String name;
    private String data;

    public JsonRecordTemplate(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public JsonRecordTemplate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
