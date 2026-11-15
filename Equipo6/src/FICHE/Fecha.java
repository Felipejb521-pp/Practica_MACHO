package FICHE;

import java.util.GregorianCalendar;

/**
 * @author Sergio García de Baya
 */
public class Fecha {

    private short anyo;
    private byte mes;
    private byte dia;

    public Fecha(byte dia, byte mes, short anyo) {
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
    }

    public boolean fechaCorrecta(byte dia, byte mes, short anyo) {
        boolean fecha;
        GregorianCalendar calendario;
        int diaReal, mesReal, anyoReal;

        calendario = new GregorianCalendar(anyo, mes - 1, dia);//se pone mes -1 porque en gregoriancalendar empieza en 0

        diaReal = calendario.get(GregorianCalendar.DAY_OF_MONTH);
        mesReal = calendario.get(GregorianCalendar.MONTH) + 1;
        anyoReal = calendario.get(GregorianCalendar.YEAR);

        if (diaReal == dia && mesReal == mes && anyoReal == anyo) {
            fecha = true; //Indica que la fecha que mete el usuario no existe
        }else{
            fecha = false; //Indica que la fecha que mete el usuario no existe
        }
    
        return fecha;
    }

    public byte getDia() {
        return dia;
    }

    public byte getMes() {
        return mes;
    }

    public short getAnyo() {
        return anyo;
    }

    public byte getAntiguedad() {
        byte anios;
        GregorianCalendar hoy;
        int anyoActual;

        hoy = new GregorianCalendar();
        anyoActual = hoy.get(GregorianCalendar.YEAR);
    
        anios = (byte) (anyoActual - anyo);

        return anios;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anyo;
    }
}