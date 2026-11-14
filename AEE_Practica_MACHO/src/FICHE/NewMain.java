//Este codigo no se añade en el trabajo es solo para saber si se estan guardando los datos en el fichero
package FICHE;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Sergio García de Baya
 */
public class NewMain {
    public static void main(String[] args) {
        FileInputStream archivo = null;
        DataInputStream entrada = null;
        Empleado trabajador;
        
        try {
            archivo = new FileInputStream("Ficheros" + File.separator + "FICHE.DAT");
            entrada = new DataInputStream(archivo);
            
            System.out.println ("=== TRABAJADORES ===");
            
            while (entrada.available() > 0) {
                trabajador = FicheDAO.leer(entrada);
                
                System.out.println ("Nombre: " + trabajador.getNomApe());
                System.out.println ("Sexo: " + trabajador.getSexo());
                System.out.println ("Salario: " + trabajador.getSalario());
                System.out.println ("Fecha: " + trabajador.getDiaIngreso() + "/" + trabajador.getMesIngreso() + "/" + trabajador.getAnyoIngreso());
                System.out.println ("Tipo: " + trabajador.getTipoEmpleado());
                System.out.println ("Provincia: " + trabajador.getProvincia());
                System.out.println ("---------------------------------");
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