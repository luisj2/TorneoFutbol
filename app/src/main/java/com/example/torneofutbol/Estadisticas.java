package com.example.torneofutbol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.torneofutbol.Adapters.AdapterEstadisticas;
import com.example.torneofutbol.Resources.Equipo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas extends AppCompatActivity {

    BottomNavigationView barraNavegacion;
    ListView listView;
    List<Equipo> equipos = new ArrayList<>();

    /*
    Intent intent = getIntent();
    List<Equipo> equipos = (List<Equipo>) intent.getSerializableExtra("listaEquipos");
*/


    MainActivity home = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        DBHelper helper = new DBHelper(this);

        equipos = helper.obtenerDatosEquipos();

        //mostrarToast("puntuacion "+equipos.get(0).getPuntos());

        listView = findViewById(R.id.ListEstadisticas);

        barraNavegacion = findViewById(R.id.barra_navegacion);

        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){
                    moverseAHome();
                }
                else if (item.getItemId() == R.id.Clasificacion) {
                     moverseAClasificacion();
                     return true;
                 }else if(item.getItemId() == R.id.Modificar){
                    moverseAModificar();
                    return true;
                }
                return false;
            }
        });


        AdapterEstadisticas adapter = new AdapterEstadisticas(getApplicationContext(),helper,null);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Equipo equipo = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), InfoEquipo.class);
                intent.putExtra("EquipoSeleccionado", equipo);
                startActivity(intent);
            }
        });






    }
    public void moverseAHome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void moverseAModificar(){
        Intent intent = new Intent(this,Modificar.class);
        startActivity(intent);
    }
    public void moverseAClasificacion(){
        Intent intent = new Intent(this, Clasificacion.class);
        startActivity(intent);

    }
    private void mostrarToast(String mensaje) {
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, mensaje, duracion);
        toast.show();
    }
}