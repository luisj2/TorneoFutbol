package com.example.torneofutbol.ModificarEquipo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torneofutbol.DBHelper;
import com.example.torneofutbol.R;
import com.example.torneofutbol.Resources.Equipo;

import java.util.List;

public class AniadirEquipo extends AppCompatActivity {

    TextView nombreEquipo;
    TextView ciudadEquipo;
    Button insertarEquipo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_equipo);
        nombreEquipo = findViewById(R.id.etNumencuentro);
        ciudadEquipo = findViewById(R.id.textviewFechaEncuentro);

        insertarEquipo = findViewById(R.id.btnBuscar);
    }

    public void funcionInsertarEquipo (View view){
        DBHelper helper = new DBHelper(this);
        boolean existeEquipo = false;
        String txtNombreEquipo = nombreEquipo.getText().toString();
        String txtciudadEquipo = ciudadEquipo.getText().toString();

        List <Equipo> equipos = helper.obtenerDatosEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            if(equipos.get(i).getNombre().equalsIgnoreCase(txtNombreEquipo)){
                existeEquipo = true;
                break;
            }
        }

        if(existeEquipo){
            Toast.makeText(this, "El equipo ya existe", Toast.LENGTH_SHORT).show();
        }else{
            if(helper.insertarEquipo(txtNombreEquipo,txtciudadEquipo)){
                Toast.makeText(this, "Equipo insetado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se ha insertado correctamente", Toast.LENGTH_SHORT).show();
            }
        }


    }
}