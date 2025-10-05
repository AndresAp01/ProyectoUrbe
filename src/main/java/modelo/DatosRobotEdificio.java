package modelo;

/**
 * Clase que representa un resumen del estado de los robots en un edificio.
 * Incluye porcentaje de robots en alerta, total de robots y cantidad de robots en alerta.
 * 
 * Ejemplo de uso:
 * Porcentaje: 23% | Total: 100 | En alerta: 23
 * 
 * Esta clase es inmutable: sus valores se establecen al crear el objeto y no cambian.
 * 
 * @author linuxman
 */
public class DatosRobotEdificio {
    private final int porcentajeAlerta;  // Ej: 23%
    private final int totalRobots;       // Ej: 100
    private final int robotsEnAlerta;    // Ej: 23

    /**
     * Constructor de la clase.
     * 
     * @param porcentajeAlerta Porcentaje de robots en alerta dentro del edificio.
     * @param totalRobots Cantidad total de robots en el edificio.
     * @param robotsEnAlerta Cantidad de robots que actualmente están en alerta.
     */
    public DatosRobotEdificio(int porcentajeAlerta, int totalRobots, int robotsEnAlerta) {
        this.porcentajeAlerta = porcentajeAlerta;
        this.totalRobots = totalRobots;
        this.robotsEnAlerta = robotsEnAlerta;
    }

    /**
     * Obtiene el porcentaje de robots en alerta.
     * 
     * @return Porcentaje de robots en alerta (0-100)
     */
    public int getPorcentajeAlerta() {
        return porcentajeAlerta;
    }

    /**
     * Obtiene la cantidad total de robots en el edificio.
     * 
     * @return Número total de robots
     */
    public int getTotalRobots() {
        return totalRobots;
    }

    /**
     * Obtiene la cantidad de robots actualmente en alerta.
     * 
     * @return Número de robots en alerta
     */
    public int getRobotsEnAlerta() {
        return robotsEnAlerta;
    }

    /**
     * Representación en String de la información de los robots en el edificio.
     * 
     * @return String con porcentaje de alerta, total de robots y robots en alerta
     */
    @Override
    public String toString() {
        return "Porcentaje: " + porcentajeAlerta +
                "% | Total: " + totalRobots +
                " | En alerta: " + robotsEnAlerta;
    }
}
