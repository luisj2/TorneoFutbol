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

public class EliminarEquipo extends AppCompatActivity {

    TextView nombreEquipo;
    Button eliminarEquipo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_equipo);

        nombreEquipo = findViewById(R.id.etNumencuentro);
        eliminarEquipo = findViewById(R.id.btnEliminarEquipo);


    }

    public void eliminarEquipo (View view){
        DBHelper helper = new DBHelper(this);
        boolean existe = false;
        String txtNombreEquipo = nombreEquipo.getText().toString();

        List<Equipo> equipos = helper.obtenerDatosEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            if(equipos.get(i).getNombre().equalsIgnoreCase(txtNombreEquipo)){
                existe = true;
                if(helper.eliminarEquipo(equipos.get(i).getNombre())){
                    Toast.makeText(this, "Eliminado Correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "No se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            if(!existe){
                Toast.makeText(this, "No existe el equipo", Toast.LENGTH_SHORT).show();
            }

        }
    }
}