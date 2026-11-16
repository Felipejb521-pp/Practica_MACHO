/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FICHE;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Sergio García de Baya
 */
public class FicheDAO {
    

    public static Empleado leer(DataInputStream data) throws IOException {
        Empleado empleado = new Empleado();
        
        empleado.setNomApes(data.readUTF());        
        char letraSexo = data.readChar();
        empleado.setSexo(Sexo.valueOf(String.valueOf(letraSexo)));
        empleado.setSalario(data.readFloat());
        
        byte dia = data.readByte();
        byte mes = data.readByte();
        short anyo = data.readShort();
        empleado.setFechaIngreso(new Fecha(dia, mes, anyo));

        char letraTipo = data.readChar();
        empleado.setTipoEmpleado(TipoEmpleado.valueOf(String.valueOf(letraTipo)));        
        String nombreProv = data.readUTF();
        empleado.setProvincia(Provincia.valueOf(nombreProv));
            
        return empleado;
    }

    public static void escribir(Empleado registro, DataOutputStream data) throws IOException {
        data.writeUTF(registro.getNomApes());
        data.writeChar(registro.getSexo().name().charAt(0)); 
        data.writeFloat(registro.getSalario());
        data.writeByte(registro.getFechaIngreso().getDia());
        data.writeByte(registro.getFechaIngreso().getMes());
        data.writeShort(registro.getFechaIngreso().getAnyo());
        data.writeChar(registro.getTipoEmpleado().name().charAt(0)); 
        data.writeUTF(registro.getProvincia().name()); 
    }
}
