package modelo;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Notas:
 * El historial se genera luego de realizar la primera tarea, por otro lado, las tareas son fijas (no deben poseer CRUD), en el documento
 * son 6 maximo, por lo que, se decidio hacer un ArraList con numeros del 1 al 6, dependiendo de la cantidad de tareas (max 6). 
 * No se requieren validaciones debido a que en la creacion de robots solo se pone un random con limite de 6. Dentro de Ciuddano, digo que haya
 * otro random, el cual solo revise si hay un numero del 1 al 6 (que representan a las tareas del documento) y si posee ese numero la lista del robot 
 * lo "realiza" y si no "el robot la rechaza".
 * 
 * Por otro lado, el documento no decia como se debian cargar los robots, por ende, a la hora de un robot cargarse se le asigna automaticamente una estacion 
 * en donde siempre cargarse.
 * 
 */

/**
 * Clase que representa un Robot. Cada robot tiene un procesador, nivel de batería,
 * estado de encendido, conexión, propietario (Ciudadano) y un conjunto de posibles tareas.
 * Además, puede conectarse a un Cargaficio para recargar.
 */
public class Robot {
    private String procesador;
    private int bateria;
    private boolean encendido;
    private boolean conectado;
    private boolean owned;
    private Ciudadano unCiudadano;
    private boolean si_esta_en_alerta;
    private ArrayList<Tarea> posibles_tareas;
    private int valorMinimo;

    /**
     * Constructor principal de un robot.
     * Inicializa con batería completa, encendido y no conectado.
     * Genera un listado aleatorio de posibles tareas.
     * 
     * @param procesador Nombre del procesador del robot.
     * @param cantidad_tareas Cantidad de tareas que el robot puede realizar (máx 6).
     * @param valorMinimo Valor mínimo de batería para ciertas operaciones.
     */
    public Robot(String procesador, int cantidad_tareas, int valorMinimo){
        this.procesador = procesador;
        this.bateria = 100;
        this.encendido = true;
        this.conectado = false;
        this.owned = false;
        this.unCiudadano = null;
        this.si_esta_en_alerta = false;
        this.posibles_tareas = getPosiblesTareas(cantidad_tareas);
        this.valorMinimo = valorMinimo;
    }

    /**
     * Genera una lista aleatoria de posibles tareas para el robot.
     * 
     * @param cantidad Número de tareas a seleccionar.
     * @return ArrayList con las tareas seleccionadas aleatoriamente.
     */
    private ArrayList<Tarea> getPosiblesTareas(int cantidad) {
        List<Tarea> tareasDisponibles = new ArrayList<>(List.of(Tarea.values()));
        Collections.shuffle(tareasDisponibles);
        return new ArrayList<>(tareasDisponibles.subList(0, Math.min(cantidad, tareasDisponibles.size())));
    }

    // Getters y Setters

    public String getProcesador() { return procesador; }
    public void setProcesador(String procesador) { this.procesador = procesador; }

    public int getBateria() { return bateria; }
    public void setBateria(int bateria) { this.bateria = bateria; }

    public boolean isEncendido() { return encendido; }
    public void setEncendido(boolean encendido) { this.encendido = encendido; }

    public boolean isConectado() { return conectado; }
    public void setConectado(boolean conectado) { this.conectado = conectado; }

    public boolean isOwned() { return owned; }
    public void setOwned(boolean owned) { this.owned = owned; }

    public Ciudadano getUnCiudadano() { return unCiudadano; }
    public void setUnCiudadano(Ciudadano unCiudadano) { this.unCiudadano = unCiudadano; }

    public boolean isSi_esta_en_alerta() { return si_esta_en_alerta; }
    public void setSi_esta_en_alerta(boolean si_esta_en_alerta) { this.si_esta_en_alerta = si_esta_en_alerta; }

    public ArrayList<Tarea> getPosibles_tareas() { return posibles_tareas; }
    public void setPosibles_tareas(ArrayList<Tarea> posibles_tareas) { this.posibles_tareas = posibles_tareas; }

    public int getValorMinimo() { return valorMinimo; }

    // Funciones
    
    /**
     * Conecta el robot a un Cargaficio para recargar.
     * Solo puede conectarse si la estación no está llena.
     * 
     * @param unCargaficio Estación de carga a la que se conectará el robot.
     * @return true si se conectó correctamente, false si la estación estaba llena.
     */
    public boolean conectarse_estacion(Cargaficio unCargaficio){
        if (unCargaficio.getEstaLleno()){
            return false;
        }
        unCargaficio.agregar_robot(this);
        this.setConectado(true);
        return true;
    }

    // Representación en String

    @Override
    public String toString() {
        return "Robot{" +
                "procesador='" + procesador + '\'' +
                ", bateria=" + bateria +
                ", encendido=" + encendido +
                ", conectado=" + conectado +
                ", owned=" + owned +
                ", unCiudadano=" + unCiudadano +
                ", si_esta_en_alerta=" + si_esta_en_alerta +
                ", posibles_tareas=" + posibles_tareas +
                '}';
    }

    // equals() y hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Robot)) return false;
        Robot robot = (Robot) o;
        return Objects.equals(procesador, robot.procesador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(procesador);
    }
}
