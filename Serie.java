package vergeflix;

import Funciones.FuncionesVergeflix;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DAW-B
 */
public class Serie extends Media {

    protected LocalDate fechaEstreno;
    protected ArrayList<Temporada> temporadas;

    public Serie(String nombre, int calificacionEdad, LocalDate fechaIncorporacion, boolean estaDisponible,
            LocalDate fechaEstreno) {
        super(nombre, calificacionEdad, fechaIncorporacion, estaDisponible);
        this.fechaEstreno = fechaEstreno;
        this.temporadas = new ArrayList<Temporada>();
    }

    public Serie(Serie s) throws CloneNotSupportedException {
        
        super(s.nombre, s.calificacionEdad, s.fechaIncorporacionAlCatalogo,
                s.estaDisponible);
        this.temporadas = new ArrayList<Temporada>();
        this.fechaEstreno = LocalDate.of(s.fechaEstreno.getYear(),
                s.fechaEstreno.getMonthValue(), s.fechaEstreno.getDayOfMonth());

        for (int i = 0; i < s.temporadas.size(); i++) {
            
            this.temporadas.add((Temporada) s.temporadas.get(i));

        }

    }

    public LocalDate getFechaEstreno() {

        return this.fechaEstreno;

    }

    @Override
    public String toString() {
        return super.toString() + ". Fecha de estreno: " + fechaEstreno
                + ". Temporadas: " + temporadas.size()+". Puntuación: "+this.calcularPuntuacion();
    }

    public boolean añadirTemporada(LocalDate fechaEstreno) {
        boolean crearTemporada = true;
        String fecha = fechaEstreno.toString();

        if (!FuncionesVergeflix.comprobarFecha(fecha)) {
            crearTemporada = false;
        } else {

            for (int i = 0; i < this.temporadas.size(); i++) {

                if (fechaEstreno.getMonthValue() == this.temporadas.get(i).fechaEstreno.getMonthValue()
                        &&fechaEstreno.getYear()==this.temporadas.get(i).fechaEstreno.getYear()) {

                    crearTemporada = false;
                }
            }
        }
        if (crearTemporada) {
            temporadas.add(new Temporada(fechaEstreno));
            crearTemporada = true;

        }
        return crearTemporada;

    }

    public boolean añadirTemporada(Temporada temporada) {
        boolean añadirTemporada = true;

        for (int i = 0; i < this.temporadas.size(); i++) {

            if (fechaEstreno.getMonthValue() == this.temporadas.get(i).fechaEstreno.getMonthValue()
                        &&fechaEstreno.getYear()==this.temporadas.get(i).fechaEstreno.getYear()) {

                añadirTemporada = false;
            }

            if (añadirTemporada) {

                Temporada t = new Temporada(temporada);

                this.temporadas.add(t);
            }
        }
        return añadirTemporada;
    }

    public boolean eliminarTemporada(int n) {
        boolean borrarTemporada = false;

        if (n >= 0 && n <= this.temporadas.size()) {

            this.temporadas.remove(n);
            borrarTemporada = true;

        }

        return borrarTemporada;
    }

    public boolean añadirCapitulo(int nTemporada, Capitulo capitulo) {
        boolean crearCapitulo = false;
        boolean indiceOK, fechaOK = false;
        Capitulo c;

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceOK = false;
        } else {
            indiceOK = true;
        }

        if (capitulo.fechaEmision.isAfter(this.temporadas.get(nTemporada).fechaEstreno)) {

            fechaOK = true;
        }

        if (indiceOK && fechaOK) {
            crearCapitulo = true;

            c = new Capitulo(capitulo);

            this.temporadas.get(nTemporada).capitulos.add(c);

        }

