package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void EditorDeTexto (View view){

        Intent intent = new Intent(MainActivity.this, Editor.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(MainActivity.this, Cronologia.class);
        startActivity(intent);

    }

    public void MostrarTextos (View view){

        Intent intent = new Intent(MainActivity.this, Textos.class);
        startActivity(intent);

    }

    public void MostrarLugares (View view){

        Intent intent = new Intent(MainActivity.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(MainActivity.this, Personajes.class);
        startActivity(intent);

    }
}
