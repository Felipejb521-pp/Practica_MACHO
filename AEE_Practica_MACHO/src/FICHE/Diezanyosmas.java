package FICHE;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

/**
 *
 * @author Sergio García de Baya
 */
public class Diezanyosmas {

    public static void main(String[] args) {
        FileInputStream archivo = null;
        DataInputStream entrada = null;
        BufferedReader lector = null;
        Empleado trabajador;
        GregorianCalendar hoy;
        int anyoActual, anyosTrabajados, contador10, total, contadorPagina;
        boolean finFichero;
        String linea;

        contador10 = 0;
        total = 0;
        contadorPagina = 0;
        finFichero = false;

        try {
            archivo = new FileInputStream("Ficheros" + File.separator + "FICHE.DAT");
            entrada = new DataInputStream(archivo);
            lector = new BufferedReader(new InputStreamReader(System.in));

            hoy = new GregorianCalendar();
            anyoActual = hoy.get(GregorianCalendar.YEAR);

            System.out.println("Con 10 años o más");

            while (!finFichero) {
                trabajador = FicheDAO.leer(entrada);

                if (trabajador.getNomApe() == null) {
                    finFichero = true;
                } else {
                    total++;
                    anyosTrabajados = anyoActual - trabajador.getAnyoIngreso();

                    if (anyosTrabajados >= 10) {
                        System.out.println(trabajador.getNomApe() + " - " + anyosTrabajados + " años");
                        contador10++;
                        contadorPagina++;

                        if (contadorPagina == 10) {
                            System.out.println("Pulse intro para continuar...");
                            linea = lector.readLine();
                            contadorPagina = 0;
                        }
                    }
                }
            }

            if (total > 0) {
                System.out.println("Porcentaje: " + (contador10 * 100 / total) + "%");
            }
        } catch (IOException ioe) {
            System.err.println("Error E/S");
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (archivo != null) archivo.close();
                if (lector != null) lector.close();
            } catch (IOException ioe) {
                System.err.println("Error al cerrar");
            }
        }
    }
}