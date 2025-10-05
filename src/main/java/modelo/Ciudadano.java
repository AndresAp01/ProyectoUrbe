package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import logica.CantTareasUsadas;

/**
 * Clase que representa un Ciudadano dentro de la ciudad.
 * Un ciudadano puede poseer robots, vivir en un edificio, realizar tareas y mantener un historial de actividades.
 */
public class Ciudadano {
    private String id;
    private ArrayList<Robot> robots_owned;
    private Edificio edificio_habitado;
    private int tareas_rechazadas;
    private int tareas_hechas;
    private ArrayList<String> historial;

    /**
     * Constructor de la clase Ciudadano.
     * Inicializa un ciudadano con ID y edificio habitado, y listas vacías de robots e historial.
     *
     * @param id Identificador único del ciudadano.
     * @param unEdificio Edificio en el que habita el ciudadano.
     */
    public Ciudadano(String id, Edificio unEdificio) {
        this.id = id;
        this.robots_owned = new ArrayList<>();
        this.edificio_habitado = unEdificio;
        this.tareas_rechazadas = 0;
        this.tareas_hechas = 0;
        this.historial = new ArrayList<>();
    }

    // Getters y Setters

    /** @return ID del ciudadano */
    public String getId() { return id; }

    /** @param id Nuevo ID del ciudadano */
    public void setId(String id) { this.id = id; }

    /** @return Lista de robots que posee el ciudadano */
    public ArrayList<Robot> getRobots_owned() { return robots_owned; }

    /** @param robots_owned Nueva lista de robots propiedad del ciudadano */
    public void setRobots_owned(ArrayList<Robot> robots_owned) { this.robots_owned = robots_owned; }

    /** @return Edificio en el que habita el ciudadano */
    public Edificio getEdificio_habitado() { return edificio_habitado; }

    /** @param edificio_habitado Nuevo edificio habitado por el ciudadano */
    public void setEdificio_habitado(Edificio edificio_habitado) { this.edificio_habitado = edificio_habitado; }

    /** @return Cantidad de tareas rechazadas por el ciudadano */
    public int getTareas_rechazadas() { return tareas_rechazadas; }

    /** @param tareas_rechazadas Nueva cantidad de tareas rechazadas */
    public void setTareas_rechazadas(int tareas_rechazadas) { this.tareas_rechazadas = tareas_rechazadas; }

    /** @return Cantidad de tareas realizadas por el ciudadano */
    public int getTareas_hechas() { return tareas_hechas; }

    /** @param tareas_hechas Nueva cantidad de tareas realizadas */
    public void setTareas_hechas(int tareas_hechas) { this.tareas_hechas = tareas_hechas; }

    /** @return Historial de actividades del ciudadano */
    public ArrayList<String> getHistorial() { return historial; }

    /** @param historial Nuevo historial de actividades */
    public void setHistorial(ArrayList<String> historial) { this.historial = historial; }

    // Funciones de gestión

    /**
     * Asigna un robot al ciudadano si aún no lo posee.
     *
     * @param unRobot Robot a asignar
     * @return true si se asignó correctamente, false si ya estaba asignado
     */
    public boolean asignar_robot(Robot unRobot) {
        for (Robot RobotActual : robots_owned)
            if (RobotActual.equals(unRobot)) {
                return false;
            }
        robots_owned.add(unRobot);
        return true;
    }

    /**
     * Permite al ciudadano mudarse a otro edificio.
     *
     * @param unEdificio Edificio al que desea mudarse
     * @return true si la mudanza fue exitosa, false si no fue posible
     */
    public boolean mudarse(Edificio unEdificio) {
        return unEdificio.agregarCiudadano(this);
    }

