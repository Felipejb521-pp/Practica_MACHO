package FICHE;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

/**
 *
 * @author Felipe Jimenez
 */
public class CreaLlenFich {
    
    public static void error(){
        System.out.println("Formato incorrecto , Introduzca de nuevo :");
    }
    
    public static void main(String[] args) {
        DataOutputStream dos;
        BufferedReader teclado;
        File accesoCarpeta;
        boolean valido;
        String nomApe;
        char sexo,continuar;
        float salario;
        short anyoIngreso;
        byte mesIngreso,diaIngreso;
        TipoEmpleado tipoEmpleado;
        Provincia provincia;
        Empleado trabajador;
        GregorianCalendar hoy;
        
        
        dos = null;
        teclado = new BufferedReader(new InputStreamReader(System.in));
        accesoCarpeta = new File("Ficheros");
        hoy = new GregorianCalendar();
        continuar = 'N';
        
        
        
        if(!accesoCarpeta.exists()){
            accesoCarpeta.mkdirs();
        }
        try{
            //true (append) permite repetidos
            dos = new DataOutputStream( new FileOutputStream("Ficheros\\FICHE.DAT", true));
        }catch(FileNotFoundException fnf){
            System.out.println("Fichero no encontrado");
        }
        
        if(dos != null){
            do{
                trabajador = new Empleado();
                nomApe = "";
                sexo = 'H';
                salario = 0;
                anyoIngreso = 0;
                mesIngreso = 0;
                diaIngreso = 0;
                tipoEmpleado = null;
                provincia = null;
                
                System.out.println("Nombre y Apellidos :");
                try{
                    do{
                        valido = true;
                        nomApe = teclado.readLine().toUpperCase();
                        
                        if(!nomApe.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")){
                            valido = false;
                            error();
                        }
                    }while(!valido);
                    trabajador.setNomApe(nomApe);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                }
                
                System.out.println("Sexo :");
                try{
                    do{
                        valido = true;
                        try{
                            sexo = teclado.readLine().charAt(0);
                            
                            if(sexo != 'H' && sexo != 'M'){
                                valido = false;
                            }
                        }catch(StringIndexOutOfBoundsException sioobe){
                            valido = false;
                        }
                        if(!valido){
                            error();
                        }
                    }while(!valido);
                    trabajador.setSexo(sexo);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                }
                
                System.out.println("Salario :");
                try{
                    do{
                        valido = true;
                        try{
                            salario = Float.parseFloat(teclado.readLine());
                            
                            if(salario < 0 || salario > 9999.99f){
                                valido = false;
                            }
                        }catch(NumberFormatException nfe){
                            valido = false;
                        }
                        if(!valido){
                            error();
                        }
                    }while(!valido);
                    trabajador.setSalario(salario);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                }
                
                System.out.println("Anyo Ingreso :");
                try{
                    do{
                        valido = true;
                        try{
                            anyoIngreso = Short.parseShort(teclado.readLine());

                            if(anyoIngreso>hoy.get(GregorianCalendar.YEAR)){
                                valido = false;
                            }
                        }catch(NumberFormatException nfe){
                            valido = false;
                        }
                        if(!valido){
                            error();
                        }
                     }while(!valido);
                        trabajador.setAnyoIngreso(anyoIngreso);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                }
                
                System.out.println("Mes Ingreso :");
                try{
                    do{ 
                        valido = true;
                        try{    
                            mesIngreso = Byte.parseByte(teclado.readLine());

                            if(mesIngreso < 1 || mesIngreso > 12 || (anyoIngreso == hoy.get(GregorianCalendar.YEAR) && mesIngreso > hoy.get(GregorianCalendar.MONTH) + 1)){
                                 valido = false;
                            }
                        }catch(NumberFormatException nfe){
                            valido = false;
                        } 
                        if(!valido){
                            error();
                        }
                    }while(!valido);
                        trabajador.setMesIngreso(mesIngreso);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                }
                
                
                System.out.println("Dia Ingreso :");
                try{
                    do{
                        valido = true;
                        try{
                            diaIngreso = Byte.parseByte(teclado.readLine());

                            if(diaIngreso < 1 || diaIngreso > new GregorianCalendar(trabajador.getAnyoIngreso(), trabajador.getMesIngreso() - 1, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH)
                                || (anyoIngreso == hoy.get(GregorianCalendar.YEAR) && mesIngreso == hoy.get(GregorianCalendar.MONTH) + 1 && diaIngreso > hoy.get(GregorianCalendar.DAY_OF_MONTH))){
                                 valido = false;
                            }
                        }catch(NumberFormatException nfe){
                            valido = false;
                        }
                        if(!valido){
                            error();
                        }
                    }while(!valido);
                    trabajador.setDiaIngreso(diaIngreso);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                }
                
                System.out.println("Tipo Empleado : A comision , A domicilio , Fijos");
                try{
                    do{
                        valido = true;
                        try{
                            //Formato del enum ACOMISION,ADOMICILIO,FIJOS
                            tipoEmpleado= TipoEmpleado.valueOf(
                                    teclado.readLine().trim().toUpperCase().replace(" ",""));
                        }catch(IllegalArgumentException iae){
                            valido = false;
                        }
                        if(!valido){
                            error();
                        }
                    }while(!valido);
                    trabajador.setTipoEmpleado(tipoEmpleado);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                } 
                
                System.out.println("Provincia(Andalucia) :");
                try{
                    do{
                        valido = true;
                        try{
                            //Formato del enum ALMERIA,CADIZ,CORDOBA,GRANADA,HUELVA,JAEN,MALAGA,SEVILLA
                            provincia = Provincia.valueOf(teclado.readLine().trim().toUpperCase().replace(" ",""));
                        }catch(IllegalArgumentException iae){
                            valido = false;
                        }
                        if(!valido){
                            error();
                        }
                    }while(!valido);
                    trabajador.setProvincia(provincia);
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                } 
                
                FicheDAO.escribir(dos, trabajador);
                
                System.out.println("¿Añadir otro trabajador? (S/N) :");
                try{
                    continuar = teclado.readLine().charAt(0);
                }catch(StringIndexOutOfBoundsException sioobe){
                    continuar = 'N';
                }catch(IOException ioe){
                    System.out.println("Error E/S");
                    continuar = 'N';
                }
            }while(continuar == 'S' || continuar == 's');
            
            try{
                dos.close();
            }catch(IOException ioe){
                System.out.println("Error E/S al cerrar");
            }
        }else{
            System.out.println("No se pudo guardar el trabajador");
        }
        
    }
}