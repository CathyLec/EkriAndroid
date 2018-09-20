package com.movilbox.lector.Referencias.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movilbox.lector.R;
import com.movilbox.lector.Referencias.Objetos.Libro;

import java.util.List;

public class TextosAdapter extends ArrayAdapter<Libro> {

    List<Libro> list;
    private Context context;

    public TextosAdapter(Context context, List<Libro> list) {
        super(context, R.layout.list_fila, list);
        this.list = list;
        this.context = context;
    }

    static class ViewHolder {

       // protected ImageView imgDescripcion;
        protected TextView txtTitulo;
        protected TextView txtDescripcion;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_fila,null);
            final LugaresAdapter.ViewHolder viewHolder = new LugaresAdapter.ViewHolder();
            viewHolder.txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
            viewHolder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
           // viewHolder.imgDescripcion = (ImageView) view.findViewById(R.id.imgDescripcion);

            view.setTag(viewHolder);
        }else{

            view=convertView;

        }
        LugaresAdapter.ViewHolder holder=(LugaresAdapter.ViewHolder) view.getTag();

        holder.txtTitulo.setText(list.get(position).getTitulo());
        //holder.imgDescripcion.setImageResource(list.get(position).getId_image);
        return view;
    }

}
