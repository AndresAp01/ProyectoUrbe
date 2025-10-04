package modelo;

/**
 *
 * @author linuxman
 */
public class DatosRobotEdificio {
    private final int porcentajeAlerta;  // Ej: 23%
    private final int totalRobots;          // Ej: 100
    private final int robotsEnAlerta;       // Ej: 23

    public DatosRobotEdificio(int porcentajeAlerta, int totalRobots, int robotsEnAlerta) {
        this.porcentajeAlerta = porcentajeAlerta;
        this.totalRobots = totalRobots;
        this.robotsEnAlerta = robotsEnAlerta;
    }

    public int getPorcentajeAlerta() {
        return porcentajeAlerta;
    }

    public int getTotalRobots() {
        return totalRobots;
    }

    public int getRobotsEnAlerta() {
        return robotsEnAlerta;
    }

    @Override
    public String toString() {
        return "Porcentaje: " + porcentajeAlerta +
                "% | Total: " + totalRobots +
                " | En alerta: " + robotsEnAlerta;
    }
}