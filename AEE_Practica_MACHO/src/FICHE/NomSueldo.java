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
public class NomSueldo {

    public static void main(String[] args) {
        int total;
        Empleado trabajador;
        DataInputStream fichero;

        fichero = null;
        total = 0;

        try {
            fichero = new DataInputStream(new FileInputStream(new File("Ficheros", "FICHE.DAT")));

            trabajador = FicheDAO.leer(fichero);
            while (trabajador != null) {
                System.out.println("Nombre y apellidos: " + trabajador.getNomApes()
                        + " Sueldo: " + String.format("%.2f", trabajador.getSueldo()));
                total++;
                trabajador = FicheDAO.leer(fichero);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el fichero");
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error E/S al cerrar");
            }
        }

        System.out.println("Trabajadores leidos: " + total);
    }
}
