package FICHE;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Sergio García de Baya
 */
public class Diezanyosmas {

    public static void main(String[] args) {
        DataInputStream entrada;
        BufferedReader lector;
        Empleado trabajador;
        int total, contador10, contadorPagina;
        float porcentaje;

        entrada = null;
        lector = new BufferedReader(new InputStreamReader(System.in));
        total = 0;
        contador10 = 0;
        contadorPagina = 0;

        try {
            entrada = new DataInputStream(new FileInputStream(new File("Ficheros", "FICHE.DAT")));

            System.out.println("Con 10 años o más");

            trabajador = FicheDAO.leer(entrada);
            while (trabajador != null) {
                total++;

                if (trabajador.getFechaIngreso().getAntiguedad() >= 10) {
                    System.out.println(trabajador.getNomApes() + " - "
                            + trabajador.getFechaIngreso().getAntiguedad() + " años");
                    contador10++;
                    contadorPagina++;

                    if (contadorPagina == 10) {
                        System.out.println("Pulse intro para continuar...");
                        lector.readLine();
                        contadorPagina = 0;
                    }
                }
                trabajador = FicheDAO.leer(entrada);
            }

            if (total > 0) {
                porcentaje = (float) contador10 * 100 / total;
                System.out.println("Porcentaje: " + String.format("%.2f", porcentaje) + "%");
            } else {
                System.out.println("El fichero no tiene trabajadores");
            }
        } catch (IOException ioe) {
            System.out.println("Error E/S");
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
