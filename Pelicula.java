package vergeflix;

import Funciones.FuncionesVergeflix;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DAW-B
 */
public class Pelicula extends Media {

    protected String director;
    protected String actorPrincipal;
    protected int duracion; // Total de minutos que dura la película. Deberá ser un valor mayor a 60 minutos.
    protected Tematica categoria;
    protected ArrayList<Integer> votos;

    public Pelicula(String nombre, int calificacionEdad, LocalDate fechaIncorporacion, boolean estaDisponible,
            String director, String actorPrincipal, int duracion, Tematica categoria) {
        super(nombre, calificacionEdad, fechaIncorporacion, estaDisponible);
        this.director = director;
        this.actorPrincipal = actorPrincipal;
        this.duracion = duracion;
        this.categoria = categoria;
        this.votos = new ArrayList<>();
    }

    public Pelicula(Pelicula p) {

        super(p.nombre, p.calificacionEdad,
                p.fechaIncorporacionAlCatalogo, p.estaDisponible);
        this.director = p.director;
        this.actorPrincipal = p.actorPrincipal;
        this.duracion = p.duracion;
        this.categoria = p.categoria;

    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if (!director.isBlank()) {
            this.director = director;
        }
    }

    public String getActorPrincipal() {
        return actorPrincipal;
    }

    public void setActorPrincipal(String actorPrincipal) {
        if (!actorPrincipal.isBlank()) {
            this.actorPrincipal = actorPrincipal;
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if (FuncionesVergeflix.comprobarRango(duracion, 59, 241)) {
            this.duracion = duracion;
        }
    }

    public Tematica getCategoria() {
        return categoria;
    }

    public void setCategoria(Tematica categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isBlank()) {
            this.nombre = nombre;
        }
    }

    public boolean getEstaDisponible() {
        return estaDisponible;
    }

    @Override
    public String toString() {

        return super.toString() + ". Director: " + director + ". Actor Principal: "
                + actorPrincipal + ". Duración: " + duracion + "minutos. Categoria: " + categoria + ". Puntuación:" + this.calcularPuntuacion();
    }

    public boolean votar(int voto) {
        boolean votado = false;
        if (voto <= 10 && voto >= 0) {
            votos.add(voto);
            votado = true;
        } else {
            throw new IllegalArgumentException("El voto no está entre cero y diez.");
        }
        return votado;
    }

    public int calcularPuntuacion() {
        int puntuacion = 0;
        int media = 0;

        if (votos != null) {
            for (int voto : votos) {
                puntuacion += voto;
            }
            if (votos.size() == 0) {
                media = puntuacion;
            } else {
                media = puntuacion / votos.size();
            }
        }
        return media;
    }

}
