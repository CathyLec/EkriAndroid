package com.movilbox.lector.Referencias.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.movilbox.lector.R;
import com.movilbox.lector.Referencias.Objetos.Libro;
import com.movilbox.lector.Referencias.Objetos.Suceso;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;

import org.w3c.dom.Text;

public class VistaSuceso extends AppCompatActivity {

    private TextView lblTitulo;
    private TextView lblFecha;
    private TextView lblTexto;
    private SingletonLibros libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_suceso);

        libros = SingletonLibros.getInstance();

        Suceso suceso = libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaCronogia().get(libros.getSucesoSeleccionado());

        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lblFecha = (TextView) findViewById(R.id.lblFecha);
        lblTexto = (TextView) findViewById(R.id.lblTexto);

        lblTitulo.setText(suceso.getTitulo());
        lblFecha.setText(suceso.getFecha());
        lblTexto.setText(suceso.getAcontecimiento());

    }
}
