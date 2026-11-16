package FICHE;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Felipe Jimenez
 */
public class ListadoNombreSueldo {
    public static void main(String[] args) {
        DataInputStream entrada = null;
        Empleado emp;
        boolean fin = false;
        float sueldo;
        int contador = 0;

        try {
            entrada = new DataInputStream(new FileInputStream("Ficheros" + File.separator + "FICHE.DAT"));

            while (!fin) {
                try {
                    emp = FicheDAO.leer(entrada);
                    sueldo = calcularSueldo(emp);
                    contador++;

                    System.out.println("Nombre: " + emp.getNomApes() + " | Sueldo: " + sueldo + "€");

                } catch (EOFException eof) {
                    fin = true;
                }
            }
            entrada.close();

            if (contador == 0) {
                System.out.println("El fichero está vacío, no hay empleados para mostrar.");
            }

        } catch (IOException ioe) {
            System.err.println("Error E/S");
        }
    }

    private static float calcularSueldo(Empleado empleado) {
        float sueldoTotal;

        sueldoTotal = empleado.getSalario();

        sueldoTotal = sueldoTotal + (empleado.getTrienios() * 24); // 24€ por trienio

        if (empleado.getProvincia() == Provincia.JAEN || 
            empleado.getProvincia() == Provincia.HUELVA || 
            empleado.getProvincia() == Provincia.ALMERIA) {
            sueldoTotal = sueldoTotal + (empleado.getSalario() * 0.10f); // 10% si es Jaén, Huelva o Almería
        }

        if (empleado.getSexo() == Sexo.H) {
            sueldoTotal = sueldoTotal + 120; // 120€ si es hombre
        }

        return sueldoTotal;
    }
}