package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.movilbox.lector.Referencias.Adapters.PersonajesAdapter;
import com.movilbox.lector.Referencias.Objetos.Personaje;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class Personajes extends AppCompatActivity {

    private ListView listPersonajes;
    private ArrayAdapter<Personaje> adapter;
    private SingletonLibros libros;

    DatabaseReference databasePersonajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes);

        libros = SingletonLibros.getInstance();
        databasePersonajes= FirebaseDatabase.getInstance().getReference("personajes").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());

        listPersonajes = (ListView) findViewById(R.id.listPersonaje);

        listPersonajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                libros.setPersonajeSeleccionado(position);
                Intent intent = new Intent(Personajes.this, EditorPersonaje.class);
                startActivity(intent);
            }
        });
    }
    public void EditorDeTexto (View view){

        Intent intent = new Intent(Personajes.this, EditorTexto.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(Personajes.this, Cronologia.class);
        startActivity(intent);

    }

    public void MostrarTextos (View view){

        Intent intent = new Intent(Personajes.this, Textos.class);
        startActivity(intent);

    }

    public void MostrarLugares (View view){

        Intent intent = new Intent(Personajes.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(Personajes.this, Personajes.class);
        startActivity(intent);

    }
    public void Notas (View view) {

        Intent intent = new Intent(Personajes.this, Notas.class);
        startActivity(intent);
    }

    public void MostrarPersonaje (View view){

        Intent intent = new Intent(Personajes.this, EditorPersonaje.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        databasePersonajes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaPersonajes().clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Personaje personaje = postSnapshot.getValue(Personaje.class);
                    libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaPersonajes().add(personaje);
                }
                adapter = new PersonajesAdapter(Personajes.this, libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaPersonajes());
                listPersonajes.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
