/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vergeflix;

import Funciones.FuncionesVergeflix;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Media;

/**
 *
 * @author Manuel Rodríguez Jareño
 */
public class Vergeflix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        try {
            System.setOut(new PrintStream(System.out, true, "utf-8"));                    

            ArrayList<Media> catalogo = new ArrayList<>();            

            FuncionesVergeflix.LlenarDatos(catalogo);                   
                    
            FuncionesVergeflix.menuPrincipal(catalogo);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Vergeflix.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
