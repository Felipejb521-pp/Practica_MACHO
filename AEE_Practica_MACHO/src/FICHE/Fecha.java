
package FICHE;

import java.util.GregorianCalendar;

/**
 *
 * @author Felipe Jimenez Brioso
 */
public class Fecha {
    private short anyo;
    private byte mes,dia;
    
   public Fecha(short anyo,byte mes,byte dia){
       this.anyo=anyo;
       this.mes=mes;
       this.dia=dia;
   }


   
   
   public static boolean fechaCorrecta(short anyo, byte mes, byte dia) {
    boolean esCorrecto;
    int diasMes;

    esCorrecto = false;

    if (anyo >= 1 && anyo <= 9999 && mes >= 1 && mes <= 12 && dia >= 1) {
        if (mes == 2) {
            if (esBisiesto(anyo)) {
                diasMes = 29;
            } else {
                diasMes = 28;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            diasMes = 30;
        } else {
            diasMes = 31;
        }
        esCorrecto = dia <= diasMes;
    }

    return esCorrecto;
   }
   
   public static boolean esBisiesto(short anyo){
       boolean esCorrecto;
       
       esCorrecto=false;
       if((anyo % 4 == 0 && anyo % 100 != 0) || anyo % 400 ==0){
           esCorrecto=true;
       }
       
       return esCorrecto;
   }
   
   public boolean esAnteriorHoy() {
    GregorianCalendar hoy;
    int hoyNum, fechaNum;
    boolean esCorrecto;

    hoy = new GregorianCalendar();
    hoyNum = hoy.get(GregorianCalendar.YEAR) * 10000
           + (hoy.get(GregorianCalendar.MONTH) + 1) * 100
           + hoy.get(GregorianCalendar.DAY_OF_MONTH);
    fechaNum = anyo * 10000 + mes * 100 + dia;
    esCorrecto = fechaNum < hoyNum;

    return esCorrecto;
    }
           
    public short getAnyo() {
        return anyo;
    }

    public void setAnyo(short anyo) {
        this.anyo = anyo;
    }

    public byte getMes() {
        return mes;
    }

    public void setMes(byte mes) {
        this.mes = mes;
    }

    public byte getDia() {
        return dia;
    }

    public void setDia(byte dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return dia+"/"+mes+"/"+anyo;
    }
    
    public int   getAntiguedad(){
        GregorianCalendar hoy;
        int anyos;
        
        hoy = new GregorianCalendar();
        anyos = hoy.get(GregorianCalendar.YEAR)- this.anyo;
         if (hoy.get(GregorianCalendar.MONTH) + 1 < this.mes
            || (hoy.get(GregorianCalendar.MONTH) + 1 == this.mes
                && hoy.get(GregorianCalendar.DAY_OF_MONTH) < this.dia)) {
                    
                anyos--;
        }
         return anyos;
    }
           
}
