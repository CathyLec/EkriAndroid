package com.movilbox.lector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.movilbox.lector.Referencias.Objetos.Libro;
import com.movilbox.lector.Referencias.Objetos.Nota;
import com.movilbox.lector.Referencias.Objetos.Suceso;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class EditorSucesos extends AppCompatActivity {

    private EditText txtTitulo, txtFecha, txtTexto;
    private SingletonLibros libros;
    private DatabaseReference dbSucesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_cronologia);

        libros = SingletonLibros.getInstance();

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtTexto = (EditText) findViewById(R.id.txtTexto);
        txtFecha = (EditText) findViewById(R.id.txtFecha);

        dbSucesos = FirebaseDatabase.getInstance().getReference("sucesos").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());

    }

    public void guardarSuceso(View view){
        {

            String id = dbSucesos.push().getKey();
            Suceso suceso = new Suceso(txtFecha.getText().toString(),txtTexto.getText().toString(),txtTitulo.getText().toString() );
            suceso.setId(id);
            dbSucesos.child(id).setValue(suceso);

        }
    }
}
