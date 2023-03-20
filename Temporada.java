package vergeflix;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DAW-B
 */
public class Temporada implements Valorable {

    protected LocalDate fechaEstreno;
    protected ArrayList<Capitulo> capitulos;

    public Temporada(LocalDate fechaEstreno) {

        this.fechaEstreno = LocalDate.of(fechaEstreno.getYear(),
                fechaEstreno.getMonthValue(), fechaEstreno.getDayOfMonth());
        this.capitulos = new ArrayList<Capitulo>();
    }

    public Temporada(Temporada t) {

        this.fechaEstreno = t.fechaEstreno;
        this.capitulos = t.capitulos;

        for (Capitulo capitulo : t.capitulos) {

            this.capitulos.add(capitulo);
        }
    }

    @Override
    public String toString() {
        return "Temporada{" + "fechaEstreno=" + fechaEstreno + ", capitulos=" + capitulos.size();
    }

    public LocalDate getFechaEstreno() {

        return this.fechaEstreno;

    }

    public boolean añadirCapitulo(Capitulo capitulo) {
        boolean crearCapitulo = false;
        int encontrado = 0;
        boolean fechaOK = false;

        for (int i = 0; i < capitulos.size(); i++) {
            if (capitulo.titulo.equalsIgnoreCase(this.capitulos.get(i).getTitulo())) {
                encontrado++;
            }
        }
        if (encontrado != 0) {
            throw new IllegalArgumentException("El capítulo ya se encuentra en el catálogo.");
        }
        if (capitulo.fechaEmision.isAfter(fechaEstreno)) {
            fechaOK = true;
        } else {

            throw new IllegalArgumentException("La fecha de emisión es anterior a la fecha de estreno.");
        }

        if (encontrado == 0 && fechaOK == true) {
            this.capitulos.add(capitulo);
            crearCapitulo = true;
        }

        return crearCapitulo;
    }

    public boolean añadirCapitulo(LocalDate fechaEmision, String titulo) {
        boolean crearCapitulo = false;
        int encontrado = 0;
        boolean fechaOK = false;

        for (int i = 0; i < capitulos.size(); i++) {
            if (titulo.equalsIgnoreCase(this.capitulos.get(i).getTitulo())) {
                encontrado++;
            }
        }

        if (encontrado != 0) {
            throw new IllegalArgumentException("El capítulo ya se encuentra en el catálogo.");
        }

        if (fechaEmision.isAfter(fechaEstreno)) {
            fechaOK = true;
        } else {

            throw new IllegalArgumentException("La fecha de emisión es anterior a la fecha de estreno.");
        }

        if (encontrado == 0 && fechaOK == true) {
            this.añadirCapitulo(fechaEmision, titulo);
            crearCapitulo = true;
        }

        return crearCapitulo;
    }

    public boolean añadirCapitulo(int posicion, Capitulo capitulo) {
        boolean crearCapitulo = false;
        int encontrado = 0;
        boolean fechaOK = false;
        boolean posicionOK = false;
        Capitulo c;

        for (int i = 0; i < capitulos.size(); i++) {
            if (capitulo.titulo.equalsIgnoreCase(this.capitulos.get(i).getTitulo())) {
                encontrado++;
            }
        }

        if (encontrado != 0) {
            throw new IllegalArgumentException("El capítulo ya se encuentra en el catálogo.");
        }

        if (capitulo.fechaEmision.isAfter(fechaEstreno)) {
            fechaOK = true;
        } else {

            throw new IllegalArgumentException("La fecha de emisión es anterior a la fecha de estreno.");
        }

        if (Funciones.FuncionesVergeflix.comprobarRango(posicion, 0, this.capitulos.size())) {

            posicionOK = true;

        } else {

            throw new IllegalArgumentException("La posición está fuera de los límites");

        }

        if (encontrado == 0 && fechaOK && posicionOK) {

            c = new Capitulo(capitulo);

            this.añadirCapitulo(posicion, c);
            crearCapitulo = true;
        }

        return crearCapitulo;
    }

