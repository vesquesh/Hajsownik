package com.mobile.hajsownik.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.mobile.hajsownik.R;
import com.mobile.hajsownik.pojo.Auth;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button loginButton;
    TextView registerLink;
    EditText loginText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=findViewById(R.id.loginButton);
        registerLink=findViewById(R.id.registerLink);
        loginText=findViewById(R.id.usernameBox);
        passwordText=findViewById(R.id.passwordBox);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginText.getText().toString().trim();
                String haslo = passwordText.getText().toString().trim();

                if(login.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nazwa użytkownika wymagana", Toast.LENGTH_LONG).show();
                }
                else {
                    if(haslo.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Hasło jest wymagane", Toast.LENGTH_LONG).show();
                    }
                    else {
                        /*
                        Call<Auth> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .loginUser(login,haslo);
                        call.enqueue(new Callback<Auth>() {
                            @Override
                            public void onResponse(Call<Auth> call, Response<Auth> response) {
                                Toast.makeText(Login.this,response.body().toString(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, Menu.class);
                                Auth odp = response.body();
                                intent.putExtra("login",loginText.getText().toString().trim());
                                intent.putExtra("token",odp.getAccessToken().toString().trim());
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Pomyślnie zalogowano.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Auth> call, Throwable t) {
                                Toast.makeText(Login.this,t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                        */
                        Intent intent = new Intent(Login.this, Menu.class);
                        intent.putExtra("login",loginText.getText().toString().trim());
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Pomyślnie zalogowano.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
}
