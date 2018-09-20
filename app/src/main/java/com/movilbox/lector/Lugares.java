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
import com.movilbox.lector.Referencias.Adapters.LugaresAdapter;
import com.movilbox.lector.Referencias.Adapters.NotasAdapter;
import com.movilbox.lector.Referencias.Objetos.Lugar;
import com.movilbox.lector.Referencias.Objetos.Nota;
import com.movilbox.lector.Referencias.Singleton.SingletonLibros;
import com.movilbox.lector.Referencias.Vistas.VistaLugar;


public class Lugares extends AppCompatActivity {

    private ListView listLugares;
    private ArrayAdapter<Lugar> adapter;
    private SingletonLibros libros;
    private DatabaseReference dbLugares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

        libros = SingletonLibros.getInstance();

        listLugares=(ListView) findViewById(R.id.listLugares);

        dbLugares = FirebaseDatabase.getInstance().getReference("lugares").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId());

        listLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                libros.setLugarSeleccionado(position);
                Intent intent = new Intent(Lugares.this, VistaLugar.class);
                startActivity(intent);
            }
        });

        listLugares.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar lugar = libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaLugares().get(position);
                showUpdateDeleteDialog(lugar.getId(),lugar.getNombre());
                return true;
            }
        });
    }

    protected void onStart() {
        super.onStart();
        dbLugares.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaLugares().clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Lugar lugar=postSnapshot.getValue(Lugar.class);
                    libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaLugares().add(lugar);
                }
                adapter = new LugaresAdapter(Lugares.this, libros.getListaLibros().get(libros.getLibroSeleccionado()).getListaLugares());
                listLugares.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void EditorDeTexto (View view){

        Intent intent = new Intent(Lugares.this, EditorTexto.class);
        startActivity(intent);

    }

    public void Cronologia (View view){

        Intent intent = new Intent(Lugares.this, Cronologia.class);
        startActivity(intent);

    }

    public void MostrarTextos (View view){

        Intent intent = new Intent(Lugares.this, Textos.class);
        startActivity(intent);

    }

    public void MostrarLugares (View view){

        Intent intent = new Intent(Lugares.this, Lugares.class);
        startActivity(intent);

    }

    public void MostrarPersonajes (View view){

        Intent intent = new Intent(Lugares.this, Personajes.class);
        startActivity(intent);

    }
    public void Notas (View view) {

        Intent intent = new Intent(Lugares.this, Notas.class);
        startActivity(intent);
    }

    private void showUpdateDeleteDialog(final String lugarId, String lugarTitulo){

        AlertDialog.Builder dialogBuilder = new  AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_lugar,null);
        dialogBuilder.setView(dialogView);

        final EditText txtNombre = (EditText) dialogView.findViewById(R.id.txtNombre);
        final EditText txtUbicacion = (EditText) dialogView.findViewById(R.id.txtUbicacion);
        final EditText txtDescripcion = (EditText) dialogView.findViewById(R.id.txtDescripcion);

        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle(lugarTitulo);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String ubicacion = txtUbicacion.getText().toString();
                String descripcion = txtDescripcion.getText().toString();

                if(!TextUtils.isEmpty(nombre)){
                    updateLugares(lugarId,ubicacion,nombre,descripcion);
                    b.dismiss();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteLugares(lugarId);
                b.dismiss();

            }
        });
    }

    public boolean updateLugares(String id, String nombre, String ubicacion, String descripcion){

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("lugares").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId()).child(id);
        Lugar lugar = new Lugar(nombre, ubicacion, descripcion);
        lugar.setId(id);
        dR.setValue(lugar);
        Toast.makeText(this, "Se altero de forma PREMANENTE el lugar", Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean deleteLugares(String id){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("lugares").child(libros.getListaLibros().get(libros.getLibroSeleccionado()).getId()).child(id);
        dR.removeValue();
        Toast.makeText(this, "Se elimino de forma PREMANENTE el lugar", Toast.LENGTH_SHORT).show();
        return true;
    }


    public void MostrarLugar (View view){

        Intent intent = new Intent(Lugares.this, EditorLugar.class);
        startActivity(intent);

    }

}
