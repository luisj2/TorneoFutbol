package com.example.torneofutbol;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.torneofutbol.Resources.Equipo;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "Torneo";
    private static final int VERSION_BASE_DE_DATOS = 1;

    private static final int PUNTOS_GANAR = 3;
    private static final int PUNTOS_EMPATAR = 1;


    public DBHelper(Context context) {
        super(context,
                NOMBRE_BASE_DE_DATOS,
                null,
                VERSION_BASE_DE_DATOS);
    }

    public SQLiteDatabase obtenerInstanciaLectura() {
        return getReadableDatabase();
    }

    // Método para realizar una consulta de escritura
    public SQLiteDatabase obtenerInstanciaEscritura() {
        return getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Definición de la tabla Equipos
        String tablaEquipos = "CREATE TABLE Equipo (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT," +
                "Ciudad TEXT," +
                "Puntos INT," +
                "Imagen INT," +
                "Partidos_Jugados INT," +
                "Partidos_Ganados INT," +
                "Partidos_Empatados INT," +
                "Partidos_Perdidos INT," +
                "Jugador_1 TEXT," +
                "Jugador_2 TEXT," +
                "Jugador_3 TEXT" +
                ")";

        String tablaEncuentros = "CREATE TABLE Encuentros (" +

                "NumeroJornada INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Fecha TEXT," +
                "Equipo_1 TEXT," +
                "Equipo_2 TEXT," +
                "PuntuacionEquipo_1 INTEGER," +
                "PuntuacionEquipo_2 INTEGER" +
                ")";


        // Ejecución de la sentencia SQL para crear las tablas
        db.execSQL(tablaEquipos);
        db.execSQL(tablaEncuentros);
    }

    public boolean deleteEquipo (){
        SQLiteDatabase db = getWritableDatabase();
        int filasEliminadas = db.delete("Equipo", null, null);
        db.close();

        return filasEliminadas>0;
    }
    public int obtenerNumeroFilasEnEquipo() {
        SQLiteDatabase db = getReadableDatabase();
        String consulta = "SELECT COUNT(*) FROM Equipo";
        Cursor cursor = db.rawQuery(consulta, null);

        int numeroFilas = 0;

        if (cursor.moveToFirst()) {
            numeroFilas = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return numeroFilas;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lógica de actualización de la base de datos (puedes dejarlo vacío por ahora si no necesitas cambios en la actualización)
    }

    // Método para agregar un equipo a la tabla
    public void agregarEquipo(String nombre, String ciudad, int puntos, String[] mejoresJugadores,int imagen,int partidosGanados,int partidosEmpatados,int partidosPerdidos,int partidosJugados) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Nombre", nombre);
            values.put("Ciudad", ciudad);
            values.put("Puntos", puntos);
            values.put("Imagen",imagen);
            values.put("Partidos_Ganados",partidosGanados);
            values.put("Partidos_Perdidos",partidosPerdidos);
            values.put("Partidos_Empatados",partidosEmpatados);
            values.put("Partidos_Jugados",partidosJugados);
            if(mejoresJugadores.length==3){
                values.put("Jugador_1", mejoresJugadores[0]);
                values.put("Jugador_2", mejoresJugadores[1]);
                values.put("Jugador_3", mejoresJugadores[2]);
            }

            db.insert("Equipo", null, values);
            db.close();

    }

    @SuppressLint("Range")
    public List<Equipo> obtenerDatosEquipos() {
        Equipo equipo =null;
        List <Equipo> equipos = new ArrayList<Equipo>();
        String consulta = "SELECT * FROM Equipo ORDER BY Puntos desc";
        Cursor cursor= getReadableDatabase().rawQuery(consulta,null);

        if(cursor.moveToFirst()){
            do {
                equipo = new Equipo();
                equipo.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                equipo.setCiudad(cursor.getString(cursor.getColumnIndex("Ciudad")));
                equipo.setPuntos(cursor.getInt(cursor.getColumnIndex("Puntos")));
                equipo.setPartidosJugados(cursor.getInt(cursor.getColumnIndex("Partidos_Jugados")));
                equipo.setPartidosGanados(cursor.getInt(cursor.getColumnIndex("Partidos_Ganados")));
                equipo.setPartidosEmpatados(cursor.getInt(cursor.getColumnIndex("Partidos_Empatados")));
                equipo.setPuntos(cursor.getInt(cursor.getColumnIndex("Puntos")));

                equipos.add(equipo);


            }while(cursor.moveToNext());
        }

        return equipos;

    }


    public int obtenerCiudadPorNombre(String nombre) {
        int id = 0;
        String consulta = "SELECT Id FROM Equipo WHERE Nombre='" + nombre + "'";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, null);

        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }

        cursor.close();

        return id;
    }

    public boolean insertarEquipo(String nombre, String ciudad) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Nombre", nombre);
        values.put("Ciudad", ciudad);
        values.put("Puntos", 0);

        long resultado = db.insert("Equipo", null, values);

        db.close();


        return resultado != -1;
    }

    public boolean eliminarEquipo(String nombreEquipo) {
        SQLiteDatabase db = getWritableDatabase();

        int filasEliminadas = db.delete("Equipo", "Nombre=?", new String[]{nombreEquipo});
        db.close();


        return filasEliminadas > 0;
    }


    public boolean insertarEncuentro(String fecha,String equipo1,String equipo2,int puntuacion1,int puntuacion2) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Fecha", fecha);
        values.put("Equipo_1",equipo1);
        values.put("Equipo_2",equipo2);
        values.put("PuntuacionEquipo_1",puntuacion1);
        values.put("PuntuacionEquipo_2",puntuacion2);

        long resultado = db.insert("´Encuentros ", null, values);

        db.close();


        return resultado != -1;
    }

    public boolean actualizarPuntuacionGanador (String nombreEquipo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        int puntuacionAntigua = puntuacionEquipo(nombreEquipo);
        int puntuacionNueva = puntuacionAntigua+PUNTOS_GANAR;

        values.put("Puntos",puntuacionNueva);


        int filasAfectadas = db.update("Equipo",values,"Nombre=?",new String[]{nombreEquipo});


        return filasAfectadas > 0;


    }

    public boolean actualizarPuntuacionEmparte (String nombreEquipo1,String nombreEquipo2){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        int puntuacionAntigua1 = puntuacionEquipo(nombreEquipo1);
        int puntuacionNueva1 = puntuacionAntigua1+PUNTOS_EMPATAR;

        values.put("Puntos",puntuacionNueva1);
        int filasAfectadas1 = db.update("Equipo",values,"Nombre=?",new String[]{nombreEquipo1});

        values = new ContentValues();

        int puntuacionAntigua2 = puntuacionEquipo(nombreEquipo2);
        int puntuacionNueva2 = puntuacionAntigua2+PUNTOS_EMPATAR;

        values.put("Puntos",puntuacionNueva2);

        int filasAfectadas2 = db.update("Equipo",values,"Nombre=?",new String[]{nombreEquipo2});

        db.close();

        return filasAfectadas1 > 0 && filasAfectadas2>0;

    }


    public int puntuacionEquipo (String nombreEquipo){

        int puntuacion = 0;
        String consulta = "SELECT Puntuacion FROM Equipo WHERE nombre="+nombreEquipo;
        Cursor cursor= getReadableDatabase().rawQuery(consulta,null);

        if(cursor.moveToFirst()){
             puntuacion = cursor.getInt(0);
        }

        return puntuacion;

    }

    public boolean eliminarEncuentro(int numEncuentro) {
        SQLiteDatabase db = getWritableDatabase();
        int filasEliminadas = db.delete("Encuentros", "NumeroJornada=?", new String[]{String.valueOf(numEncuentro)});
        db.close();

        return filasEliminadas > 0;
    }










}
