package com.example.torneofutbol.Resources;

import java.io.Serializable;

public class Equipo implements Serializable {

   private String nombre;
   private String ciudad;
   private int puntos;
   private int partidosJugados;
   private int partidosGanados;
   private int partidosEmpatados;
   private int partidosPerdidos;
   private String [] mejoresJugadores;

   private int imagen;

    public Equipo(String nombre, String ciudad, int puntos, int partidosJugados, int partidosGanados, int partidosEmpatados, int partidosPerdidos, String[] mejoresJugadores,int imagen) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.puntos = puntos;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosEmpatados = partidosEmpatados;
        this.partidosPerdidos = partidosPerdidos;
        this.mejoresJugadores = mejoresJugadores;
        this.imagen = imagen;
    }


    public Equipo() {
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public String[] getMejoresJugadores() {
        return mejoresJugadores;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public void setMejoresJugadores(String[] mejoresJugadores) {
        this.mejoresJugadores = mejoresJugadores;
    }
}
