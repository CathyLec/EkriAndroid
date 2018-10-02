package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Singin extends AppCompatActivity {
    private TextView txtSingup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        txtSingup=(TextView) findViewById(R.id.link_Singup);
    }
    public void SingUp (View view){

        Intent intent = new Intent(Singin.this, Singup.class);
        startActivity(intent);

    }
    public void Textos (View view){

        Intent intent = new Intent(Singin.this, Textos.class);
        startActivity(intent);

    }
}
