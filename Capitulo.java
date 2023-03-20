package vergeflix;

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
public class Capitulo implements Valorable{

    protected int votosPositivos;
    protected int votosNegativos;
    protected String titulo;
    protected LocalDate fechaEmision;

    public Capitulo(String titulo, LocalDate fechaEmision) {

        this.titulo = titulo;
        this.fechaEmision = fechaEmision;
        this.votosNegativos = 0;
        this.votosPositivos = 0;
    }

    public Capitulo(Capitulo c) {
        this.titulo = c.titulo;
        this.fechaEmision = LocalDate.of(c.fechaEmision.getYear(),
                c.fechaEmision.getMonthValue(), c.fechaEmision.getDayOfMonth());
        this.votosNegativos = c.getVotosNegativos();
        this.votosPositivos = c.getVotosPositivos();
    }

    public int getVotosPositivos() {
        return votosPositivos;
    }

    public int getVotosNegativos() {
        return votosNegativos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(!titulo.isBlank()){
        this.titulo = titulo;
        }
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public String toString() {
        return "Capitulo{" + "votosPositivos=" + votosPositivos + ", votosNegativos=" + votosNegativos + ", titulo=" + titulo + ", fechaEmision=" + fechaEmision ;
    }

    public void meGusta(boolean like) {

        if (like) {
            this.votosPositivos++;
        } else {
            this.votosNegativos++;

        }
    }

    public int calcularPuntuacion() {
        
        double ratio = (double)votosPositivos/(votosPositivos+votosNegativos);
        
        int puntuacion = (int) Math.round(ratio*10);
        
        return puntuacion;
    }
}
