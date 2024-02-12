package com.example.torneofutbol.ModificarEncuentros;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torneofutbol.DBHelper;
import com.example.torneofutbol.R;

public class EliminarEncuentros extends AppCompatActivity {

    EditText numEncuentro;
    Button botonEliminar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_encuentros);

        numEncuentro = findViewById(R.id.etNumencuentro);
        botonEliminar = findViewById(R.id.btnEliminarEncuentro);
    }

    public void eliminarEncuentro (View view){
        DBHelper helper = new DBHelper(this);


        int encuentroInt = Integer.valueOf(numEncuentro.getText().toString());
        String consulta = "SELECT count(*) FROM Encuentros WHERE NumeroJornada="+encuentroInt;

        Cursor cursor = helper.getReadableDatabase().rawQuery(consulta,null);


        if(!cursor.moveToFirst()) {

            Toast.makeText(this, "No existe el encuentro", Toast.LENGTH_SHORT).show();

        }else{
            if(helper.eliminarEncuentro(encuentroInt)) {
                Toast.makeText(this, "Encuentro eliminado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Encuentro no eliminado", Toast.LENGTH_SHORT).show();
            }
        }


    }
}