package com.doozycod.fashion;

// Model class or Pojo class to set and get the data

public class DatabaseModel {
    private String barcode, product_name, product_img, price_one, store_url_1, store_url_2, store_url_3, price_two, price_three;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPrice_one() {
        return price_one;
    }

    public void setPrice_one(String price_one) {
        this.price_one = price_one;
    }

    public String getStore_url_1() {
        return store_url_1;
    }

    public void setStore_url_1(String store_url_1) {
        this.store_url_1 = store_url_1;
    }

    public String getStore_url_2() {
        return store_url_2;
    }

    public void setStore_url_2(String store_url_2) {
        this.store_url_2 = store_url_2;
    }

    public String getStore_url_3() {
        return store_url_3;
    }

    public void setStore_url_3(String store_url_3) {
        this.store_url_3 = store_url_3;
    }

    public String getPrice_two() {
        return price_two;
    }

    public void setPrice_two(String price_two) {
        this.price_two = price_two;
    }

    public String getPrice_three() {
        return price_three;
    }

    public void setPrice_three(String price_three) {
        this.price_three = price_three;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }
}
