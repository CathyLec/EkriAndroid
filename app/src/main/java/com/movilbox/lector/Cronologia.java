package com.movilbox.lector;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.movilbox.lector.Referencias.Adapters.NotasAdapter;
import com.movilbox.lector.Referencias.Adapters.SucesosAdapter;
import com.movilbox.lector.Referencias.Objetos.Libro;
import com.movilbox.lector.Referencias.Objetos.Nota;
import com.movilbox.lector.Referencias.Objetos.Suceso;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;
import com.movilbox.lector.Referencias.Vistas.VistaSuceso;

public class Cronologia extends AppCompatActivity {

    private ListView listSucesos;
    private ArrayAdapter<Suceso>adapter;
    private SingletonLibros libros;
    private DatabaseReference dbSucesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronologia);

        libros = SingletonLibros.getInstance();

        dbSucesos = FirebaseDatabase.getInstance().getReference("sucesos").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());

        listSucesos=(ListView) findViewById(R.id.listSucesos);

        listSucesos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Suceso suceso=libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaCronogia().get(position);
                showUpdateDeleteDialog(suceso.getId(),suceso.getTitulo());
                return true;
            }
        });

        listSucesos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                libros.setSucesoSeleccionado(position);
                Intent intent = new Intent(Cronologia.this, VistaSuceso.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        dbSucesos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaCronogia().clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Suceso suceso=postSnapshot.getValue(Suceso.class);
                    libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaCronogia().add(suceso);
                }
                adapter = new SucesosAdapter(Cronologia.this, libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaCronogia());
                listSucesos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void EditorDeTexto (View view){

        Intent intent = new Intent(Cronologia.this, EditorTexto.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(Cronologia.this, Cronologia.class);
        startActivity(intent);

    }

    public void MostrarTextos (View view){

        Intent intent = new Intent(Cronologia.this, Textos.class);
        startActivity(intent);

    }

    public void MostrarLugares (View view){

        Intent intent = new Intent(Cronologia.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(Cronologia.this, Personajes.class);
        startActivity(intent);

    }
    public void Notas (View view) {

        Intent intent = new Intent(Cronologia.this, Notas.class);
        startActivity(intent);
    }
    private void showUpdateDeleteDialog(final String sucesoId, String sucesoTitulo){

        AlertDialog.Builder dialogBuilder = new  AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_suceso,null);
        dialogBuilder.setView(dialogView);

        final EditText txtTitulo = (EditText) dialogView.findViewById(R.id.txtTitulo);
        final EditText txtFecha = (EditText) dialogView.findViewById(R.id.txtFecha);
        final EditText txtTexto = (EditText) dialogView.findViewById(R.id.txtTexto);

        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle(sucesoTitulo);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();
                String fecha = txtFecha.getText().toString();
                String texto = txtTexto.getText().toString();

                if(!TextUtils.isEmpty(titulo)){
                    updateSuceso(sucesoId,fecha,titulo,texto);
                    b.dismiss();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteSuceso(sucesoId);
                b.dismiss();

            }
        });
    }

    public boolean updateSuceso(String id, String fecha, String titulo, String acontecimiento){

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("sucesos").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId()).child(id);

        Suceso suceso = new Suceso(fecha,titulo,acontecimiento);
        suceso.setId(id);
        dR.setValue(suceso);
        Toast.makeText(this, "Se altero de forma PREMANENTE el suceso "+suceso.getTitulo(), Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean deleteSuceso(String id){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("sucesos").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId()).child(id);
        Toast.makeText(this, "Se elimino de forma PREMANENTE el suceso", Toast.LENGTH_SHORT).show();
        dR.removeValue();


        return true;
    }


    public void EditorDecronologia (View view){

        Intent intent = new Intent(Cronologia.this, EditorSucesos.class);
        startActivity(intent);

    }
}
