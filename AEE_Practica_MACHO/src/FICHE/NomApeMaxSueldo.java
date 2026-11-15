package FICHE;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Felipe Jimenez
 */
public class NomApeMaxSueldo {

    public static void main(String[] args) {
        float sueldoMax;
        Empleado trabajador;
        DataInputStream fichero;
        boolean hayTrabajadores;

        fichero = null;
        sueldoMax = 0;
        hayTrabajadores = false;

        try {
            // Primera lectura: se busca el sueldo maximo
            fichero = new DataInputStream(new FileInputStream(new File("Ficheros", "FICHE.DAT")));
            trabajador = FicheDAO.leer(fichero);
            while (trabajador != null) {
                if (!hayTrabajadores || trabajador.getSueldo() > sueldoMax) {
                    sueldoMax = trabajador.getSueldo();
                    hayTrabajadores = true;
                }
                trabajador = FicheDAO.leer(fichero);
            }
            fichero.close();

            // Segunda lectura: se muestran todos los que igualan el maximo (puede haber empate)
            if (hayTrabajadores) {
                System.out.println("Sueldo superior: " + String.format("%.2f", sueldoMax));
                fichero = new DataInputStream(new FileInputStream(new File("Ficheros", "FICHE.DAT")));
                trabajador = FicheDAO.leer(fichero);
                while (trabajador != null) {
                    if (trabajador.getSueldo() == sueldoMax) {
                        System.out.println("Nombre y apellidos: " + trabajador.getNomApes());
                    }
                    trabajador = FicheDAO.leer(fichero);
                }
            } else {
                System.out.println("No hay trabajadores");
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException ioe) {
            System.out.println("Error E/S");
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error E/S al cerrar");
            }
        }
    }
}
