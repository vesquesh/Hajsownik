package com.mobile.hajsownik.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.hajsownik.R;
import com.mobile.hajsownik.presenter.UznaniaPresenter;
import com.mobile.hajsownik.views.UznaniaView;

import java.util.ArrayList;

public class Uznania extends AppCompatActivity implements UznaniaView {

    ListView listView;
    TextView suma;
    UznaniaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uznania);
        Intent intent=getIntent();
        suma=findViewById(R.id.textView10);
        listView=findViewById(R.id.listview2);
        presenter = new UznaniaPresenter(this);

        String token=intent.getStringExtra("token");
        String type =intent.getStringExtra("type");

        presenter.Uznania(token,type);
        presenter.UznaniaLista();
    }


    @Override
    public void wypiszSume(String wynik) {
        suma.setText(wynik);
    }

    @Override
    public void dodajUznania(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(arrayAdapter);
    }
}
