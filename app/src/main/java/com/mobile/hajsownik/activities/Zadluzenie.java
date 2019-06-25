package com.mobile.hajsownik.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.hajsownik.R;
import com.mobile.hajsownik.presenter.ZadluzeniePresenter;
import com.mobile.hajsownik.views.ZadluzenieView;

import java.util.ArrayList;

public class Zadluzenie extends AppCompatActivity implements ZadluzenieView {

    ListView listView;
    TextView suma;
    ZadluzeniePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadluzenie);
        Intent intent=getIntent();
        suma=findViewById(R.id.textView10);
        listView=findViewById(R.id.listview1);
        presenter=new ZadluzeniePresenter(this);

        String token=intent.getStringExtra("token");
        String type =intent.getStringExtra("type");
        presenter.Zadluzenie(token,type);
        presenter.ZadluzenieLista();
    }

    @Override
    public void wypiszSume(String wynik) {
        suma.setText(wynik);
    }

    @Override
    public void dodajDluznikow(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(arrayAdapter);
    }
}
