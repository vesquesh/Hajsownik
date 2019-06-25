package com.mobile.hajsownik.presenter;

import com.mobile.hajsownik.api.RetrofitClient;
import com.mobile.hajsownik.model.User;
import com.mobile.hajsownik.pojo.Auth;
import com.mobile.hajsownik.pojo.DaneLog;
import com.mobile.hajsownik.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private User user;
    private LoginView loginview;

    public LoginPresenter(LoginView view){
        this.user = new User();
        this.loginview = view;
    }

    public void login(String name,String pass){
        user.setUsername(name);
        user.setPassword(pass);
        if(validate()){
            Call<Auth> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .loginUser(new DaneLog(user.getUsername(),user.getPassword()));
            call.enqueue(new Callback<Auth>() {
                @Override
                public void onResponse(Call<Auth> call, Response<Auth> response) {
                    Auth odp = response.body();
                    loginview.logowanie(user.getUsername(),odp.getAccessToken(),odp.getTokenType());
                    loginview.registerMessage("Pomyślnie zalogowano.");
                }

                @Override
                public void onFailure(Call<Auth> call, Throwable t) {
                    loginview.registerMessage(t.getMessage());
                }
            });
        }
    }


    public boolean validate(){
        if(user.getUsername().length()==0){
            loginview.registerMessage("Nazwa użytkownika wymagana");
            return false;
        }
        else {
            if(user.getPassword().length()==0){
                loginview.registerMessage("Hasło jest wymagane");
                return false;
            }
            else{
                    return true;
                }
            }
        }
}
