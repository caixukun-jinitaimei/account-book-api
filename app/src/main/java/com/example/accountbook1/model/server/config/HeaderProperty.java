package com.example.accountbook1.model.server.config;

public class HeaderProperty {
    String key;
    String value;

    public HeaderProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public HeaderProperty() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HeaderProperty{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
