package vergeflix;

import java.time.LocalDate;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DAW-B
 */
public abstract class Media implements Comparable<Media>, Valorable{

    protected String nombre;
    protected int calificacionEdad;
    protected LocalDate fechaIncorporacionAlCatalogo;
    protected boolean estaDisponible;

    public Media(String nombre, int calificacionEdad, LocalDate fechaIncorporacion,
            boolean estaDisponible) {
        this.nombre = nombre;
        if(calificacionEdad>-1&&calificacionEdad<19){
        this.calificacionEdad = calificacionEdad;} 
        this.fechaIncorporacionAlCatalogo = fechaIncorporacion;
        this.estaDisponible = estaDisponible;
    }

    public String getNombre() {

        return nombre;
    }

    public int getCalificacionEdad() {
        return calificacionEdad;
    }

    public void setCalificacionEdad(int calificacionEdad) {
        if(calificacionEdad>-1&&calificacionEdad<19){
        this.calificacionEdad = calificacionEdad;}
    }

    public LocalDate getFechaIncorporacionAlCatalogo() {
        return fechaIncorporacionAlCatalogo;
    }

    public void setFechaIncorporacionAlCatalogo(LocalDate fechaIncorporacionAlCatalogo) {
        
        this.fechaIncorporacionAlCatalogo = fechaIncorporacionAlCatalogo;
    }

    public boolean getEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }

    @Override
    public String toString() {
        return "Título :" + nombre + ". Clasificacion de edad: " + calificacionEdad + "."
                + " Fecha de incorporación al catálogo: " + fechaIncorporacionAlCatalogo + ". Disponibilidad: "+Funciones.FuncionesVergeflix.disponibleToString(estaDisponible);
    }

    public boolean equals(Media m) {

        return this.nombre.equalsIgnoreCase(m.nombre) && this.calificacionEdad == m.calificacionEdad;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Media other = (Media) obj;
        if (this.calificacionEdad != other.calificacionEdad) {
            return false;
        }
        if (this.estaDisponible != other.estaDisponible) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.fechaIncorporacionAlCatalogo, other.fechaIncorporacionAlCatalogo);
    }

    @Override
    public int compareTo(Media m) {

        int resultado =0;

        resultado = this.nombre.compareTo(m.nombre);
        if (resultado == 0) {

            resultado = Integer.compare(calificacionEdad, m.calificacionEdad);
        }
        return resultado;
    }
}
