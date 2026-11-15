package FICHE;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Felipe Jimenez
 */
public class CreaLlenFich {

    public static void error() {
        System.out.println("Formato incorrecto , Introduzca de nuevo :");
    }

    public static String leerNombre(BufferedReader teclado) {
        String nomApe;
        boolean valido;

        do {
            try {
                nomApe = teclado.readLine().trim().toUpperCase();
            } catch (IOException ioe) {
                System.out.println("Error E/S");
                nomApe = "";
            }
            valido = nomApe.matches("[A-ZÁÉÍÓÚÑ ]+") && nomApe.contains(" ")
                    && nomApe.length() <= 30;
            if (!valido) {
                System.out.println("Debe anyadir al menos 1 apellido");
                error();
            }
        } while (!valido);

        return nomApe;
    }

    public static Sexo leerSexo(BufferedReader teclado) {
        String linea;
        Sexo sexo;
        boolean valido;

        sexo = null;
        do {
            try {
                linea = teclado.readLine().trim().toUpperCase();
            } catch (IOException ioe) {
                System.out.println("Error E/S");
                linea = "";
            }
            valido = linea.equals("H") || linea.equals("M");
            if (valido) {
                sexo = Sexo.buscador(linea.charAt(0));
            } else {
                error();
            }
        } while (!valido);

        return sexo;
    }

    public static float leerSalario(BufferedReader teclado) {
        float salario;
        boolean valido;

        salario = 0;
        do {
            try {
                salario = Float.parseFloat(teclado.readLine().trim());
                valido = salario >= 0 && salario <= 9999.99f;
            } catch (NumberFormatException nfe) {
                valido = false;
            } catch (IOException ioe) {
                System.out.println("Error E/S");
                valido = false;
            }
            if (!valido) {
                error();
            }
        } while (!valido);

        return salario;
    }

    public static Fecha leerFecha(BufferedReader teclado) {
        Fecha fecha;
        String[] partes;
        byte dia, mes;
        short anyo;
        boolean valido;

        fecha = null;
        do {
            valido = false;
            try {
                partes = teclado.readLine().split("/");
                if (partes.length == 3) {
                    // parseByte y parseShort fallan si el numero no cabe: no hay truncado
                    dia = Byte.parseByte(partes[0].trim());
                    mes = Byte.parseByte(partes[1].trim());
                    anyo = Short.parseShort(partes[2].trim());

                    if (Fecha.fechaCorrecta(dia, mes, anyo)) {
                        fecha = new Fecha(dia, mes, anyo);
                        valido = fecha.esAnteriorHoy();
                    }
                }
            } catch (NumberFormatException nfe) {
                valido = false;
            } catch (IOException ioe) {
                System.out.println("Error E/S");
                valido = false;
            }
            if (!valido) {
                error();
            }
        } while (!valido);

        return fecha;
    }

    public static TipoEmpleado leerTipo(BufferedReader teclado) {
        String linea;
        TipoEmpleado tipo;
        boolean valido;

        tipo = null;
        do {
            try {
                linea = teclado.readLine().trim().toUpperCase();
            } catch (IOException ioe) {
                System.out.println("Error E/S");
                linea = "";
            }
            valido = linea.equals("C") || linea.equals("F") || linea.equals("D");
            if (valido) {
                tipo = TipoEmpleado.buscador(linea.charAt(0));
            } else {
                error();
            }
        } while (!valido);

        return tipo;
    }

    public static Provincia leerProvincia(BufferedReader teclado) {
        Provincia provincia;

        provincia = null;
        do {
            try {
                provincia = Provincia.buscador(Byte.parseByte(teclado.readLine().trim()));
            } catch (NumberFormatException nfe) {
                provincia = null;
            } catch (IOException ioe) {
                System.out.println("Error E/S");
                provincia = null;
            }
            if (provincia == null) {
                error();
            }
        } while (provincia == null);

        return provincia;
    }

    public static void main(String[] args) {
        DataOutputStream dos;
        BufferedReader teclado;
        File accesoCarpeta;
        String nomApe;
        Sexo sexo;
        float salario;
        Fecha fechaIngreso;
        TipoEmpleado tipoEmpleado;
        Provincia provincia;
        Empleado empleado;
        char continuar;

        dos = null;
        teclado = new BufferedReader(new InputStreamReader(System.in));
        accesoCarpeta = new File("Ficheros");

        if (!accesoCarpeta.exists()) {
            accesoCarpeta.mkdirs();
        }
        try {
            // sin true: cada ejecucion crea el fichero de nuevo
            dos = new DataOutputStream(new FileOutputStream(new File(accesoCarpeta, "FICHE.DAT")));
        } catch (FileNotFoundException fnf) {
            System.out.println("Fichero no encontrado");
        }

        if (dos != null) {
            do {
                System.out.println("Nombre Apellidos :");
                nomApe = leerNombre(teclado);
                System.out.println("Sexo(H/M) :");
                sexo = leerSexo(teclado);
                System.out.println("Salario :");
                salario = leerSalario(teclado);
                System.out.println("Fecha de ingreso (dd/mm/AAAA) :");
                fechaIngreso = leerFecha(teclado);
                System.out.println("Tipo Empleado : C (A comision), F (Fijos), D (A domicilio)");
                tipoEmpleado = leerTipo(teclado);
                System.out.println("Provincia : 1 Almeria, 2 Cadiz, 3 Cordoba, 4 Granada,"
                        + " 5 Huelva, 6 Jaen, 7 Malaga, 8 Sevilla");
                provincia = leerProvincia(teclado);
                empleado = new Empleado(nomApe, sexo, salario, fechaIngreso, tipoEmpleado, provincia);
                FicheDAO.escribir(dos, empleado);
                System.out.println("¿Añadir otro trabajador? (S/N) :");
                try {
                    continuar = teclado.readLine().charAt(0);
                } catch (StringIndexOutOfBoundsException sioobe) {
                    continuar = 'N';
                } catch (IOException ioe) {
                    System.out.println("Error E/S");
                    continuar = 'N';
                }
            } while (continuar == 'S' || continuar == 's');

            try {
                dos.close();
            } catch (IOException ioe) {
                System.out.println("Error E/S al cerrar");
            }
        } else {
            System.out.println("No se pudo guardar el trabajador");
        }
    }
}