        return crearCapitulo;
    }

    public boolean añadirCapitulo(int nTemporada, LocalDate fechaEmision, String titulo) {
        boolean crearCapitulo = false;
        boolean indiceOK = true, fechaOK = false, tituloOK = true;
        Capitulo c;

        if (nTemporada >= 0 && nTemporada <= this.temporadas.size()) {
            indiceOK = true;
        } else {
            indiceOK = false;
        }

        if (fechaEmision.isAfter(this.temporadas.get(nTemporada).fechaEstreno)||fechaEmision.isEqual(this.temporadas.get(nTemporada).fechaEstreno)) {

            fechaOK = true;
        }

        if (!this.temporadas.get(nTemporada).capitulos.isEmpty()) {

            for (int i = 0; i < this.temporadas.get(nTemporada).capitulos.size(); i++) {

                if (titulo.equalsIgnoreCase(this.temporadas.get(nTemporada).capitulos.get(i).titulo) || titulo.isBlank()) {
                    tituloOK = false;
                }
            }
        } else {
            tituloOK = true;
        }

        if (indiceOK && fechaOK && tituloOK) {
            crearCapitulo = true;

            c = new Capitulo(titulo, fechaEmision);

            this.temporadas.get(nTemporada).capitulos.add(c);
        }
        return crearCapitulo;
    }

    public boolean añadirCapitulo(int nTemporada, int posicion, Capitulo capitulo) {
        boolean añadirCapitulo = false;
        boolean indiceCapOK, indiceTempOK, fechaOK = false, tituloOK = true;

        if (capitulo.fechaEmision.isAfter(this.temporadas.get(nTemporada).fechaEstreno)) {

            fechaOK = true;

        }

        for (Capitulo cap : this.temporadas.get(nTemporada).capitulos) {

            if (capitulo.titulo.equalsIgnoreCase(cap.titulo)) {
                tituloOK = false;
            }
        }

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = true;
        } else {
            indiceTempOK = false;
        }

        if (posicion < 0 || posicion > this.temporadas.get(nTemporada).capitulos.size()) {
            indiceCapOK = false;
        } else {
            indiceCapOK = true;
        }

        if (fechaOK && tituloOK && indiceCapOK && indiceTempOK) {
            añadirCapitulo = true;

            Capitulo c = new Capitulo(capitulo);

            this.temporadas.get(nTemporada).capitulos.add(c);

        }

        return añadirCapitulo;
    }

    public boolean añadirCapitulo(int nTemporada, int posicion, LocalDate fechaEmision, String titulo) {
        boolean añadirCapitulo = false;
        boolean indiceCapOK, indiceTempOK, fechaOK = false, tituloOK = true;

        if (fechaEmision.isAfter(this.temporadas.get(nTemporada).fechaEstreno)) {

            fechaOK = true;
        }

        for (Capitulo cap : this.temporadas.get(nTemporada).capitulos) {

            if (titulo.equalsIgnoreCase(cap.titulo)) {
                tituloOK = false;
            }
        }

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = true;
        } else {
            indiceTempOK = false;
        }

        if (posicion < 0 || posicion > this.temporadas.get(nTemporada).capitulos.size()) {
            indiceCapOK = false;
        } else {
            indiceCapOK = true;
        }

        if (fechaOK && tituloOK && indiceCapOK && indiceTempOK) {
            añadirCapitulo = true;

            Capitulo c = new Capitulo(titulo, fechaEmision);

            this.temporadas.get(nTemporada).capitulos.add(c);
        }
        return añadirCapitulo;
    }

    public boolean eliminarCapitulo(int nTemporada, String titulo) {
        boolean borrarCapitulo = false;
        int encontrado = 0;
        int indice = -1;

        for (int i = 0; i < this.temporadas.size(); i++) {
            if (titulo.equalsIgnoreCase(this.temporadas.get(nTemporada).capitulos.get(i).titulo)) {
                encontrado++;
                indice = i;
            }
        }

        if (encontrado != 0) {
            this.temporadas.get(nTemporada).capitulos.remove(indice);
            borrarCapitulo = true;
        }

        return borrarCapitulo;
    }

    public int eliminarCapitulos(String expresionRegular) {
        int capitulosBorrados = 0;

        Iterator<Temporada> iterador = this.temporadas.iterator();

        for (int i = 0; iterador.hasNext(); i++) {
            if (Funciones.FuncionesVergeflix.validarExpresionRegular(expresionRegular, this.temporadas.get(i).capitulos.get(i).getTitulo())) {
                this.temporadas.get(i).capitulos.remove(i);
                capitulosBorrados++;
            }
        }
        return capitulosBorrados;
    }

    public Capitulo getCapitulo(int nTemporada, int posicion) throws CloneNotSupportedException {

        Capitulo c = (Capitulo) this.temporadas.get(nTemporada).capitulos.get(posicion);

        return c;
    }

    public boolean setCapitulo(int nTemporada, int posicion, LocalDate fechaEmision, String titulo) {
        boolean setCapitulo = false;
        boolean indiceTempOK, indiceCapOK, fechaOK = false, tituloOK = true;

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = false;
        } else {
            indiceTempOK = true;
        }

        if (posicion < 0 || posicion > this.temporadas.get(nTemporada).capitulos.size()) {
            indiceCapOK = false;
        } else {
            indiceCapOK = true;
        }

        if (fechaEmision.isAfter(this.temporadas.get(nTemporada).fechaEstreno)) {

            fechaOK = true;
        }

        if (titulo.isBlank()) {
            tituloOK = false;

        }
        if (indiceTempOK && indiceCapOK && fechaOK && tituloOK) {
            setCapitulo = true;

            this.temporadas.get(nTemporada).capitulos.get(posicion).setFechaEmision(fechaEmision);
            this.temporadas.get(nTemporada).capitulos.get(posicion).setTitulo(titulo);
        }
        return setCapitulo;
    }

    public boolean setCapitulo(int nTemporada, int posicion, LocalDate fechaEmision) {
        boolean setCapitulo = false;
        boolean indiceTempOK, indiceCapOK, fechaOK = false, tituloOK = true;

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = false;
        } else {
            indiceTempOK = true;
        }

        if (posicion < 0 || posicion > this.temporadas.get(nTemporada).capitulos.size()) {
            indiceCapOK = false;
        } else {
            indiceCapOK = true;
        }

        if (fechaEmision.isAfter(this.temporadas.get(nTemporada).fechaEstreno)) {

            fechaOK = true;
        }

        if (indiceTempOK && indiceCapOK && fechaOK) {
            setCapitulo = true;

            this.temporadas.get(nTemporada).capitulos.get(posicion).setFechaEmision(fechaEmision);

        }
        return setCapitulo;
    }

    public boolean setCapitulo(int nTemporada, int posicion, String titulo) {
        boolean setCapitulo = false;
        boolean indiceTempOK, indiceCapOK, fechaOK = false, tituloOK = true;
        Capitulo c;

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = false;
        } else {
            indiceTempOK = true;
        }

        if (posicion < 0 || posicion > this.temporadas.get(nTemporada).capitulos.size()) {
            indiceCapOK = false;
        } else {
            indiceCapOK = true;
        }

        if (titulo.isBlank()) {
            tituloOK = false;

        }
        if (indiceTempOK && indiceCapOK && tituloOK) {
            setCapitulo = true;

            this.temporadas.get(nTemporada).capitulos.get(posicion).setTitulo(titulo);
        }
        return setCapitulo;
    }

    public boolean meGusta(int nTemporada, int posicionCapitulo, boolean like) {
        boolean votar = false;
        boolean indiceTempOK, indiceCapOK;

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = false;
        } else {
            indiceTempOK = true;
        }

        if (posicionCapitulo < 0 || posicionCapitulo > this.temporadas.get(nTemporada).capitulos.size()) {
            indiceCapOK = false;
        } else {
            indiceCapOK = true;
        }

        if (indiceTempOK && indiceCapOK) {
            votar = true;

            this.temporadas.get(nTemporada).capitulos.get(posicionCapitulo).meGusta(like);
        }

        return votar;
    }

    public boolean meGusta(int nTemporada, String titulo, boolean like) {
        boolean votar = false;
        boolean indiceTempOK;
        int encontrado = 0, indice = -1;

        if (nTemporada < 0 || nTemporada > this.temporadas.size()) {
            indiceTempOK = false;
        } else {
            indiceTempOK = true;
        }

        for (int i = 0; i < this.temporadas.size(); i++) {
            if (titulo.equalsIgnoreCase(this.temporadas.get(nTemporada).capitulos.get(i).titulo)) {
                encontrado++;
                indice = i;
            }
        }

        if (indiceTempOK && encontrado != 0) {
            votar = true;

            this.temporadas.get(nTemporada).capitulos.get(indice).meGusta(like);
        }

        return votar;
    }

    public boolean setFechaEstreno(LocalDate fecha) {
        boolean cambiarFecha = false;

        if (fecha.isEqual(this.fechaEstreno) || fecha.isBefore(this.fechaEstreno)) {

            this.fechaEstreno = fecha;
            cambiarFecha = true;
        }

        return cambiarFecha;
    }

    public Temporada getCopiaTemporada(int nTemporada) throws CloneNotSupportedException {

        return (Temporada) this.temporadas.get(nTemporada);

    }

   
 

    public ArrayList<Temporada> listaTemporadas() {
        return this.temporadas;
    }

  
    public int calcularPuntuacion() {
        int puntuacion = 0;
        int media;

        for (Temporada temporada : temporadas) {
            puntuacion += temporada.calcularPuntuacion();
        }

        media = puntuacion / temporadas.size();

        return media;
    }

    public boolean getEstaDisponible() {
        return this.estaDisponible;
    }

    public void setNombre(String titulo) {
        if (!nombre.isBlank()){
        this.nombre=titulo;
        }
    }

}
