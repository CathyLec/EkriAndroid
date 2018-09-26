package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Singup extends AppCompatActivity {
    private TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        txtLogin=(TextView) findViewById(R.id.link_login);
    }
    public void Login (View view){

        Intent intent = new Intent(Singup.this, Singin.class);
        startActivity(intent);

    }
}
