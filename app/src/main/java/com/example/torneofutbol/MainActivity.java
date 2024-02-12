package com.example.torneofutbol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torneofutbol.Resources.Equipo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView barraNavegacion;
    public static List <Equipo> equipos = new ArrayList<Equipo>();

    TextView textView;
    Button button;

    public MainActivity() {
    }

    public MainActivity(int contentLayoutId) {
        super(contentLayoutId);
    }

    public static List<Equipo> getEquipos() {
        return equipos;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rellenar toda la informacion de los equipos en un arraylist
        datosEquipos();

        // IDS DE LOS ELEMENTOS
        barraNavegacion = findViewById(R.id.barra_navegacion);

        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home) {

                    return true;
                }
               else if (item.getItemId() == R.id.Clasificacion) {
                    moverseAClasificacion();
                    return true;
                } else if (item.getItemId() == R.id.Estadisticas) {
                    moverseAEstadisticas();
                    return true;
                }else if(item.getItemId() == R.id.Modificar){
                    moverseAModificar();
                    return true;
                }
                return false;
            }
        });

    button = (Button) findViewById(R.id.buttonEst);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            moverseAEstadisticas();
        }
    });
    //inicializamos el helper de la clase de la base de datos
        DBHelper helper = new DBHelper(this);




        /*
        if(helper.deleteEquipo()){
            mostrarToast("Borrado Correctamente");
        }else{
            mostrarToast("No borrado correctamente");
        }

         */





    //insertar la informacion del arraylist de equipos en la base de datos
        if(helper.obtenerNumeroFilasEnEquipo()==0){
            insertarEquipos(helper);
        }


       // mostrarToast("filas de informacion "+helper.obtenerNumeroFilasEnEquipo());

        //textView = findViewById(R.id.txtView1);

        equipos.clear();
        equipos = helper.obtenerDatosEquipos();

        mostrarToast("Partidos Jugados: "+equipos.get(0).getPartidosJugados());






    }

    public void moverseAClasificacion(){
        Intent intent = new Intent(this, Clasificacion.class);
        startActivity(intent);

    }
    public void moverseAEstadisticas(){
        Intent intent = new Intent(this, Estadisticas.class);
        //intent.putExtra("ListaEquipos",(Serializable) equipos);
        startActivity(intent);
    }
    public void moverseAModificar(){
        Intent intent = new Intent(this,Modificar.class);
        startActivity(intent);
    }



        public void datosEquipos() {

            equipos.add(new Equipo("Real Madrid", "Madrid", 25, 10, 8, 1, 1, new String[]{"Sergio Ramos", "Karim Benzema", "Luka Modric"},R.drawable.madrid));
            equipos.add(new Equipo("Barcelona", "Barcelona", 22, 10, 7, 1, 2, new String[]{"Lionel Messi", "Gerard Piqué", "Ansu Fati"},R.drawable.barcelona));
            equipos.add(new Equipo("Manchester United", "Manchester", 19, 10, 6, 1, 3, new String[]{"Bruno Fernandes", "Marcus Rashford", "Paul Pogba"},R.drawable.manchester));
            equipos.add(new Equipo("Bayern Munich", "Munich", 17, 10, 5, 2, 3, new String[]{"Robert Lewandowski", "Thomas Müller", "Joshua Kimmich"},R.drawable.munich));
            equipos.add(new Equipo("Juventus", "Turín", 14, 10, 3, 5, 2, new String[]{"Cristiano Ronaldo", "Paulo Dybala", "Giorgio Chiellini"},R.drawable.juventus));
            equipos.add(new Equipo("Liverpool", "Liverpool", 13, 10, 3, 4, 3, new String[]{"Mohamed Salah", "Sadio Mané", "Virgil van Dijk"},R.drawable.liverpool));
            equipos.add(new Equipo("Paris Saint-Germain", "París", 16, 10, 4, 4, 2, new String[]{"Neymar", "Kylian Mbappé", "Marco Verratti"},R.drawable.paris));


        }

    public void insertarEquipos (DBHelper helper) {

        for (int i = 0; i < equipos.size(); i++) {
            helper.agregarEquipo(equipos.get(i).getNombre(),equipos.get(i).getCiudad(),equipos.get(i).getPuntos(),equipos.get(i).getMejoresJugadores(),equipos.get(i).getImagen(),equipos.get(i).getPartidosGanados(),equipos.get(i).getPartidosEmpatados(),equipos.get(i).getPartidosPerdidos(),equipos.get(i).getPartidosJugados());
        }
    }
    private void mostrarToast(String mensaje) {
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, mensaje, duracion);
        toast.show();
    }

    }



