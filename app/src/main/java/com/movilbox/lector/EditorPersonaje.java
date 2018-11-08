package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.movilbox.lector.Referencias.Objetos.Personaje;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class EditorPersonaje extends AppCompatActivity {

    private EditText txtNombre, txtEdad, txtDescripcion;
    private RadioGroup radioGroupGenero;
    private SingletonLibros libros;
    private String genero;
    private DatabaseReference databasePersonajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        libros = SingletonLibros.getInstance();

        databasePersonajes= FirebaseDatabase.getInstance().getReference("personajes").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());


        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        radioGroupGenero = (RadioGroup) findViewById(R.id.radioGroupGenero);

        radioGroupGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rbtnFemenino){
                    genero="femenino";
                }
                if (checkedId == R.id.rbtnMasculino){
                    genero="masculino";
                }
                if (checkedId == R.id.rbtnOtro){
                    genero="otro";
                }

            }
        });


    }

    public void GuardarPersonje(View view){

        String id = databasePersonajes.push().getKey();
        Personaje personaje = new Personaje(txtNombre.getText().toString(), genero, "", "", txtDescripcion.getText().toString(),Integer.parseInt(txtEdad.getText().toString()),0.0);
        personaje.setId(id);
        databasePersonajes.child(id).setValue(personaje);
    }

    public void Personajes (View view) {

        Intent intent = new Intent(EditorPersonaje.this, Personajes.class);
        startActivity(intent);
    }


}
