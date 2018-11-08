package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.movilbox.lector.Referencias.Objetos.Lugar;
import com.movilbox.lector.Referencias.Objetos.Nota;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class EditorLugar extends AppCompatActivity {

    private EditText txtNombre, txtUbicacion, txtDescripcion;
    private SingletonLibros libros;
    private DatabaseReference dbLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);

        libros = SingletonLibros.getInstance();

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtUbicacion = (EditText) findViewById(R.id.txtUbicacion);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

        dbLugares = FirebaseDatabase.getInstance().getReference("lugares").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());
    }
    public void Lugares(View view) {

        Intent intent = new Intent(EditorLugar.this, Lugares.class);
        startActivity(intent);
    }

    public void guardarLugar(View view){

        String id = dbLugares.push().getKey();
        Lugar lugar = new Lugar(txtNombre.getText().toString(), txtUbicacion.getText().toString(), txtDescripcion.getText().toString());
        lugar.setId(id);
        dbLugares.child(id).setValue(lugar);

        Toast.makeText(this, "Lugar: "+ txtNombre.getText().toString()+" agregado", Toast.LENGTH_SHORT).show();
    }
}
