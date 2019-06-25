package com.mobile.hajsownik.model;

public class Dlugi {
    float suma;
    String token="";
    String type="";

    public Dlugi(){}

    public Dlugi(String token,String type){
        this.token=token;
        this.type=type;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public float getSuma() {
        return suma;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
