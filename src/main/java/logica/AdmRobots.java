package logica;

import java.util.ArrayList;
import java.util.Random;
import modelo.Robot;
import modelo.Cargaficio;
import modelo.Regla;

/**
 * Clase que administra la lista de robots del sistema.
 * Permite agregar, consultar, modificar, eliminar y generar robots,
 * así como conectarlos a estaciones de carga disponibles.
 * También proporciona funciones de estadísticas sobre los robots.
 */
public class AdmRobots {

    /** Lista de robots gestionados por el sistema. */
    private ArrayList<Robot> listaRobots;

    /** Contador de robots totales en la lista. */
    private int total_robots;

    /** Contador de robots que se encuentran en estado de alerta. */
    private int total_robots_alerta;

    /**
     * Constructor que inicializa la lista de robots.
     */
    public AdmRobots() {
        this.listaRobots = new ArrayList<Robot>();
    }

    // -------------------- CRUD --------------------

    /**
     * Agrega un robot a la lista si no existe previamente.
     * @param unRobot Robot a agregar.
     * @return true si se agrega, false si ya existía.
     */
    public boolean agregar(Robot unRobot) {
        for (Robot RobotActual : listaRobots)
            if (RobotActual.equals(unRobot))
                return false;
        listaRobots.add(unRobot);
        return true;
    }

    /**
     * Busca un robot por su procesador.
     * @param elProcesador Identificador del procesador.
     * @return El robot correspondiente o null si no se encuentra.
     */
    public Robot consultar(String elProcesador) {
        for (int i = 0; i < listaRobots.size(); i++) {
            Robot Robot = listaRobots.get(i);
            if (elProcesador.equals(Robot.getProcesador()))
                return Robot;
        }
        return null;
    }

    /**
     * Modifica un robot existente reemplazándolo por uno nuevo.
     * @param nuevoRobot Robot con los datos actualizados.
     * @return true si se modificó, false si no se encontró.
     */
    public boolean modificar(Robot nuevoRobot) {
        for (int i = 0; i < listaRobots.size(); i++) {
            if (listaRobots.get(i).equals(nuevoRobot)) {
                listaRobots.set(i, nuevoRobot);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un robot de la lista según su procesador.
     * @param elProcesador Identificador del procesador.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(String elProcesador) {
        for (int i = 0; i < listaRobots.size(); i++) {
            if (elProcesador.equals(listaRobots.get(i).getProcesador())) {
                listaRobots.remove(i);
                return true;
            }
        }
        return false;
    }

    // -------------------- Funciones adicionales --------------------

    /**
     * Retorna la cantidad total de robots.
     * @return Número total de robots.
     */
    public int TotalRobots() {
        for (Robot RobotActual : listaRobots)
            total_robots += 1;
        return total_robots;
    }

    /**
     * Retorna la cantidad de robots en estado de alerta.
     * @return Número de robots en alerta.
     */
    public int TotalRobotsAlerta() {
        for (Robot RobotActual : listaRobots)
            if (RobotActual.isSi_esta_en_alerta()) {
                total_robots_alerta += 1;
            }
        return total_robots_alerta;
    }

    /**
     * Conecta los robots apagados a cargaficios disponibles.
     * @param AdmCargaficios Administrador de cargaficios.
     * @return true si todos los robots se conectan, false si no hay cargaficios suficientes.
     */
    public boolean conectarRobots(AdmCargaficios AdmCargaficios) {
        for (Robot RobotActual : listaRobots) {
            if (!RobotActual.isEncendido() && !RobotActual.isConectado()) {
                Cargaficio cargaficio = AdmCargaficios.retornarCargaficioDisponible();
                RobotActual.conectarse_estacion(cargaficio);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Crea una lista de robots nuevos con tareas aleatorias y nivel de batería.
     * @param cant Cantidad de robots a crear.
     * @param regla Regla que puede modificar el valor mínimo de batería.
     * @return true si se ejecuta la creación.
     */
    public boolean crearListaRobots(int cant, Regla regla) {
        for (int i = (listaRobots.size() + 1); i <= (cant + listaRobots.size() + 1); i++) {
            String procesador = "IAAA-" + i;

            Random random = new Random();
            int numero = random.nextInt(6) + 1;

            if (regla.getActiva()) {
                Robot nRobot = new Robot(procesador, numero, regla.getValorMinimoBateria());
                i++;
            } else {
                Robot nRobot = new Robot(procesador, numero, 5);
            }
        }
        return true;
    }

    /**
     * Representación en String de la lista de robots.
     * @return String con todos los robots.
     */
    @Override
    public String toString() {
        return "lista de Robots:\n" + listaRobots;
    }
}
