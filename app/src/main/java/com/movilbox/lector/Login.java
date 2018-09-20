package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private TextView txtSingup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtSingup=(TextView) findViewById(R.id.link_login);
    }
    public void SingUp (View view){

        Intent intent = new Intent(Login.this, Singup.class);
        startActivity(intent);

    }
}
