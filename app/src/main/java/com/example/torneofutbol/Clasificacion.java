package com.example.torneofutbol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Clasificacion extends AppCompatActivity {

    BottomNavigationView barraNavegacion;

    MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);

        main = new MainActivity();

        barraNavegacion = findViewById(R.id.barra_navegacion);

        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home) {
                    moverseAHome();
                    return true;
                }

                else if (item.getItemId() == R.id.Estadisticas) {
                    moverseAEstadisticas();
                    return true;
                }else if(item.getItemId() == R.id.Modificar){
                    moverseAModificar();
                    return true;
                }
                return false;
            }
        });
    }

    public void moverseAHome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void moverseAEstadisticas(){
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }
    public void moverseAModificar(){
        Intent intent = new Intent(this,Modificar.class);
        startActivity(intent);
    }
}