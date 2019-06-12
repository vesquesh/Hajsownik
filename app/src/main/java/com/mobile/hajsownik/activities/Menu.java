package com.mobile.hajsownik.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import com.mobile.hajsownik.R;

public class Menu extends AppCompatActivity {

    Button relogButton,listaButton,uznButton,zalButton;
    TextView witaj;
    String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        relogButton=findViewById(R.id.button);
        listaButton=findViewById(R.id.button2);
        uznButton=findViewById(R.id.button4);
        zalButton=findViewById(R.id.button3);
        witaj=findViewById(R.id.textView3);

        Intent intent=getIntent();
        String login = intent.getStringExtra("login");
        String login2="Witaj "+login;
        authToken=intent.getStringExtra("token");
        witaj.setText(login2);


        relogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Login.class));
                Toast.makeText(getApplicationContext(), "Wylogowano!", Toast.LENGTH_SHORT).show();
            }
        });

        listaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Work in progress", Toast.LENGTH_SHORT).show();
            }
        });

        uznButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Uznania.class);
                //intent.putExtra("token",authToken.trim());
                startActivity(intent);
            }
        });

        zalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Zadluzenie.class);
                //intent.putExtra("token",authToken.trim());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
}
