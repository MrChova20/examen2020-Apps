package com.example.pchoapa_upv.examen2020estrella;

import java.util.HashMap;
import java.util.Map;

public class Lectura {
    private long tiempo;
    private String ciudad;
    private int intensidad;

    public Lectura(long tiempo, String ciudad, int intensidad) {
        this.tiempo = tiempo;
        this.ciudad = ciudad;
        this.intensidad = intensidad;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    @Override
    public String toString() {
        return "Lectura{" +
                "tiempo=" + tiempo +
                ", ciudad='" + ciudad + '\'' +
                ", intensidad=" + intensidad +
                '}';
    }



    static Map<Integer,Lectura> creaMapa(long[] tiempo, String[] ciudad, int[] intensidad) {
        Map<Integer,Lectura> listaMapa = new HashMap<>();
        for (int i = 0; i<tiempo.length; i++) {
            listaMapa.put(i+1,new Lectura(tiempo[i],ciudad[i],intensidad[i]));
        }
        return listaMapa;
    }
}
