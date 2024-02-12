package com.example.torneofutbol.ModificarEncuentros;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.torneofutbol.DBHelper;
import com.example.torneofutbol.R;
import com.example.torneofutbol.Resources.Equipo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AniadirEncuentro extends AppCompatActivity {

    EditText fecha;
    EditText equipo1;
    EditText equipo2;
    EditText puntos1;
    EditText puntos2;
    static List <Equipo> equipos = new ArrayList<Equipo>();
    Button InsertarEncuentro;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_encuentro);

        fecha = findViewById(R.id.textviewFechaEncuentro);
        equipo1 = findViewById(R.id.textviewNombreEquipo1);
        equipo2 = findViewById(R.id.textviewNombreEquipo2);
        puntos1 = findViewById(R.id.textviewPuntuacion1);
        puntos2 = findViewById(R.id.textviewPuntuacion2);

    }

    public void insertarEncuentro(View view) {
        DBHelper helper = new DBHelper(this);
        String txtFecha = fecha.getText().toString();
        int puntuacion1 = 0;
        int puntuacion2 = 0;
        boolean fechaCorrecta = true;
        boolean numerosCorrectos = true;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(txtFecha);
        } catch (ParseException e) {
            Toast.makeText(this, "Escribe la fecha en el formato día/mes/año", Toast.LENGTH_SHORT).show();
            fechaCorrecta = false;
            fecha.setText("");
        }



        if (fechaCorrecta) {
            equipos = helper.obtenerDatosEquipos();
            String txtEquipo1 = equipo1.getText().toString();
            String txtEquipo2 = equipo2.getText().toString();

            if (existeEquipo(txtEquipo1) && existeEquipo(txtEquipo2)) {



                try {
                    puntuacion1 = Integer.parseInt(puntos1.getText().toString());
                    puntuacion2 = Integer.parseInt(puntos2.getText().toString());

                    // Validación del rango de puntuaciones (puedes ajustar según tus reglas)

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Ingresa valores numéricos válidos (entre 0 y 10)", Toast.LENGTH_SHORT).show();
                    numerosCorrectos = false;
                    puntos1.setText("");
                    puntos2.setText("");
                }



                if (numerosCorrectos) {
                    if (helper.insertarEncuentro(txtFecha, txtEquipo1, txtEquipo2, puntuacion1, puntuacion2)) {
                        Toast.makeText(this, "Inserción hecha correctamente", Toast.LENGTH_SHORT).show();
                        if(puntuacion1>puntuacion2){
                            helper.actualizarPuntuacionGanador(txtEquipo1);
                        }else if(puntuacion2>puntuacion1){
                            helper.actualizarPuntuacionGanador(txtEquipo2);
                        }else{
                            helper.actualizarPuntuacionEmparte(txtEquipo1,txtEquipo2);
                        }
                    } else {
                        Toast.makeText(this, "Inserción no se ha hecho correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Uno o ambos equipos no existen", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean existeEquipo (String nombre){
        boolean existe = false;
        for (int i = 0; i < equipos.size(); i++) {
            if(equipos.get(i).getNombre().equalsIgnoreCase(nombre)){
                existe = true;
            }
        }
        return existe;
    }
}