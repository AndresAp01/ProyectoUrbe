package modelo;

import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Clase que representa un Cargaficio, encargado de gestionar robots y drones conectados,
 * controlar la ocupación y registrar un historial de servicios realizados.
 * 
 * Capacidad máxima por defecto: 20.
 */
public class Cargaficio {
    private String id;
    private String descripcion;
    private int espaciosOcupados;
    private int capacidadMaxima;
    private TStatus estado;
    private boolean estaLleno;
    private Calle calle;
    private Avenida avenida;
    private ArrayList<String> historial;
    private int serviciosUltHora;
    private ArrayList<Dron> dronesConectados;
    private ArrayList<Robot> robotsConectados;

    /**
     * Constructor de la clase Cargaficio.
     * Inicializa los valores por defecto y asigna aleatoriamente estado, avenida y calle.
     * 
     * @param id Identificador único del Cargaficio.
     * @param descripcion Breve descripción del Cargaficio.
     */
    public Cargaficio(String id, String descripcion){
        Random rand = new Random();
        Avenida[] avenidas = Avenida.values();
        Calle[] calles = Calle.values();
        TStatus[] estados = TStatus.values();
        this.id = id;
        this.descripcion = descripcion;
        this.espaciosOcupados = 0;
        this.capacidadMaxima = 20;
        this.estado = estados[rand.nextInt(TStatus.values().length)];
        this.estaLleno = false;
        this.avenida = avenidas[rand.nextInt(Avenida.values().length)];
        this.calle = calles[rand.nextInt(Calle.values().length)];
        this.historial = new ArrayList<>();
        this.serviciosUltHora = 0;
        this.dronesConectados = new ArrayList<>();
        this.robotsConectados = new ArrayList<>();
    }

    // =====================
    // Getters y Setters
    // =====================

    /** @return El ID del Cargaficio */
    public String getId() { return id; }

    /** @param id Nuevo ID del Cargaficio */
    public void setId(String id) { this.id = id; }

    /** @return La descripción del Cargaficio */
    public String getDescripcion() { return descripcion; }

    /** @param descripcion Nueva descripción del Cargaficio */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /** @return La cantidad de espacios ocupados actualmente */
    public int getEspaciosOcupados() { return espaciosOcupados; }

    /** @param espaciosOcupados Nueva cantidad de espacios ocupados */
    public void setEspaciosOcupados(int espaciosOcupados) { this.espaciosOcupados = espaciosOcupados; }

    /** @return La capacidad máxima del Cargaficio */
    public int getCapacidadMaxima() { return capacidadMaxima; }

    /** @param capacidadMaxima Nueva capacidad máxima */
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    /** @return El estado actual del Cargaficio */
    public TStatus getEstado() { return estado; }

    /** @param estado Nuevo estado del Cargaficio */
    public void setEstado(TStatus estado) { this.estado = estado; }

    /** @return true si el Cargaficio está lleno, false en caso contrario */
    public boolean getEstaLleno() { return estaLleno; }

    /** @param estaLleno Indica si el Cargaficio está lleno */
    public void setEstaLleno(boolean estaLleno) { this.estaLleno = estaLleno; }

    /** @return La calle donde se encuentra el Cargaficio */
    public Calle getCalle() { return calle; }

    /** @param calle Nueva calle del Cargaficio */
    public void setCalle(Calle calle) { this.calle = calle; }

    /** @return La avenida donde se encuentra el Cargaficio */
    public Avenida getAvenida() { return avenida; }

    /** @param avenida Nueva avenida del Cargaficio */
    public void setAvenida(Avenida avenida) { this.avenida = avenida; }

    /** @return El historial de servicios realizados */
    public ArrayList<String> getHistorial() { return historial; }

    /** @param historial Nuevo historial de servicios */
    public void setHistorial(ArrayList<String> historial) { this.historial = historial; }

    /** @return La cantidad de servicios realizados en la última hora */
    public int getServiciosUltHora() { return serviciosUltHora; }

    /** @param serviciosUltHora Nueva cantidad de servicios en la última hora */
    public void setServiciosUltHora(int serviciosUltHora) { this.serviciosUltHora = serviciosUltHora; }

    /** @return Lista de drones actualmente conectados */
    public ArrayList<Dron> getDronesConectados() { return dronesConectados; }

    /** @param dronesConectados Nueva lista de drones conectados */
    public void setDronesConectados(ArrayList<Dron> dronesConectados) { this.dronesConectados = dronesConectados; }

