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
import com.mobile.hajsownik.presenter.LoginPresenter;
import com.mobile.hajsownik.views.LoginView;


public class Login extends AppCompatActivity implements LoginView {

    Button loginButton;
    TextView registerLink;
    EditText loginText,passwordText;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=findViewById(R.id.loginButton);
        registerLink=findViewById(R.id.registerLink);
        loginText=findViewById(R.id.usernameBox);
        passwordText=findViewById(R.id.passwordBox);

        presenter=new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(loginText.getText().toString().trim(),passwordText.getText().toString().trim());

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

    @Override
    public void registerMessage(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logowanie(String name,String token,String type) {
        Intent intent = new Intent(Login.this, Menu.class);
        intent.putExtra("login",name);
        intent.putExtra("token",token);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
