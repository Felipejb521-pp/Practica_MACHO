package FICHE;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sergio García de Baya
 */
public class NomApeMaxSueldo {
    public static void main(String[] args) {
        DataInputStream entrada = null;
        Empleados emp;
        boolean fin = false;
        float sueldo, sueldoMax = 0;
        String nombreMax = "";
        
        try {
            entrada = new DataInputStream(new FileInputStream("Ficheros" + File.separator + "FICHE.DAT"));
                        
            while (!fin) {
                try {
                    emp = FicheDAO.leer(entrada);
                    sueldo = calcularSueldo(emp);
                    
                    System.out.println ("Nombre: " + emp.getNomApes() + " | Sueldo: " + sueldo);
                    
                    if (nombreMax.equals("") || sueldo > sueldoMax) {
                        sueldoMax = sueldo;
                        nombreMax = emp.getNomApes();
                    }
                    
                } catch (EOFException eof) {
                    fin = true;
                }
            }
            entrada.close();
            
            System.out.println ("Empleado con más sueldo: " + nombreMax + " (" + sueldoMax + "€)");
            
        } catch (IOException ioe) {
            System.err.println ("Error E/S");
        }
    }

    private static float calcularSueldo(Empleados empleado) {
        float sueldoTotal;
        
        sueldoTotal = empleado.getSalario();
        
        sueldoTotal = sueldoTotal + (empleado.getTrienios() * 24); //24€ por trienio
        
        if (empleado.getProvincia() == Provincia.JAEN || empleado.getProvincia() == Provincia.HUELVA || empleado.getProvincia() == Provincia.ALMERIA) {
            sueldoTotal = sueldoTotal + (empleado.getSalario() * 0.10f); //10% si es Jaén, Huelva o Almería
        }
        
        if (empleado.getSexo() == Sexo.H) {
            sueldoTotal = sueldoTotal + 120; //120€ si es hombre
        }
        
        return sueldoTotal;
    }
}