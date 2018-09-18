package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Lugar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);
    }
    public void Lugares(View view) {

        Intent intent = new Intent(Lugar.this, Lugares.class);
        startActivity(intent);
    }
}
