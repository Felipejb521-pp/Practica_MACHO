
package FICHE;

/**
 *
 * @author Felipe Jimenez
 */
public enum Provincia {
    ALMERIA((byte) 1, "Almeria"),
    CADIZ((byte) 2, "Cadiz"),
    CORDOBA((byte) 3, "Cordoba"),
    GRANADA((byte) 4, "Granada"),
    HUELVA((byte) 5, "Huelva"),
    JAEN((byte) 6, "Jaen"),
    MALAGA((byte) 7, "Malaga"),
    SEVILLA((byte) 8, "Sevilla");

    private byte codigo;
    private String descripcion;

    private Provincia(byte codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public byte getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Provincia buscador(byte codigo) {
        Provincia provincia;

        provincia = null;
        for (Provincia p : values()) {
            if (p.codigo == codigo) {
                provincia = p;
            }
        }

        return provincia;
    }

    public boolean tieneComplementoDestino() {
        boolean verdadero;

        verdadero = false;
        if (this == Provincia.ALMERIA || this == Provincia.HUELVA || this == Provincia.JAEN) {
            verdadero = true;
        }

        return verdadero;
    }
}
