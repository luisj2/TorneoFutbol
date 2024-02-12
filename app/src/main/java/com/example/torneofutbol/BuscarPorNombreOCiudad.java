package com.example.torneofutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torneofutbol.Resources.Equipo;

import java.util.List;

public class BuscarPorNombreOCiudad extends AppCompatActivity {

    RadioButton opcionCiudad;
    RadioButton opcionEquipo;

    EditText txtBuscar;

    Button buscar;

    TextView nombre;
    TextView ciudad;
    TextView puntos;

    DBHelper helper;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_por_nombre_ociudad);

        helper = new DBHelper(this);

        opcionCiudad = findViewById(R.id.radioButton);
        opcionEquipo = findViewById(R.id.radioButton2);
        txtBuscar = findViewById(R.id.etNumencuentro);
        buscar = findViewById(R.id.btnBuscar);
        nombre = findViewById(R.id.tvNombre);
        ciudad = findViewById(R.id.tvCiudad);
        puntos = findViewById(R.id.tvPuntos2);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarEquipo();
            }
        });
    }

    private void buscarEquipo() {
        String strBuscar = txtBuscar.getText().toString();

        if (opcionCiudad.isChecked()) {
            buscarPorCiudad(strBuscar);
        } else if (opcionEquipo.isChecked()) {
            buscarPorNombre(strBuscar);
        } else {
            Toast.makeText(this, "Elige un tipo de búsqueda", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarPorCiudad(String ciudadBuscar) {
        List<Equipo> equipos = helper.obtenerDatosEquipos();
        Equipo equipoEncontrado = null;

        for (Equipo equipo : equipos) {
            if (equipo.getCiudad().equalsIgnoreCase(ciudadBuscar)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        mostrarResultado(equipoEncontrado);
    }

    private void buscarPorNombre(String nombreBuscar) {
        List<Equipo> equipos = helper.obtenerDatosEquipos();
        Equipo equipoEncontrado = null;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreBuscar)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        mostrarResultado(equipoEncontrado);
    }

    private void mostrarResultado(Equipo equipo) {
        if (equipo != null) {
            nombre.setText(equipo.getNombre());
            ciudad.setText(equipo.getCiudad());
            puntos.setText(String.valueOf(equipo.getPuntos()));
        } else {
            Toast.makeText(this, "No se encontró el equipo o ciudad", Toast.LENGTH_SHORT).show();
        }
    }
}
