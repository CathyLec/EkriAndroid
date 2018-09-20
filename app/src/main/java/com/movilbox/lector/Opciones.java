package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class Opciones extends AppCompatActivity {

    SingletonLibros libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        libros = SingletonLibros.getInstance();
    }

    public void EditorDeTexto (View view){

        Intent intent = new Intent(Opciones.this, EditorTexto.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(Opciones.this, Cronologia.class);
        startActivity(intent);

    }

    public void MostrarTextos (View view){

        Intent intent = new Intent(Opciones.this, Textos.class);
        startActivity(intent);

    }

    public void MostrarLugares (View view){

        Intent intent = new Intent(Opciones.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(Opciones.this, Personajes.class);
        startActivity(intent);

    }
    public void Notas (View view) {

        Intent intent = new Intent(Opciones.this, Notas.class);
        startActivity(intent);
    }

}
