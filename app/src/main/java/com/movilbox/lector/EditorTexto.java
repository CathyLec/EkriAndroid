package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.movilbox.lector.Referencias.Objetos.Libro;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class EditorTexto extends AppCompatActivity {

    private EditText titulo, historia;
    private SingletonLibros libros;
    private DatabaseReference dbLibros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        libros = SingletonLibros.getInstance();

        titulo = (EditText) findViewById(R.id.txtTitulo);
        historia = (EditText) findViewById(R.id.txtTexto);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbLibros = database.getReference("libros");

        if(libros.getLibroSeleccionado() >= 0){
            titulo.setText(libros.getListaLibros().get(libros.getLibroSeleccionado()).getTitulo());
            historia.setText(libros.getListaLibros().get(libros.getLibroSeleccionado()).getHistoria());

        }
    }
    public void EditorDeTexto (View view){

        Intent intent = new Intent(EditorTexto.this, EditorTexto.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(EditorTexto.this, Cronologia.class);
        startActivity(intent);

    }


    public void MostrarLugares (View view){

        Intent intent = new Intent(EditorTexto.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(EditorTexto.this, Personajes.class);
        startActivity(intent);

    }
    public void Notas (View view) {

        Intent intent = new Intent(EditorTexto.this, Notas.class);
        startActivity(intent);
    }

    public void guardarLibro(View view){

        String id = dbLibros.push().getKey();
        Libro libro = new Libro(titulo.getText().toString(),historia.getText().toString());
        libro.setId(id);
        dbLibros.child(id).setValue(libro);
    }

}
