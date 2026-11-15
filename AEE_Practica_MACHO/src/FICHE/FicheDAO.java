package FICHE;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 *
 * @author Sergio García de Baya
 */
public class FicheDAO {

    public static final byte TAM = 73;
    private static final byte LONG_NOMBRE = 30;

    private static String leerCaracteres(DataInputStream data, byte longitud) {
        String cadena = null;
        char[] letras;

        try {
            letras = new char[longitud];
            for (int i = 0; i < longitud; i++) {
                letras[i] = data.readChar();
            }
            cadena = new String(letras).trim();
        } catch (EOFException eof) {
            // fin de fichero: se devuelve null sin mensaje
        } catch (IOException ioe) {
            System.out.println("Error E/S");
        }

        return cadena;
    }

    private static void escribirCaracteres(DataOutputStream data, String cadena, byte longitud) {
        StringBuilder sb = new StringBuilder(cadena);

        while (sb.length() < longitud) {
            sb.append(' ');
        }
        sb.setLength(longitud);

        try {
            data.writeChars(sb.toString());
        } catch (IOException ioe) {
            System.out.println("Error E/S");
        }
    }

    public static Empleado leer(DataInputStream data) {
        Empleado empleado = null;
        String nomApes;
        Sexo sexo;
        float salario;
        short anyo;
        byte mes, dia;
        TipoEmpleado tipo;
        Provincia provincia;

        nomApes = leerCaracteres(data, LONG_NOMBRE);

        // si el nombre es null, ya no quedan registros (o hubo error)
        if (nomApes != null) {
            try {
                sexo = Sexo.buscador(data.readChar());
                salario = data.readFloat();
                anyo = data.readShort();
                mes = data.readByte();
                dia = data.readByte();
                tipo = TipoEmpleado.buscador(data.readChar());
                provincia = Provincia.buscador(data.readByte());

                empleado = new Empleado(nomApes, sexo, salario, new Fecha(dia, mes, anyo), tipo, provincia);
            } catch (EOFException eof) {
                // registro incompleto: se devuelve null sin mensaje
            } catch (IOException ioe) {
                System.out.println("Error E/S");
            }
        }

        return empleado;
    }

    public static void escribir(DataOutputStream data, Empleado registro) {
        try {
            escribirCaracteres(data, registro.getNomApes(), LONG_NOMBRE);
            data.writeChar(registro.getSexo().name().charAt(0));
            data.writeFloat(registro.getSalario());
            data.writeShort(registro.getFechaIngreso().getAnyo());
            data.writeByte(registro.getFechaIngreso().getMes());
            data.writeByte(registro.getFechaIngreso().getDia());
            data.writeChar(registro.getTipoEmpleado().name().charAt(0));
            data.writeByte(registro.getProvincia().getCodigo());
        } catch (IOException ioe) {
            System.out.println("Error E/S");
        }
    }
}
