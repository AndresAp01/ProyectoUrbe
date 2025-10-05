package modelo;

/**
 * Clase que representa un resumen del estado de un Cargaficio,
 * incluyendo su porcentaje de ocupación y su estado actual.
 * 
 * Ejemplo de uso:
 * Porcentaje Ocupacion: 23% | Estado: En Mantenimiento
 * 
 * Esta clase es inmutable: sus valores se establecen al crear el objeto y no cambian.
 * 
 * @author linuxman
 */
public class DatosCargaficioOcupacionEstado {
    private final int ocupacion;  // Ej: 23%
    private final TStatus status; // Ej: En Mantenimiento

    /**
     * Constructor de la clase.
     * 
     * @param ocupacion Porcentaje de ocupación del Cargaficio.
     * @param status Estado actual del Cargaficio.
     */
    public DatosCargaficioOcupacionEstado(int ocupacion, TStatus status) {
        this.ocupacion = ocupacion;
        this.status = status;
    }

    /**
     * Obtiene el estado actual del Cargaficio.
     * 
     * @return Estado del Cargaficio (TStatus)
     */
    public TStatus getStatus() {
        return status;
    }

    /**
     * Obtiene el porcentaje de ocupación del Cargaficio.
     * 
     * @return Porcentaje de ocupación (0-100)
     */
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Representación en String de la información del Cargaficio.
     * 
     * @return String con porcentaje de ocupación y estado.
     */
    @Override
    public String toString() {
        return "Porcentaje Ocupacion: " + ocupacion +
                "% | Estado: " + status;
    }
}
