/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author linuxman
 * 20 = capacidad_maxima
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
        this.dronesConectados = new ArrayList<Dron>();
        this.robotsConectados = new ArrayList<Robot>();
        
    }
    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEspaciosOcupados() {
        return espaciosOcupados;
    }

    public void setEspaciosOcupados(int espaciosOcupados) {
        this.espaciosOcupados = espaciosOcupados;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public TStatus getEstado() {
        return estado;
    }

    public void setEstado(TStatus estado) {
        this.estado = estado;
    }

    public boolean getEstaLleno() {
        return estaLleno;
    }

    public void setEstaLleno(boolean estaLleno) {
        this.estaLleno = estaLleno;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    public Avenida getAvenida() {
        return avenida;
    }

    public void setAvenida(Avenida avenida) {
        this.avenida = avenida;
    }

    public ArrayList getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList historial) {
        this.historial = historial;
    }

    public int getServiciosUltHora() {
        return serviciosUltHora;
    }

    public void setServiciosUltHora(int serviciosUltHora) {this.serviciosUltHora = serviciosUltHora;}

    public ArrayList<Dron> getDronesConectados() {
        return dronesConectados;
    }

    public void setDronesConectados(ArrayList<Dron> dronesConectados) {
        this.dronesConectados = dronesConectados;
    }

    public ArrayList<Robot> getRobotsConectados() {
        return robotsConectados;
    }

    public void setRobotsConectados(ArrayList<Robot> robotsConectados) {
        this.robotsConectados = robotsConectados;
    }
    
    public int getOcupacion(){
        return ((espaciosOcupados*100)/capacidadMaxima);
    }
    
    //Funciones
    public boolean cargar_robots() {
        if (robotsConectados.isEmpty()) return false;

        // Creamos una lista temporal para evitar ConcurrentModificationException
        ArrayList<Robot> robotsACargar = new ArrayList<>(robotsConectados);

        for (Robot robotActual : robotsACargar) {
            // Guardar en historial
            String detalle = "Robot [procesador=" + robotActual.getProcesador() + "] cargado el " + LocalDateTime.now();
            historial.add(detalle);
            serviciosUltHora += 1; //actualizar servicios dados

            // Cargar batería
            robotActual.setBateria(100);

            // Remover robot de la lista original
            robotsConectados.remove(robotActual);

            // Actualizar espacios ocupados y si está lleno
            setEspaciosOcupados(getEspaciosOcupados() - 1);
            if (getEstaLleno() && getEspaciosOcupados() < getCapacidadMaxima()) {
                setEstaLleno(false);
            }
        }
        return true;
    }

    public boolean cargarDrones(int capaciadMaxima) {
        if (dronesConectados.isEmpty()) return false;

        // Creamos una lista temporal para evitar ConcurrentModificationException
        ArrayList<Dron> dronesACargar = new ArrayList<>(dronesConectados);

        for (Dron dronActual : dronesACargar) {
            // Guardar en historial
            String detalle = "Dron [procesador=" + dronActual.getProcesador() + "] cargado el " + LocalDateTime.now();
            historial.add(detalle);
            serviciosUltHora += 1; //actualiar servicios dados
            
            // Cargar batería
            dronActual.setBateria(100);

            // Remover robot de la lista original
            dronesConectados.remove(dronActual);

            // Actualizar espacios ocupados y si está lleno
            setEspaciosOcupados(getEspaciosOcupados() - 1);
            if (getEstaLleno() && getEspaciosOcupados() < capaciadMaxima) {
                setEstaLleno(false);
            }
        }
        return true;
    }
    
    public boolean agregar_robot(Robot unRobot){
        if (!robotsConectados.contains(unRobot) && !estaLleno) {
            robotsConectados.add(unRobot);
            setEspaciosOcupados(getEspaciosOcupados()+1);
            if (getEspaciosOcupados() == getCapacidadMaxima()){
                setEstaLleno(true);
            }
            return true;
        }
        return false;
    }
    //Falta cargar drones, toca hacer clase y agregar_dron, etc.
    public boolean cargar_drones(){
        return false;
    }
    // equals() solo por ID

    public String toString(){
        return "{Id: "+id+
                ", descripcion: "+descripcion+
                ",  espaciosOcupados: "+espaciosOcupados+
                ", capacidadMaxima: "+capacidadMaxima+ 
                ", Porcentaje de ocupacion: "+((espaciosOcupados*100)/capacidadMaxima)+
                ", estado: "+estado+
                ", estaLleno: "+estaLleno+
                ", calle: "+calle+
                ", avenida: "+avenida+
                ", historial: "+getHistorial()+
                ", serviciosUltHora: "+serviciosUltHora+
                ", dronesConectados: "+dronesConectados+
                ", robotsConectados: "+robotsConectados+
                "}\n";
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cargaficio)) return false;
        Cargaficio other = (Cargaficio) obj;
        return Objects.equals(this.id, other.id);
    }

    // hashCode() basado en ID (requerido si sobrescribes equals)

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
