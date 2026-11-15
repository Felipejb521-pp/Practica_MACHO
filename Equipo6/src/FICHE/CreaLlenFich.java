package FICHE;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sergio García de Baya
 */
public class CreaLlenFich {
    public static void main(String[] args) {
        DataOutputStream dos = null;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        Empleados empleado;
        char continuar;
        char letraSexo;
        char letraTipo;
        byte dia, mes;
        short anyo;
        String fechaStr;
        String[] partes;
        Fecha fechaAux;
        String entradaProvincia;
        boolean fechaValida, provinciaValida, nombreValido;
        String nombreAux;
        
        try {
            dos = new DataOutputStream(new FileOutputStream("Ficheros" + File.separator + "FICHE.DAT", true));
            
            do {
                empleado = new Empleados();
                
                nombreValido = false;
                do {
                    System.out.println ("Nombre y apellidos:");
                    nombreAux = teclado.readLine();
                    
                    if (nombreAux.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        empleado.setNomApes(nombreAux);
                        nombreValido = true;
                    } else {
                        System.err.println ("Error: El nombre solo puede contener letras y espacios");
                    }
                } while (!nombreValido);
                
                System.out.println ("Sexo (M/H):");
                letraSexo = teclado.readLine().toUpperCase().charAt(0);
                empleado.setSexo(Sexo.valueOf(String.valueOf(letraSexo)));
                
                System.out.println ("Salario:");
                empleado.setSalario(Float.parseFloat(teclado.readLine()));
                
                fechaValida = false;
                do {
                    System.out.println ("Fecha de ingreso (dd/mm/aaaa):");
                    fechaStr = teclado.readLine();
                    partes = fechaStr.split("/");

                    if (partes.length == 3) {
                        try {
                            dia = Byte.parseByte(partes[0]);
                            mes = Byte.parseByte(partes[1]);
                            anyo = Short.parseShort(partes[2]);
                            
                            fechaAux = new Fecha(dia, mes, anyo);
                            
                            if (fechaAux.fechaCorrecta(dia, mes, anyo)) {
                                empleado.setFechaIngreso(fechaAux);
                                fechaValida = true;
                            } else {
                                System.err.println ("Error: La fecha no existe");
                            }
                        } catch (NumberFormatException nfe) {
                            System.err.println ("Error: Los datos deben ser números");
                        }
                    } else {
                        System.err.println ("Error: El formato de fecha debe ser dd/mm/aaaa");
                    }
                } while (!fechaValida);
                
                System.out.println ("Tipo Empleado (C (A Comision)/F (Fijo)/D (A Domicilio)):");
                letraTipo = teclado.readLine().toUpperCase().charAt(0);
                empleado.setTipoEmpleado(TipoEmpleado.valueOf(String.valueOf(letraTipo)));
                
                provinciaValida = false;
                do {
                    System.out.println ("Provincia (Nombre o número del 1 al 8):");
                    entradaProvincia = teclado.readLine().toUpperCase();
                    
                    try {
                        int numProv = Integer.parseInt(entradaProvincia);
                        if (numProv >= 1 && numProv <= 8) {
                            empleado.setProvincia(Provincia.values()[numProv - 1]);
                            provinciaValida = true;
                        } else {
                            System.err.println ("Error: El número de provincia debe estar entre 1 y 8");
                        }
                    } catch (NumberFormatException nfe) {
                        try {
                            empleado.setProvincia(Provincia.valueOf(entradaProvincia));
                            provinciaValida = true;
                        } catch (IllegalArgumentException iae) {
                            System.err.println ("Error: Provincia no válida. Escribe el nombre correcto o un número del 1 al 8.");
                        }
                    }
                } while (!provinciaValida);
                
                FicheDAO.escribir(empleado, dos);
                
                System.out.println ("¿Añadir otro? (S/N): ");
                continuar = teclado.readLine().charAt(0);
            } while (continuar == 'S' || continuar == 's');
            
            dos.close(); 
            
        } catch (IOException ioe) {
            System.err.println ("Error E/S");
        } catch (IllegalArgumentException iae) {
            System.err.println ("Error: Has introducido un valor de enum no válido");
        }
    }
}