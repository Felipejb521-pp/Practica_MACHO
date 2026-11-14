
package FICHE;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Sergio García de Baya
 */
public class Mayorempleados {

   
    public static void main(String[] args) {
        FileInputStream archivo = null;
        DataInputStream entrada = null;
        Empleado trabajador;
        
        int[] contadorProvincias = new int[8];// Utilizamos el array para poder contar los empleados por provincia
        int maximo;
        int i;
        
        try {
            archivo = new FileInputStream("Ficheros" + File.separator + "FICHE.DAT");
            entrada = new DataInputStream(archivo);
            
            while (entrada.available() > 0) {
                trabajador = FicheDAO.leer(entrada);
                
                
                contadorProvincias[trabajador.getProvincia().ordinal()]++;//.ordinal() devuelve la posición del enumerado (0 para ALMERIA, 1 para CADIZ...)
            }

            maximo = 0;
            for (i = 0; i < 8; i++) {
                if (contadorProvincias[i] > maximo) {
                    maximo = contadorProvincias[i];
                }
            }
            
            System.out.println("Provincia con más empleados");
            
            for (i = 0; i < 8; i++) {
                if (contadorProvincias[i] == maximo) {
                    System.out.println (Provincia.values()[i] + " - " + maximo + " empleados");
                }
            }
            
        } catch (IOException ioe) {
            System.err.println ("Error E/S");
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (archivo != null) archivo.close();
            } catch (IOException ioe) {
                System.err.println ("Error al cerrar");
            }
        }
    }
}