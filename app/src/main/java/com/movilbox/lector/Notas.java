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
import com.movilbox.lector.Referencias.Adapters.TextosAdapter;
import com.movilbox.lector.Referencias.Objetos.Libro;
import com.movilbox.lector.Referencias.Objetos.Nota;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;
import com.movilbox.lector.Referencias.Vistas.VistaNotas;

public class Notas extends AppCompatActivity {

    private ListView editor;
    private ArrayAdapter<Nota> adapter;
    private SingletonLibros libros;
    private DatabaseReference dbNotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        libros = SingletonLibros.getInstance();

        editor = (ListView) findViewById(R.id.listNotas);

        dbNotas = FirebaseDatabase.getInstance().getReference("notas").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());

        editor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                libros.setNotaSeleccionada(position);
                Intent intent = new Intent(Notas.this, VistaNotas.class);
                startActivity(intent);
            }
        });

        editor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Nota nota = libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaNotas().get(position);
                showUpdateDeleteDialog(nota.getId(),nota.getTitulo());
                return true;
            }
        });
    }

    protected void onStart() {
        super.onStart();
        dbNotas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaNotas().clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Nota nota=postSnapshot.getValue(Nota.class);
                    libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaNotas().add(nota);
                }
                adapter = new NotasAdapter(Notas.this, libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaNotas());
                editor.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDeleteDialog(final String notaId, String lugarTitulo){

        AlertDialog.Builder dialogBuilder = new  AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_nota,null);
        dialogBuilder.setView(dialogView);

        final EditText txtTitulo = (EditText) dialogView.findViewById(R.id.txtTitulo);
        final EditText txtImportancia = (EditText) dialogView.findViewById(R.id.txtImportancia);
        final EditText txtTexto = (EditText) dialogView.findViewById(R.id.txtTexto);

        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle(lugarTitulo);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();
                String importancia = txtImportancia.getText().toString();
                String texto = txtTexto.getText().toString();

                if(!TextUtils.isEmpty(titulo) && !TextUtils.isEmpty(importancia)){
                    updateNotas(notaId,titulo,texto, Integer.parseInt(importancia));
                    b.dismiss();
                }else {
                    if(!TextUtils.isEmpty(titulo)) {
                        updateNotas(notaId, titulo, texto, 0);
                        b.dismiss();
                    }
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteNotas(notaId);
                b.dismiss();

            }
        });
    }

    public boolean updateNotas(String id,String titulo, String texto, int importancia){

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("notas").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId()).child(id);
        Nota nota = new Nota(titulo, texto,importancia);
        nota.setId(id);
        dR.setValue(nota);
        Toast.makeText(this, "Se altero de forma PREMANENTE la nota", Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean deleteNotas(String id){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("notas").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId()).child(id);
        dR.removeValue();
        Toast.makeText(this, "Se elimino de forma PREMANENTE la nota", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void EditorDeTexto (View view){

        Intent intent = new Intent(Notas.this, EditorTexto.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(Notas.this, Cronologia.class);
        startActivity(intent);

    }

    public void MostrarTextos (View view){

        Intent intent = new Intent(Notas.this, Textos.class);
        startActivity(intent);

    }

    public void MostrarLugares (View view){

        Intent intent = new Intent(Notas.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(Notas.this, Personajes.class);
        startActivity(intent);

    }
    public void Notas (View view) {

        Intent intent = new Intent(Notas.this, Notas.class);
        startActivity(intent);
    }

    public void EditorDeNotas (View view){

        Intent intent = new Intent(Notas.this, EditorNotas.class);
        startActivity(intent);

    }

}
