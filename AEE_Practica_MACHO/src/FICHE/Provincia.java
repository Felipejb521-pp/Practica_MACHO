
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

    private byte numero;
    private String descripcion;

    private Provincia(byte numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public byte getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Provincia buscador(byte numero) {
        Provincia provincia;
        provincia = null;
        for (Provincia p : values()) {
            if (p.numero == numero) {
                provincia = p;
            }
        }
        return provincia;
    }

    public boolean tieneComplementoDestino() {
        boolean verdadero;
        verdadero = false;
        if(this == Provincia.ALMERIA || this ==Provincia.HUELVA || this== Provincia.JAEN){
            verdadero = true;
        }
        return verdadero;
    }
}