    public boolean añadirCapitulo(int posicion, LocalDate fechaEmision, String titulo) {
        boolean crearCapitulo = false;
        int encontrado = 0;
        boolean fechaOK = false;
        boolean posicionOK = false;
        boolean tituloOK = false;
        Capitulo c;

        for (int i = 0; i < capitulos.size(); i++) {
            if (titulo.equalsIgnoreCase(this.capitulos.get(i).getTitulo())) {
                encontrado++;
            }
        }

        if (encontrado != 0) {
            throw new IllegalArgumentException("El capítulo ya se encuentra en el catálogo.");
        }

        if (fechaEmision.isAfter(fechaEstreno)) {
            fechaOK = true;
        } else {

            throw new IllegalArgumentException("La fecha de emisión es anterior a la fecha de estreno.");
        }

        if (Funciones.FuncionesVergeflix.comprobarRango(posicion, 0, this.capitulos.size())) {

            posicionOK = true;

        } else {
            throw new IllegalArgumentException("La posición está fuera de los límites");
        }

        if (titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo no puede estar en blanco");
        } else {
            tituloOK = true;
        }

        if (encontrado == 0 && fechaOK && posicionOK && tituloOK) {

            c = new Capitulo(titulo, fechaEmision);

            this.añadirCapitulo(posicion, c);
            crearCapitulo = true;
        }

        return crearCapitulo;
    }

    public boolean eliminarCapitulo(String titulo) {
        boolean borrarCapitulo = false;
        int encontrado = 0;
        boolean fechaOK = false;
        int indice = -1;

        for (int i = 0; i < capitulos.size(); i++) {
            if (titulo.equalsIgnoreCase(this.capitulos.get(i).getTitulo())) {
                encontrado++;
                indice = i;
            }
        }

        if (encontrado != 0) {
            this.capitulos.remove(indice);
            borrarCapitulo = true;
        }

        return borrarCapitulo;
    }

    public int eliminarCapitulos(String expresionRegular) {
        int capitulosBorrados = 0;

        Iterator<Capitulo> iterador = this.capitulos.iterator();

        for (int i = 0; iterador.hasNext(); i++) {
            if (Funciones.FuncionesVergeflix.validarExpresionRegular(expresionRegular, capitulos.get(i).getTitulo())) {
                capitulos.remove(i);
                capitulosBorrados++;
            }
        }

        return capitulosBorrados;
    }

    public Capitulo getCapitulo(int posicion) {
        Capitulo c;
        c = new Capitulo(this.capitulos.get(posicion).titulo,
                this.capitulos.get(posicion).fechaEmision);
        c.votosNegativos = this.capitulos.get(posicion).getVotosNegativos();
        c.votosPositivos = this.capitulos.get(posicion).getVotosPositivos();

        return c;
    }

    public boolean setCapitulo(int posicion, LocalDate fechaEmision, String titulo) {
        boolean cambiarCapitulo = false;
        boolean posicionOK = false;
        boolean fechaOK = false;
        boolean tituloOK = true;
        Capitulo c;

        if (posicion < 0 || posicion > this.capitulos.size()) {
            posicionOK = true;
        } else {
            throw new IllegalArgumentException("El indice está fuera de los límites.");

        }

        if (fechaEmision.isBefore(fechaEstreno)) {
            fechaOK = true;
        } else {

            throw new IllegalArgumentException("La fecha de emisión es anterior a la fecha de estreno.");
        }

        if (titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo no puede estar en blanco");
        } else {
            tituloOK = true;
        }

        if (posicionOK && fechaOK && tituloOK) {

            c = new Capitulo(this.capitulos.get(posicion));

            c.setTitulo(titulo);
            c.setFechaEmision(fechaEmision);

            this.capitulos.remove(posicion);
            this.capitulos.add(c);

            cambiarCapitulo = true;
        }
        return cambiarCapitulo;
    }

    public boolean setCapitulo(int posicion, LocalDate fechaEmision) {
        boolean cambiarCapitulo = false;
        boolean posicionOK = false;
        boolean fechaOK = false;
        Capitulo c;

        if (posicion < 0 || posicion > this.capitulos.size()) {
            posicionOK = true;
        } else {
            throw new IllegalArgumentException("El indice está fuera de los límites.");

        }

        if (fechaEmision.isBefore(fechaEstreno)) {
            fechaOK = true;
        } else {

            throw new IllegalArgumentException("La fecha de emisión es anterior a la fecha de estreno.");
        }

        if (posicionOK && fechaOK) {

            c = new Capitulo(this.capitulos.get(posicion));

            c.setFechaEmision(fechaEmision);

            this.capitulos.remove(posicion);
            this.capitulos.add(c);

            cambiarCapitulo = true;
        }
        return cambiarCapitulo;
    }

