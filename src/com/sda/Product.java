package com.sda;

public enum Product {
    SNICKERS("Snickers",2.5F,"60g","#1"),
    COCA_COLA("Coca Cola",5F,"500 ml","#2"),
    WATER("Borsec",3F,"500 ml","#3"),
    PEANUTS("Mogyi",4L,"50 g","#4"),
    CHIPS("Lays",6L,"120 g","#5"),
    KINDER("Kinder Bueno",3.5F,"70 g","#6");

    private String name;
    private float price;
    private String quanity;
    private String code;

    Product(String name, float price, String quanity, String code) {
        this.name = name;
        this.price = price;
        this.quanity = quanity;
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getQuanity() {
        return quanity;
    }
}
