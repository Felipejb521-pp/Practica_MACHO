package FICHE;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Felipe Jimenez
 * 
 */
public class NomApeMaxSueldo {
    public static void main(String[] args) {
        DataInputStream entrada = null;
        Empleado emp;
        boolean fin = false;
        float sueldo, sueldoMax = -1;
        String nombresMax = "";
        
        try {
            entrada = new DataInputStream(new FileInputStream("Ficheros" + File.separator + "FICHE.DAT"));
                        
            while (!fin) {
                try {
                    emp = FicheDAO.leer(entrada);
                    sueldo = calcularSueldo(emp);
                                       
                    if (sueldo > sueldoMax) {
                        sueldoMax = sueldo;
                        nombresMax = emp.getNomApes();
                    } else if (sueldo == sueldoMax) {      
                        nombresMax += ", " + emp.getNomApes();
                    }          
                } catch (EOFException eof) {
                    fin = true;
                }
            }
            entrada.close();
            
            if (!nombresMax.isEmpty()) {
                System.out.println("Empleado(s) con el sueldo superior (" + sueldoMax + "€): " + nombresMax);
            } else {
                System.out.println("El fichero está vacío.");
            }
            
        } catch (IOException ioe) {
            System.err.println("Error E/S");
        }
    }

    private static float calcularSueldo(Empleado empleado) {
        float sueldoTotal = empleado.getSalario();
        
        sueldoTotal += (empleado.getTrienios() * 24); // 24€ por trienio
        
        if (empleado.getProvincia() == Provincia.JAEN || 
            empleado.getProvincia() == Provincia.HUELVA || 
            empleado.getProvincia() == Provincia.ALMERIA) {
            sueldoTotal += (empleado.getSalario() * 0.10f); // 10% si es Jaén, Huelva o Almería
        }
        
        if (empleado.getSexo() == Sexo.H) {
            sueldoTotal += 120; // 120€ si es hombre
        }
        
        return sueldoTotal;
    }
}