package FICHE;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Felipe Jimenez
 */
public class CreaLlenFich {
    public static void main(String[] args) {
        DataOutputStream dos = null;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        Empleado empleado;
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
        String nomApe;
        File carpeta = new File("Ficheros");
        
        try {
            
            if (!carpeta.exists()) {
                carpeta.mkdirs(); 
            }
            dos = new DataOutputStream(new FileOutputStream("Ficheros" + File.separator + "FICHE.DAT", true));
            
            do {
                empleado = new Empleado();         
                nombreValido = false;
                do {
                    System.out.println("Nombre y apellidos:");
                    nomApe = teclado.readLine().trim();

                    if (nomApe.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")
                            && nomApe.contains(" ")
                            && nomApe.length() <= 30) {
                        
                        empleado.setNomApes(nomApe);
                        nombreValido = true;
                    } else {
                        if (nomApe.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                            System.out.println("Debe anyadir al menos 1 apellido");
                        } else {
                            System.out.println("Error: solo letras y espacios, maximo 30 caracteres");
                        }
                    }
                } while (!nombreValido);
                              
                boolean sexoValido = false;
                do {
                    System.out.println("Sexo (M/H):");
                    String entradaSexo = teclado.readLine().trim().toUpperCase();

                    if (entradaSexo.length() == 1) {
                        try {
                            empleado.setSexo(Sexo.valueOf(entradaSexo));
                            sexoValido = true;
                        } catch (IllegalArgumentException iae) {
                            System.out.println("Error: Sexo no válido. Introduce M o H.");
                        }
                    } else {
                        System.out.println("Error: Debes introducir M o H.");
                    }
                } while (!sexoValido);
                
                System.out.println ("Salario:");
                empleado.setSalario(Float.parseFloat(teclado.readLine()));
                
                fechaValida = false;
                do {
                    System.out.println ("Fecha de ingreso (dd/mm/aaaa):");
                    fechaStr = teclado.readLine();
                    partes = fechaStr.split("/");

                    if (partes.length == 3 && partes[2].length()==4) {
                        try {
                            dia = Byte.parseByte(partes[0]);
                            mes = Byte.parseByte(partes[1]);
                            anyo = Short.parseShort(partes[2]);
                            
                            fechaAux = new Fecha(dia, mes, anyo);
                            
                            if (fechaAux.fechaCorrecta(dia, mes, anyo)) {
                                empleado.setFechaIngreso(fechaAux);
                                fechaValida = true;
                            } else {
                                System.out.println ("Error: La fecha no existe");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println ("Error: Los datos deben ser números");
                        }
                    } else {
                        System.out.println ("Error: El formato de fecha debe ser dd/mm/aaaa");
                    }
                } while (!fechaValida);
                
                boolean tipoValido = false;
                do {
                    System.out.println("Tipo Empleado :\nC :A Comision\nF :Fijo\nD :A Domicilio");
                    String entradaTipo = teclado.readLine().trim().toUpperCase();

                    if (entradaTipo.length() == 1) {
                        try {
                            empleado.setTipoEmpleado(TipoEmpleado.valueOf(entradaTipo));
                            tipoValido = true;
                        } catch (IllegalArgumentException iae) {
                            System.out.println("Error: Tipo de empleado no válido. Introduce C, F o D.");
                        }
                    } else {
                        System.out.println("Error: Debes introducir una sola letra (C, F o D).");
                    }
                } while (!tipoValido);
                
                provinciaValida = false;
                do {
                    System.out.println ("Provincia :"+"\n"+"1 :Almeria"+"\n"+"2 :Cadiz"+"\n"+"3 :Cordoba"+"\n"+"4 :Granada"+"\n"+"5 :Huelva"+"\n"+"7 :Malaga"+"\n"+"8 :Sevilla");
                    entradaProvincia = teclado.readLine().toUpperCase();
                    
                    try {
                        int numProv = Integer.parseInt(entradaProvincia);
                        if (numProv >= 1 && numProv <= 8) {
                            empleado.setProvincia(Provincia.values()[numProv - 1]);
                            provinciaValida = true;
                        } else {
                            System.out.println ("Error: El número de provincia debe estar entre 1 y 8");
                        }
                    } catch (NumberFormatException nfe) {
                        try {
                            empleado.setProvincia(Provincia.valueOf(entradaProvincia));
                            provinciaValida = true;
                        } catch (IllegalArgumentException iae) {
                            System.out.println ("Error: Provincia no válida. Escribe el nombre correcto o un número del 1 al 8.");
                        }
                    }
                } while (!provinciaValida);
                
                FicheDAO.escribir(empleado, dos);
                
                System.out.println ("¿Añadir otro? (S/N): ");
                continuar = teclado.readLine().charAt(0);
            } while (continuar == 'S' || continuar == 's');
            
            dos.close(); 
            
        } catch (IOException ioe) {
            System.out.println ("Error E/S");
        } catch (IllegalArgumentException iae) {
            System.out.println ("Error: Has introducido un valor de enum no válido");
        }
    }
}