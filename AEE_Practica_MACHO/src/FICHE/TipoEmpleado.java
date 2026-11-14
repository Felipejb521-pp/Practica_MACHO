
package FICHE;

/**
 *
 * @author Felipe Jimenez
 */
public enum TipoEmpleado {
    C("A comision"), F("Fijos"), D("A domicilio");

    private String descripcion;

    private TipoEmpleado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoEmpleado buscador(char letra) {
        return valueOf(String.valueOf(letra));
    }
}