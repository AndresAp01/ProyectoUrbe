package modelo;

    /**
     *
     * @author linuxman
     */
public class DatosCargaficioOcupacionEstado {
    private final int ocupacion;  // Ej: 23%
    private final TStatus status; //Ej: En Mantenimiento

    public DatosCargaficioOcupacionEstado(int ocupacion, TStatus status) {
        this.ocupacion = ocupacion;
        this.status = status;
    }

    public TStatus getStatus() {
        return status;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    @Override
    public String toString() {
        return "Porcentaje Ocupacion: " + ocupacion +
                "% | Estado: " + status;
    }
}

