package com.example.torneofutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torneofutbol.Resources.Equipo;

public class InfoEquipo extends AppCompatActivity {

    Bundle datos;
    TextView nombreEquipo;
    TextView partidosEmpatados;
    TextView partidosGanados;
    TextView partidosPerdidos;



    ImageView escudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_equipo);

        nombreEquipo = findViewById(R.id.tvinfoNombreEquipo);
        partidosEmpatados = findViewById(R.id.tvinfoPartidosEmpatados);
        partidosGanados = findViewById(R.id.tvPartidosGanados);
        partidosPerdidos = findViewById(R.id.tvinfoPartidosPerdidos);
        escudo = findViewById(R.id.imagenEscudo);




        datos = getIntent().getExtras();
        Equipo equipo = (Equipo) datos.getSerializable("EquipoSeleccionado");



        nombreEquipo.setText(equipo.getNombre());
        partidosEmpatados.setText(String.valueOf(equipo.getPartidosEmpatados()));
        partidosPerdidos.setText(String.valueOf(equipo.getPartidosPerdidos()));
        partidosGanados.setText(String.valueOf(equipo.getPartidosGanados()));
        escudo.setImageResource(equipo.getImagen());





    }
}