package com.example.salonmanage.DTO;

public class AuthReponse {
    private String phone;
    private  String name;
    private String img;

    private String accessToken;

    public AuthReponse() { }

    public AuthReponse(String phone, String name, String img, String accessToken) {
        this.phone = phone;
        this.name = name;
        this.img = img;
        this.accessToken = accessToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
