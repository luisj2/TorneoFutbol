package com.example.torneofutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.torneofutbol.ModificarEncuentros.AniadirEncuentro;
import com.example.torneofutbol.ModificarEncuentros.EliminarEncuentros;
import com.example.torneofutbol.ModificarEquipo.AniadirEquipo;
import com.example.torneofutbol.ModificarEquipo.EliminarEquipo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Modificar extends AppCompatActivity {

    BottomNavigationView barraNavegacion;

    Button aniadirEquipo;
    Button eliminarEquipo;
    Button agregarEncuentro;
    Button eliminarEncuentro;

    Button buscar;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        aniadirEquipo = findViewById(R.id.btnAniadirEquipo);
        eliminarEquipo = findViewById(R.id.btnEliminarEquipo);
        agregarEncuentro = findViewById(R.id.btnAniadirEncuentro);
        eliminarEncuentro = findViewById(R.id.btnEliminarEncuentro);
        buscar = findViewById(R.id.buttonbsc);






    }

    public void mandarAniadirEquipo (View view){
        Intent intent = new Intent(this, AniadirEquipo.class);
        startActivity(intent);
    }
    public void mandarEliminarEquipo (View view){
        Intent intent = new Intent(this, EliminarEquipo.class);
        startActivity(intent);
    }
    public void mandarAniadirEncuentro (View view){
        Intent intent = new Intent(this, AniadirEncuentro.class);
        startActivity(intent);
    }
    public void mandarEliminarEncuentro (View view){
        Intent intent = new Intent(this, EliminarEncuentros.class);
        startActivity(intent);
    }
    public void mandarBuscar (View view){
        Intent intent = new Intent(this, BuscarPorNombreOCiudad.class);
        startActivity(intent);
    }
}