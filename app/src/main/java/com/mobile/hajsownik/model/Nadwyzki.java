package com.mobile.hajsownik.model;

public class Nadwyzki {
    float suma;
    String token="";
    String type="";

    public Nadwyzki(){}

    public Nadwyzki(String token,String type){
        this.token=token;
        this.type=type;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public float getSuma() {
        return suma;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
