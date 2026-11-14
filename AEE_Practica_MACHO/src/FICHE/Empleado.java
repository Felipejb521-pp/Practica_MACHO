package FICHE;

import java.util.GregorianCalendar;

    /**
 *
 
@author Sergio García de Baya*/
public class Empleado {
    private String nomApe;
    private char sexo;
    private float salario;
    private Fecha fechaIngreso;
    private TipoEmpleado tipoEmpleado;
    private Provincia provincia;

    public Empleado() {
    }

    public String getNomApe() {
        return nomApe;
    }

    public void setNomApe(String nomApe) {
        this.nomApe = nomApe;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Fecha getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Fecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    public byte getTrienios(){
        
        return (byte)(fechaIngreso.getAntiguedad()/3);
    }
    
    public float getComplemento() {
        float complemento;

        complemento = getTrienios() * 24;
        if (provincia.tieneComplementoDestino()) {
            complemento += salario * 0.10f;
        }
        if (sexo == Sexo.H) {
            complemento += 120;
        }

        return complemento;
    }

    public float getSueldo() {
        float sueldo;

        sueldo = salario + getComplemento();

        return sueldo;
    }
}

