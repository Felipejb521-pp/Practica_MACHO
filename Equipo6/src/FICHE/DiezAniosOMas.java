package FICHE;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Sergio García de Baya
 */
public class DiezAniosOMas {
    public static void main(String[] args) {
        DataInputStream entrada = null;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        Empleados empleado;
        boolean fin = false;
        int total = 0, contador10 = 0, contadorPagina = 0;
        String linea;
        
        try {
            entrada = new DataInputStream(new FileInputStream("Ficheros" + File.separator + "FICHE.DAT"));
            
            while (!fin) {
                try {
                    empleado = FicheDAO.leer(entrada);
                    total++;
                    
                    if (empleado.getFechaIngreso().getAntiguedad() >= 10) {
                        System.out.println (empleado.getNomApes() + " - " + empleado.getFechaIngreso().getAntiguedad() + " años");
                        contador10++;
                        contadorPagina++;
                        
                        if (contadorPagina == 10) {
                            System.out.println ("Pulse intro para continuar");
                            linea = teclado.readLine();
                            contadorPagina = 0;
                        }
                    }
                } catch (EOFException eof) {
                    fin = true;
                }
            }
            
            if (total > 0) {
                System.out.println ("Porcentaje: " + (contador10 * 100 / total) + "%");
            }
            entrada.close();
        } catch (IOException ioe) {
            System.err.println ("Error E/S");
        }
    }
}