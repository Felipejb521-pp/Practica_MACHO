package FICHE;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Felipe Jimenez
 */
public class NomApeMaxSueldo {

    public static void main(String[] args) {
        String nombre;
        float sueldoMax, sueldo;
        boolean finFichero;
        Empleado trabajador;
        DataInputStream fichero;

        fichero = null;
        nombre = null;
        sueldoMax = 0;
        finFichero = false;

        try{
            fichero = new DataInputStream(new FileInputStream("Ficheros\\FICHE.DAT"));

            while(!finFichero){
                trabajador = FicheDAO.leer(fichero);

                if(trabajador.getNomApe() == null){
                    finFichero = true;
                }else{
                    sueldo = NomSueldo.calcular(trabajador);

                    if(nombre == null || sueldo > sueldoMax){
                        nombre = trabajador.getNomApe();
                        sueldoMax = sueldo;
                    }
                }
            }

        }catch(FileNotFoundException fnf){
            System.out.println("No se encuentra el fichero");
        }finally{
            try{
                if(fichero != null){
                    fichero.close();
                }
            }catch(IOException ioe){
                System.out.println("Error E/S al cerrar");
            }
        }

        if(nombre == null){
            System.out.println("No hay trabajadores");
        }else{
            System.out.println("Nombre y apellidos: " + nombre + " Sueldo: " + sueldoMax);
        }
    }
}