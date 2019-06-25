package com.mobile.hajsownik.presenter;

import com.mobile.hajsownik.api.RetrofitClient;
import com.mobile.hajsownik.model.Dlugi;
import com.mobile.hajsownik.pojo.Lista;
import com.mobile.hajsownik.views.ZadluzenieView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZadluzeniePresenter {
    Dlugi dlugi;
    ZadluzenieView zadluzenieView;

    public ZadluzeniePresenter(ZadluzenieView view){
        this.dlugi=new Dlugi();
        this.zadluzenieView=view;
    }

    public void Zadluzenie(String token, String type){
        dlugi.setToken(token);
        dlugi.setType(type);
        Call<Float> call= RetrofitClient
                .getInstance()
                .getApi()
                .sumaZad(dlugi.getType()+" "+dlugi.getToken());
        call.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                Float odp = response.body();
                dlugi.setSuma(odp);
                zadluzenieView.wypiszSume(odp.toString()+" zł");
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                t.printStackTrace();
                System.out.println("test error");
                call.cancel();
            }
        });
    }

    public void ZadluzenieLista(){
        Call<List<Lista>> call= RetrofitClient
                .getInstance()
                .getApi()
                .sumaLista(dlugi.getType()+" "+dlugi.getToken());
        call.enqueue(new Callback<List<Lista>>(){
            @Override
            public void onResponse(Call<List<Lista>> call, Response<List<Lista>> response) {
                List<Lista> odp = response.body();
                ArrayList<String> dluznicy = new ArrayList<>();
                for (Lista l: odp) {
                    dluznicy.add(l.pozycja());
                }
                zadluzenieView.dodajDluznikow(dluznicy);
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
