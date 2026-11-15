//Este codigo no se añade en el trabajo es solo para saber si se estan guardando los datos en el fichero
package FICHE;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Sergio García de Baya
 */
public class NewMain {
    public static void main(String[] args) {
        DataInputStream entrada;
        Empleado trabajador;

        entrada = null;

        try {
            entrada = new DataInputStream(new FileInputStream(new File("Ficheros", "FICHE.DAT")));

            System.out.println("=== TRABAJADORES ===");

            trabajador = FicheDAO.leer(entrada);
            while (trabajador != null) {
                System.out.println("Nombre: " + trabajador.getNomApes());
                System.out.println("Sexo: " + trabajador.getSexo().getDescripcion());
                System.out.println("Salario: " + trabajador.getSalario());
                System.out.println("Fecha: " + trabajador.getFechaIngreso());
                System.out.println("Tipo: " + trabajador.getTipoEmpleado().getDescripcion());
                System.out.println("Provincia: " + trabajador.getProvincia().getDescripcion());
                System.out.println("---------------------------------");
                trabajador = FicheDAO.leer(entrada);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("No se encuentra el fichero");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error E/S al cerrar");
            }
        }
    }
}
