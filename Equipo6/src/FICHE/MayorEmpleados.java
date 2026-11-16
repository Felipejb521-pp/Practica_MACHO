package FICHE;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sergio García de Baya
 */
public class MayorEmpleados {
    public static void main(String[] args) {
        DataInputStream entrada = null;
        Empleado empleado;
        boolean fin = false;
        int[] contador = new int[8];
        int maximo = 0;
        int i;
        
        try {
            entrada = new DataInputStream(new FileInputStream("Ficheros" + File.separator + "FICHE.DAT"));
            
            while (!fin) {
                try {
                    empleado = FicheDAO.leer(entrada);
                    contador[empleado.getProvincia().ordinal()]++; // Sumamos 1 a su provincia
                } catch (EOFException eof) {
                    fin = true;
                }
            }
            entrada.close();
            
            for (i = 0; i < 8; i++) {
                if (contador[i] > maximo) {
                    maximo = contador[i];
                }
            }
            
            System.out.println ("Provincia(s) con más empleados:");
            for (i = 0; i < 8; i++) {
                if (contador[i] == maximo) {
                    System.out.println (Provincia.values()[i] + " - " + maximo + " empleados");
                }
            }
            
        } catch (IOException ioe) {
            System.err.println ("Error E/S");
        }
    }
}