/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author linuxman
 */


/**
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
 * 
 */

//Modificar robot para que sea como la regla 2 y modificar admRobot tmb.
public class Robot {
    private String procesador;
    private int bateria;
    private boolean encendido;
    private boolean conectado;
    private Cargaficio unCargaficio;
    private boolean owned;
    private Ciudadano unCiudadano;
    private boolean si_esta_en_alerta;
    private ArrayList<Tarea> posibles_tareas;
    
    
    public Robot (String procesador, int cantidad_tareas){
        this.procesador = procesador;
        this.bateria = 100;
        this.encendido = true;
        this.conectado = false;
        this.unCargaficio = null;
        this.owned = false;
        this.unCiudadano = null;
        this.si_esta_en_alerta = false;
        this.posibles_tareas = getPosiblesTareas(cantidad_tareas);
        
    }
    
    private ArrayList<Tarea> getPosiblesTareas(int cantidad) {
        //da valores aleatorios de las posibles tareas que puede hacer el robot, llamando a la class enum Tarea
        List<Tarea> tareasDisponibles = new ArrayList<>(List.of(Tarea.values()));
        Collections.shuffle(tareasDisponibles);
        return new ArrayList<>(tareasDisponibles.subList(0, Math.min(cantidad, tareasDisponibles.size())));
    }



     // Getters y Setters

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public Cargaficio getUnCargaficio() {
        return unCargaficio;
    }

    public void setUnCargaficio(Cargaficio unCargaficio) {
        this.unCargaficio = unCargaficio;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public Ciudadano getUnCiudadano() {
        return unCiudadano;
    }

    public void setUnCiudadano(Ciudadano unCiudadano) {
        this.unCiudadano = unCiudadano;
    }

    public boolean isSi_esta_en_alerta() {
        return si_esta_en_alerta;
    }

    public void setSi_esta_en_alerta(boolean si_esta_en_alerta) {
        this.si_esta_en_alerta = si_esta_en_alerta;
    }

    public ArrayList<Tarea> getPosibles_tareas() {
        return posibles_tareas;
    }

    public void setPosibles_tareas(ArrayList<Tarea> posibles_tareas) {
        this.posibles_tareas = posibles_tareas;
    }

    
    // funciones
    public boolean conectarse_estacion(){
        if (unCargaficio.getEstaLleno()){
            return false;
        }
        unCargaficio.agregar_robot(this);
        return true;
    }
    
    // toString()

    @Override
    public String toString() {
        return "Robot{" +
                "procesador='" + procesador + '\'' +
                ", bateria=" + bateria +
                ", encendido=" + encendido +
                ", conectado=" + conectado +
                ", unCargaficio=" + unCargaficio +
                ", owned=" + owned +
                ", unCiudadano=" + unCiudadano +
                ", si_esta_en_alerta=" + si_esta_en_alerta +
                ", posibles_tareas=" + posibles_tareas +
                '}';
    }

    // equals()

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


