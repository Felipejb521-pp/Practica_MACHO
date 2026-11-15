
package FICHE;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Sergio García de Baya
 */
public class Mayorempleados {

    public static void main(String[] args) {
        DataInputStream entrada;
        Empleado trabajador;
        
        int[] contadorProvincias;
        int maximo;
        int i;

        entrada = null;
        contadorProvincias = new int[8];
        maximo = 0;

        try {
            entrada = new DataInputStream(new FileInputStream(new File("Ficheros", "FICHE.DAT")));

            trabajador = FicheDAO.leer(entrada);
            while (trabajador != null) {
                contadorProvincias[trabajador.getProvincia().getCodigo() - 1]++;
                trabajador = FicheDAO.leer(entrada);
            }

            for (i = 0; i < 8; i++) {
                if (contadorProvincias[i] > maximo) {
                    maximo = contadorProvincias[i];
                }
            }

            if (maximo > 0) {
                System.out.println("Provincia con más empleados");
                for (i = 0; i < 8; i++) {
                    if (contadorProvincias[i] == maximo) {
                        System.out.println(Provincia.buscador((byte) (i + 1)).getDescripcion()
                                + " - " + maximo + " empleados");
                    }
                }
            } else {
                System.out.println("No hay trabajadores");
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
