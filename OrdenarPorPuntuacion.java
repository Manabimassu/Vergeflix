/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vergeflix;

import java.util.Comparator;


/**
 *
 * @author Manuel
 */
 public class OrdenarPorPuntuacion implements Comparator<Media> {

    @Override
    public int compare(Media o1, Media o2) {
        return Integer.compare(o1.calcularPuntuacion(), o2.calcularPuntuacion());
    }

}

