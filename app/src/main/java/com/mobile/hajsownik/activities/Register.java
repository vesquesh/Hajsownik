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
import com.mobile.hajsownik.presenter.RegisterPresenter;
import com.mobile.hajsownik.views.RegisterView;

public class Register extends AppCompatActivity implements RegisterView {

    private Button registerButton;
    private TextView loginLink;
    private EditText usernameBox,passwordBox,password2Box;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton=findViewById(R.id.registerButton);
        loginLink=findViewById(R.id.loginLink);
        usernameBox=findViewById(R.id.usernameBox1);
        passwordBox=findViewById(R.id.passwordBox1);
        password2Box=findViewById(R.id.password2Box);

        presenter = new RegisterPresenter(this);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.register(usernameBox.getText().toString().trim(),passwordBox.getText().toString().trim(),password2Box.getText().toString().trim());
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

    @Override
    public void registerMessage(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }
}