    public boolean setCapitulo(int posicion, String titulo) {
        boolean cambiarCapitulo = false;
        boolean posicionOK = false;
        boolean tituloOK = true;
        Capitulo c;

        if (posicion <= 0 || posicion >= this.capitulos.size()) {
            posicionOK = true;
        } else {
            throw new IllegalArgumentException("El indice está fuera de los límites.");

        }

        if (titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar en blanco");
        } else {
            tituloOK = true;
        }

        if (posicionOK && tituloOK) {

            c = new Capitulo(this.capitulos.get(posicion));

            c.setTitulo(titulo);

            this.capitulos.remove(posicion);
            this.capitulos.add(c);

            cambiarCapitulo = true;
        }
        return cambiarCapitulo;
    }

    public boolean meGusta(int posicionCapitulo, boolean like) {
        boolean votar = false;

        if (posicionCapitulo >= 0 || posicionCapitulo <= this.capitulos.size()) {

            if (like) {
                this.capitulos.get(posicionCapitulo).votosPositivos++;
                votar = true;
            } else {
                this.capitulos.get(posicionCapitulo).votosNegativos++;
                votar = true;
            }
        } else {
            votar = false;
        }
        return votar;
    }

    public boolean meGusta(String titulo, boolean like) {
        boolean votar = false;
        int indice = -1;

        for (int i = 0; i < capitulos.size(); i++) {
            if (titulo.equalsIgnoreCase(this.capitulos.get(i).getTitulo())) {

                indice = i;
            }
        }

        if (indice != -1 && like) {

            if (like) {
                this.capitulos.get(indice).votosPositivos++;
                votar = true;
            } else {
                this.capitulos.get(indice).votosNegativos++;
                votar = true;

            }
        } else {
            votar = false;
        }
        return votar;
    }

    public boolean setFechaEstreno(LocalDate fecha) {
        boolean cambiarFecha = false;
        boolean fechaOK = true;

        for (int i = 0; i < this.capitulos.size(); i++) {

            if (this.capitulos.get(i).fechaEmision.isBefore(fecha)) {
                fechaOK = false;
            }
        }

        if (fechaOK) {

            this.fechaEstreno = fecha;

        }

        return cambiarFecha;

    }

    public ArrayList<Capitulo> capitulosMejorValorados(int n) {

        ArrayList<Capitulo> mejoresCapitulos = new ArrayList<>();
        for (Capitulo capitulo : this.capitulos) {
            if (mejoresCapitulos.size() < n) {
                mejoresCapitulos.add(capitulo);
            } else {
                int peorIndice = 0;
                for (int i = 1; i < mejoresCapitulos.size(); i++) {
                    if (mejoresCapitulos.get(i).calcularPuntuacion() < mejoresCapitulos.get(peorIndice).calcularPuntuacion()) {
                        peorIndice = i;
                    }
                }
                if (capitulo.calcularPuntuacion() > mejoresCapitulos.get(peorIndice).calcularPuntuacion()) {
                    mejoresCapitulos.set(peorIndice, capitulo);
                }
            }
        }
        Collections.sort(mejoresCapitulos, new Comparator<Capitulo>() {
          
            public int compare(Capitulo c1, Capitulo c2) {
                return Integer.compare(c2.calcularPuntuacion(), c1.calcularPuntuacion());
            }
        });
        ArrayList<Capitulo> mejoresCapitulosCopiados = new ArrayList<>();
        for (Capitulo capitulo : mejoresCapitulos) {
            mejoresCapitulosCopiados.add(new Capitulo(capitulo));
        }
        return mejoresCapitulosCopiados;
    }

    public int calcularPuntuacion() {
        int puntuacion = 0;
        int media;

        for (Capitulo capitulo : capitulos) {
            puntuacion += capitulo.calcularPuntuacion();
        }
        
        if (capitulos.size()==0){
            
            media=puntuacion;
            
        }else{
        media = puntuacion / capitulos.size();
        }
        
        return media;

    }

    public ArrayList listaCapitulos() {

        return this.capitulos;

    }

}
