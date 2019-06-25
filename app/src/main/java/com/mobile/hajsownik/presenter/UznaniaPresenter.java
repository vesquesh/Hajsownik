package com.mobile.hajsownik.presenter;

import com.mobile.hajsownik.api.RetrofitClient;
import com.mobile.hajsownik.model.Nadwyzki;
import com.mobile.hajsownik.pojo.Lista;
import com.mobile.hajsownik.views.UznaniaView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UznaniaPresenter {
    Nadwyzki nadwyzki;
    UznaniaView uznaniaView;

    public UznaniaPresenter(UznaniaView view){
        this.nadwyzki=new Nadwyzki();
        this.uznaniaView=view;
    }

    public void Uznania(String token,String type){
        nadwyzki.setToken(token);
        nadwyzki.setType(type);
        Call<Float> call= RetrofitClient
                .getInstance()
                .getApi()
                .sumaUzn(nadwyzki.getType()+" "+nadwyzki.getToken());
        call.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                Float odp = response.body();
                nadwyzki.setSuma(odp);
                uznaniaView.wypiszSume(odp.toString()+" zł");
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                t.printStackTrace();
                System.out.println("test error");
                call.cancel();
            }
        });
    }

    public void UznaniaLista(){
        Call<List<Lista>> call= RetrofitClient
                .getInstance()
                .getApi()
                .kredytLista(nadwyzki.getType()+" "+nadwyzki.getToken());
        call.enqueue(new Callback<List<Lista>>(){
            @Override
            public void onResponse(Call<List<Lista>> call, Response<List<Lista>> response) {
                List<Lista> odp = response.body();
                ArrayList<String> uznani = new ArrayList<>();
                for (Lista l: odp) {
                    uznani.add(l.pozycja());
                }
                uznaniaView.dodajUznania(uznani);
            }

            @Override
            public void onFailure(Call<List<Lista>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("test error");
                call.cancel();
            }
        });
    }
}
