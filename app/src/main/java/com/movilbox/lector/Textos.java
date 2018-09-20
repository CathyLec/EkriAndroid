package com.movilbox.lector;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.movilbox.lector.Referencias.Adapters.TextosAdapter;
import com.movilbox.lector.Referencias.FirebaseReferences;
import com.movilbox.lector.Referencias.Objetos.Libro;
import com.movilbox.lector.Referencias.Objetos.Nota;
import com.movilbox.lector.Referencias.Objetos.Personaje;
import com.movilbox.lector.Referencias.Objetos.Suceso;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

import java.util.ArrayList;
import java.util.List;

public class Textos extends AppCompatActivity {

    public static  final String LIBRO_ID = "com.movilbox.lector.Referencias.Objetos.Libro.id";
    public static  final String LIBRO_TITULO = "com.movilbox.lector.Referencias.Objetos.Libro.titulo";

    private SingletonLibros libros;
    private Context context;
    private TextosAdapter adapter;
    private ListView lv;
    private DatabaseReference dbLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textos);

        libros = SingletonLibros.getInstance();
        lv = (ListView) findViewById(R.id.listLugares);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        dbLibros = database.getReference("libros");



/*
        dbLibros.getRoot().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.eliminarLista();

                leerLibro(dataSnapshot);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                libros.setLibroSeleccionado(position);
                Intent intent = new Intent(Textos.this, EditorTexto.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        dbLibros.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.getListaLibros().clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Libro manuscrito=postSnapshot.getValue(Libro.class);
                    libros.getListaLibros().add(manuscrito);
                }
                adapter = new TextosAdapter(Textos.this, libros.getListaLibros());
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


/*
    private void leerLibro(DataSnapshot dataSnapshot) {

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Libro libro = snapshot.getValue(Libro.class);
            libros.agragarLibro(libro);
        }
    }
    */

    public void MostrarEditorDeTexto (View view){

        Intent intent = new Intent(Textos.this, EditorTexto.class);
        startActivity(intent);

    }
}
