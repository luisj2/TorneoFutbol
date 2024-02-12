package com.example.torneofutbol.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torneofutbol.DBHelper;
import com.example.torneofutbol.Estadisticas;
import com.example.torneofutbol.R;
import com.example.torneofutbol.Resources.Equipo;

import java.util.List;

public class AdapterEstadisticas extends BaseAdapter {

    private Context context;
    private List<Equipo> equipos;
    private List<Integer> imagenes;
    private LayoutInflater inflater;

    public AdapterEstadisticas(Context context, DBHelper helper, List<Integer> imagenes) {
        this.context = context;
        this.equipos = helper.obtenerDatosEquipos();
        this.imagenes = imagenes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return equipos.size();
    }

    @Override
    public Equipo getItem(int position) {
        return equipos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Solo inflar si convertView es nulo
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list_view_estadisticas, parent, false);
        }



        TextView puesto = convertView.findViewById(R.id.tvPuesto);
        // ImageView escudo = convertView.findViewById(R.id.imagenEscudo);

        TextView puntos = convertView.findViewById(R.id.tvPuntos);

        TextView nombreEquipo = convertView.findViewById(R.id.tvNombreEquipo);

        // Cambiar el contenido de estos elementos
        puesto.setText(String.valueOf(position + 1));
        // escudo.setImageResource(imagenes.get(position));

        puntos.setText(String.valueOf(equipos.get(position).getPuntos()));
        nombreEquipo.setText(equipos.get(position).getNombre());




        return convertView;
    }

}
