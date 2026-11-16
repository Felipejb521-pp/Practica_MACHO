/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FICHE;

/**
 * @author Sergio García de Baya
 */
public class Empleado {

    private String nomApes;
    private Sexo sexo;
    private float salario;
    private Fecha fechaIngreso;
    private TipoEmpleado tipoEmpleado;
    private Provincia provincia;

    public Empleado() {
    }

    public Empleado(String nomApes, Sexo sexo, float salario, Fecha fechaIngreso, TipoEmpleado tipoEmpleado, Provincia provincia) {
        this.nomApes = nomApes;
        this.sexo = sexo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.tipoEmpleado = tipoEmpleado;
        this.provincia = provincia;
    }

    public String getNomApes() {
        return nomApes;
    }

    public void setNomApes(String nomApes) {
        this.nomApes = nomApes;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
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

    public byte getTrienios() {
        return (byte) (fechaIngreso.getAntiguedad() / 3);
    }

    public float getComplemento() {
        float complemento = 0;
        complemento += getTrienios() * 24;

        if (provincia == Provincia.ALMERIA || provincia == Provincia.HUELVA || provincia == Provincia.JAEN) {
            complemento += salario * 0.10f;
        }

        if (sexo == Sexo.H) {
            complemento += 120;
        }
        return complemento;
    }

    public float getSueldo() {
        return salario + getComplemento();
    }
}