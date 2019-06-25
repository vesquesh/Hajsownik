package com.mobile.hajsownik.model;

public class User {
    private String username="",password="",password2="";

    public User(){
    }

    public User(String newUsername,String newPassword){
        this.username=newUsername;
        this.password=newPassword;
    }

    public User(String newUsername,String newPassword,String newPassword2){
        this.username=newUsername;
        this.password=newPassword;
        this.password2=newPassword2;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String newOne){
        this.username=newOne;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String newOne){
        this.password=newOne;
    }

    public String getPassword2(){
        return password2;
    }

    public void setPassword2(String newOne){
        this.password2=newOne;
    }
}