    /** @return Lista de robots actualmente conectados */
    public ArrayList<Robot> getRobotsConectados() { return robotsConectados; }

    /** @param robotsConectados Nueva lista de robots conectados */
    public void setRobotsConectados(ArrayList<Robot> robotsConectados) { this.robotsConectados = robotsConectados; }

    // =====================
    // Funciones de gestión
    // =====================

    /**
     * Calcula el porcentaje de ocupación del Cargaficio.
     * @return Porcentaje de ocupación (0 a 100)
     */
    public int getOcupacion() {
        return (espaciosOcupados * 100) / capacidadMaxima;
    }

    /**
     * Carga todos los robots conectados, registrando el servicio y actualizando el historial.
     * @return true si se cargaron robots, false si no había robots conectados
     */
    public boolean cargar_robots() {
        if (robotsConectados.isEmpty()) return false;
        ArrayList<Robot> robotsACargar = new ArrayList<>(robotsConectados);

        for (Robot robotActual : robotsACargar) {
            String detalle = "Robot [procesador=" + robotActual.getProcesador() + "] cargado el " + LocalDateTime.now();
            historial.add(detalle);
            serviciosUltHora += 1;

            robotActual.setBateria(100);
            robotActual.setEncendido(true);
            robotActual.setConectado(false);

            robotsConectados.remove(robotActual);
            setEspaciosOcupados(getEspaciosOcupados() - 1);
            if (getEstaLleno() && getEspaciosOcupados() < getCapacidadMaxima()) {
                setEstaLleno(false);
            }
        }
        return true;
    }

    /**
     * Carga todos los drones conectados, registrando el servicio y actualizando el historial.
     * @param capaciadMaxima Capacidad máxima del Cargaficio (para actualizar estado de lleno)
     * @return true si se cargaron drones, false si no había drones conectados
     */
    public boolean cargarDrones(int capaciadMaxima) {
        if (dronesConectados.isEmpty()) return false;
        ArrayList<Dron> dronesACargar = new ArrayList<>(dronesConectados);

        for (Dron dronActual : dronesACargar) {
            String detalle = "Dron [procesador=" + dronActual.getProcesador() + "] cargado el " + LocalDateTime.now();
            historial.add(detalle);
            serviciosUltHora += 1;

            dronActual.setBateria(100);
            dronActual.setActivo(true);

            dronesConectados.remove(dronActual);
            setEspaciosOcupados(getEspaciosOcupados() - 1);
            if (getEstaLleno() && getEspaciosOcupados() < capaciadMaxima) {
                setEstaLleno(false);
            }
        }
        return true;
    }

    /**
     * Agrega un robot al Cargaficio si hay espacio disponible.
     * @param unRobot Robot a agregar
     * @return true si se agregó correctamente, false si ya estaba conectado o no hay espacio
     */
    public boolean agregar_robot(Robot unRobot) {
        if (!robotsConectados.contains(unRobot) && !estaLleno) {
            robotsConectados.add(unRobot);
            setEspaciosOcupados(getEspaciosOcupados() + 1);
            if (getEspaciosOcupados() == getCapacidadMaxima()) {
                setEstaLleno(true);
            }
            return true;
        }
        return false;
    }

    /**
     * Método temporal para cargar drones (pendiente de implementar).
     * @return false
     */
    public boolean cargar_drones() {
        return false;
    }

    /**
     * Representación en String del Cargaficio.
     * @return Detalles completos del Cargaficio y sus elementos conectados
     */
    public String toString() {
        return "{Id: " + id +
                ", descripcion: " + descripcion +
                ",  espaciosOcupados: " + espaciosOcupados +
                ", capacidadMaxima: " + capacidadMaxima +
                ", Porcentaje de ocupacion: " + ((espaciosOcupados * 100) / capacidadMaxima) +
                ", estado: " + estado +
                ", estaLleno: " + estaLleno +
                ", calle: " + calle +
                ", avenida: " + avenida +
                ", historial: " + getHistorial() +
                ", serviciosUltHora: " + serviciosUltHora +
                ", dronesConectados: " + dronesConectados +
                ", robotsConectados: " + robotsConectados +
                "}\n";
    }

    /**
     * Compara Cargaficios únicamente por su ID.
     * @param obj Objeto a comparar
     * @return true si los IDs son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cargaficio)) return false;
        Cargaficio other = (Cargaficio) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * Genera hash basado en ID (necesario al sobrescribir equals)
     * @return Hashcode del Cargaficio
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
