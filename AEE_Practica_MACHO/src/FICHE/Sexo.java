package FICHE;
/**
 *
 * @author Felipe Jimenez
 */

public enum Sexo {
    M("Mujer"), H("Hombre");

    private String descripcion;

    private Sexo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Sexo buscador(char letra) {
        return valueOf(String.valueOf(letra));
    }
}
