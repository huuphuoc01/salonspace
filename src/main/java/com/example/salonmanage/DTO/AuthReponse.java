package com.example.salonmanage.DTO;

public class AuthReponse {
    private String phone;
    private String accessToken;

    public AuthReponse() { }

    public AuthReponse(String phone, String accessToken) {
        this.phone = phone;
        this.accessToken = accessToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
