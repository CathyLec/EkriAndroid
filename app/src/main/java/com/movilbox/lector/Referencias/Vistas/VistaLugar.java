package com.movilbox.lector.Referencias.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.movilbox.lector.R;
import com.movilbox.lector.Referencias.Objetos.Lugar;
import com.movilbox.lector.Referencias.Objetos.Suceso;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

public class VistaLugar extends AppCompatActivity {

    private TextView lblNombre;
    private TextView lblUbicacion;
    private TextView lblDescripcion;
    private SingletonLibros libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_lugar);

        libros = SingletonLibros.getInstance();

        Lugar lugar = libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaLugares().get(libros.getLugarSeleccionado());

        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lblUbicacion = (TextView) findViewById(R.id.lblUbicacion);
        lblDescripcion = (TextView) findViewById(R.id.lblDescripcion);

        lblNombre.setText(lugar.getNombre());
        lblUbicacion.setText(lugar.getUbicacion());
        lblDescripcion.setText(lugar.getDescripcion());

    }
}
