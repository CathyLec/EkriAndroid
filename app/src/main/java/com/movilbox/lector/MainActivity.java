package com.movilbox.lector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnEditor, btnTextos, btnLugar, btnLugares, btnCronologia,btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEditor=(Button) findViewById(R.id.btnEditor);
    }
}