    /**
     * Permite al ciudadano pedir a un robot realizar una tarea específica.
     * Se verifica que el robot tenga la tarea disponible y suficiente batería.
     * Actualiza el historial y las estadísticas de tareas hechas/rechazadas.
     *
     * @param cantTareasUsadas Objeto que registra la cantidad de tareas realizadas por tipo
     * @return true si la tarea fue realizada exitosamente, false si no fue posible
     */
    public boolean pedir_tarea(CantTareasUsadas cantTareasUsadas) {
        Random rand = new Random();
        if (!robots_owned.isEmpty()) {
            int indice = rand.nextInt(robots_owned.size());
            Robot aleatorio = robots_owned.get(indice);
            List<Tarea> tareasDisponibles = new ArrayList<>(List.of(Tarea.values()));
            int indice_tarea = rand.nextInt(6);
            Tarea tarea_a_realizar = tareasDisponibles.get(indice_tarea);

            if (aleatorio.getPosibles_tareas().contains(tarea_a_realizar) && aleatorio.isEncendido()) {
                // Dependiendo del tipo de tarea, se resta batería y se actualiza historial
                if (tarea_a_realizar == Tarea.MEDICO) {
                    if ((aleatorio.getBateria() - 5) < 0) {
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Agendar Cita Medica, atendida por Robot: " + aleatorio.getProcesador() + " a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 5);
                    cantTareasUsadas.setCantMedico(cantTareasUsadas.getCantMedico() + 1);
                    return true;
                }

                if (tarea_a_realizar == Tarea.DORMITORIO) {
                    if ((aleatorio.getBateria() - 15) < 0) {
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Asear Dormitorio atendida por Robot: " + aleatorio.getProcesador() + " a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 15);
                    cantTareasUsadas.setCantDormitorio(cantTareasUsadas.getCantDormitorio() + 1);
                    return true;
                }

                if (tarea_a_realizar == Tarea.COMPRAR) {
                    if ((aleatorio.getBateria() - 10) < 0) {
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Comprar alimentos: " + aleatorio.getProcesador() + " a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 10);
                    cantTareasUsadas.setCantAlimentos(cantTareasUsadas.getCantAlimentos() + 1);
                    return true;
                }

                if (tarea_a_realizar == Tarea.REGAR) {
                    if ((aleatorio.getBateria() - 5) < 0) {
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Regar las plantas atendida por Robot: " + aleatorio.getProcesador() + " a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 5);
                    cantTareasUsadas.setCantPlantas(cantTareasUsadas.getCantPlantas() + 1);
                    return true;
                }

                if (tarea_a_realizar == Tarea.PASEO) {
                    if ((aleatorio.getBateria() - 20) < 0) {
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Pasear con Usuario atendida por Robot: " + aleatorio.getProcesador() + " a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 20);
                    cantTareasUsadas.setCantPaseo(cantTareasUsadas.getCantPaseo() + 1);
                    return true;
                }

                if (tarea_a_realizar == Tarea.REUNION) {
                    if ((aleatorio.getBateria() - 25) < 0) {
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Asistir Reunion con Usuario atendida por Robot: " + aleatorio.getProcesador() + " a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 25);
                    cantTareasUsadas.setCantReunion(cantTareasUsadas.getCantReunion() + 1);
                    return true;
                }
            }

            if (aleatorio.getBateria() <= aleatorio.getValorMinimo()) {
                aleatorio.setEncendido(false); // Apaga robot si batería mínima alcanzada
            }
            return false; // robot no puede realizar la tarea
        }
        return false; // ciudadano no posee robots
    }

    // ToString, equals y hashCode

    /**
     * Representación en String del ciudadano, incluyendo ID, cantidad de robots y historial.
     * @return Detalles del ciudadano en formato String
     */
    @Override
    public String toString() {
        return "Ciudadano{" +
                "id='" + id + '\'' +
                ", robots_owned=" + robots_owned.size() +
                ", edificio_habitado=" + (edificio_habitado != null ? edificio_habitado.toString() : "null") +
                ", tareas_rechazadas=" + tareas_rechazadas +
                ", tareas_hechas=" + tareas_hechas +
                ", historial=" + historial +
                '}';
    }

    /**
     * Compara ciudadanos únicamente por su ID.
     * @param obj Objeto a comparar
     * @return true si los IDs coinciden, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Ciudadano other = (Ciudadano) obj;
        return this.id != null && this.id.equals(other.id);
    }

    /**
     * Genera hash basado en el ID del ciudadano.
     * @return Hashcode del ciudadano
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
