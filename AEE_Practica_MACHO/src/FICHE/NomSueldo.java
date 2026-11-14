package FICHE;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 *
 * @author Felipe Jimenez
 */
public class NomSueldo {

    public static float calcular(Empleado trabajador) {
        float sueldo, complemento;
        byte trienio, anyo;
        GregorianCalendar hoy, ingreso;

        hoy = new GregorianCalendar();
        complemento = 0;

        ingreso = new GregorianCalendar(trabajador.getAnyoIngreso(),
                trabajador.getMesIngreso() - 1,
                trabajador.getDiaIngreso());

        anyo = (byte)(hoy.get(GregorianCalendar.YEAR) - ingreso.get(GregorianCalendar.YEAR));

        if(hoy.get(GregorianCalendar.MONTH) < ingreso.get(GregorianCalendar.MONTH)
                || (hoy.get(GregorianCalendar.MONTH) == ingreso.get(GregorianCalendar.MONTH)
                && hoy.get(GregorianCalendar.DAY_OF_MONTH) < ingreso.get(GregorianCalendar.DAY_OF_MONTH))){

            anyo--;
        }

        trienio = (byte)(anyo / 3);
        complemento += trienio * 24;

        if(trabajador.getProvincia() == Provincia.JAEN
                || trabajador.getProvincia() == Provincia.HUELVA
                || trabajador.getProvincia() == Provincia.ALMERIA){

            complemento += trabajador.getSalario() * 0.1f;
        }

        if(trabajador.getSexo() == 'H'){
            complemento += 120;
        }

        sueldo = trabajador.getSalario() + complemento;

        return sueldo;
    }

    public static void main(String[] args) {
        float sueldo;
        int total;
        boolean finFichero;
        Empleado trabajador;
        DataInputStream fichero;

        fichero = null;
        total = 0;
        finFichero = false;

        try{
            fichero = new DataInputStream(new FileInputStream("Ficheros\\FICHE.DAT"));

            while(!finFichero){
                trabajador = FicheDAO.leer(fichero);

                if(trabajador.getNomApe() == null){
                    finFichero = true;
                }else{
                    sueldo = calcular(trabajador);

                    System.out.println("Nombre y apellidos: " + trabajador.getNomApe() + " Sueldo: " + sueldo);
                    total++;
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

        System.out.println("Trabajadores leidos: " + total);
    }
}