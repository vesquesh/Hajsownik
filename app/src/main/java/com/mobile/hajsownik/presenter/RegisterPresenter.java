package com.mobile.hajsownik.presenter;

import com.mobile.hajsownik.api.RetrofitClient;
import com.mobile.hajsownik.model.User;
import com.mobile.hajsownik.pojo.DaneLog;
import com.mobile.hajsownik.pojo.Signup;
import com.mobile.hajsownik.views.RegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private User user;
    private RegisterView registerview;

    public RegisterPresenter(RegisterView view){
        this.user = new User();
        this.registerview = view;
    }

    public void register(String name,String pass,String pass2){
        user.setUsername(name);
        user.setPassword(pass);
        user.setPassword2(pass2);
        if(validate()){
            Call<Signup> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .createUser(new DaneLog(user.getUsername(),user.getPassword()));
            call.enqueue(new Callback<Signup>() {
                @Override
                public void onResponse(Call<Signup> call, Response<Signup> response) {
                    Signup odp= response.body();
                    if(response.isSuccessful())registerview.registerMessage(odp.getMessage());
                    else registerview.registerMessage("Nazwa jest już zajęta");
                }

                @Override
                public void onFailure(Call<Signup> call, Throwable t) {
                    t.printStackTrace();
                    System.out.println("test error");
                    call.cancel();
                }
            });
        }

    }

    public boolean validate(){
        if(user.getUsername().length()<5){
            registerview.registerMessage("Nazwa użytkownika musi zawierać przynajmniej 5 znaków");
            return false;
        }
        else {
            if(user.getPassword().length()<8){
                registerview.registerMessage("Hasło musi zawierać przynajmniej 8 znaków");
                return false;
            }
            else{
                if(user.getPassword2().contentEquals(user.getPassword())){
                    return true;
                }
                else {
                    registerview.registerMessage("Hasła muszą się zgadzać");
                    return false;
                }
            }
        }
    }
}
