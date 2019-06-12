package com.mobile.hajsownik.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.hajsownik.R;
import com.mobile.hajsownik.pojo.Signup;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {

    Button registerButton;
    TextView loginLink;
    EditText usernameBox,passwordBox,password2Box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton=findViewById(R.id.registerButton);
        loginLink=findViewById(R.id.loginLink);
        usernameBox=findViewById(R.id.usernameBox1);
        passwordBox=findViewById(R.id.passwordBox1);
        password2Box=findViewById(R.id.password2Box);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazwa=usernameBox.getText().toString().trim();
                String haslo=passwordBox.getText().toString().trim();
                String haslo2=password2Box.getText().toString().trim();
                System.out.println("Nazwa pobrana: "+nazwa);
                System.out.println("haslo pobrane: "+haslo);

                if(nazwa.length()<5){
                    Toast.makeText(getApplicationContext(), "Nazwa użytkownika musi zawierać przynajmniej 5 znaków", Toast.LENGTH_LONG).show();
                }
                else {
                    if(haslo.length()<8){
                        Toast.makeText(getApplicationContext(), "Hasło musi zawierać przynajmniej 8 znaków", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(haslo2.contentEquals(haslo)){
                            Call<Signup> call = RetrofitClient
                                    .getInstance()
                                    .getApi()
                                    .createUser(nazwa,haslo);
                            call.enqueue(new Callback<Signup>() {
                                @Override
                                public void onResponse(Call<Signup> call, Response<Signup> response) {
                                    System.out.println("test response");
                                    Signup odp= response.body();
                                    try {
                                        Toast.makeText(Register.this, odp.getMessage(), Toast.LENGTH_LONG).show();

                                    } catch(NullPointerException e)
                                    {
                                        System.out.println(e.getMessage());
                                    }

                                }

                                @Override
                                public void onFailure(Call<Signup> call, Throwable t) {
                                    t.printStackTrace();
                                    System.out.println("test error");
                                    call.cancel();
                                }
                            });
                            //Toast.makeText(getApplicationContext(), "Zarejestrowano?", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Hasła muszą się zgadzać", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
}
