package com.doozycod.fashion;

public class Model {
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    String img, url;
    int company;

    public Model(String image, int companylogo, String url) {
        this.img = image;
        this.company = companylogo;
        this.url = url;
    }
}
