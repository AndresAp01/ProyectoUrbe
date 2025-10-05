/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import logica.CantTareasUsadas;
/**
 *
 * @author linuxman
 */
public class Ciudadano {
    private String id;
    private ArrayList<Robot> robots_owned;
    private Edificio edificio_habitado;
    private int tareas_rechazadas;
    private int tareas_hechas;
    private ArrayList<String> historial;
    
    public Ciudadano (String id, Edificio unEdificio){
        this.id = id;
        this.robots_owned = new ArrayList<>();
        this.edificio_habitado = unEdificio;
        this.tareas_rechazadas = 0;
        this.tareas_hechas = 0;
        this.historial = new ArrayList<>();
    }
    
    //setters y getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Robot> getRobots_owned() {
        return robots_owned;
    }

    public void setRobots_owned(ArrayList<Robot> robots_owned) {
        this.robots_owned = robots_owned;
    }

    public Edificio getEdificio_habitado() {
        return edificio_habitado;
    }

    public void setEdificio_habitado(Edificio edificio_habitado) {
        this.edificio_habitado = edificio_habitado;
    }

    public int getTareas_rechazadas() {
        return tareas_rechazadas;
    }

    public void setTareas_rechazadas(int tareas_rechazadas) {
        this.tareas_rechazadas = tareas_rechazadas;
    }

    public int getTareas_hechas() {
        return tareas_hechas;
    }

    public void setTareas_hechas(int tareas_hechas) {
        this.tareas_hechas = tareas_hechas;
    }
    
    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }
    
    //funciones
    public boolean asignar_robot(Robot unRobot){
        for (Robot RobotActual: robots_owned)
            if (RobotActual.equals(unRobot)){
                return false;
            }
        robots_owned.add(unRobot);
        return true;
    }
    
    public boolean mudarse(Edificio unEdificio){
        return unEdificio.agregarCiudadano(this);
    }
    
    public boolean pedir_tarea(CantTareasUsadas cantTareasUsadas){
        Random rand = new Random();
        if (!robots_owned.isEmpty()){
            int indice = rand.nextInt(robots_owned.size());
            Robot aleatorio = robots_owned.get(indice);
            List<Tarea> tareasDisponibles = new ArrayList<>(List.of(Tarea.values()));
            int indice_tarea = rand.nextInt(6);
            Tarea tarea_a_realizar = tareasDisponibles.get(indice_tarea);
            if (aleatorio.getPosibles_tareas().contains(tarea_a_realizar) && aleatorio.isEncendido()){
                //realiza las tareas dependiendo de que posea dentro
                if (tarea_a_realizar == Tarea.MEDICO){
                    if ((aleatorio.getBateria() - 5) < 0){
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Agendar Cita Medica, atendida por Robot:  "+aleatorio.getProcesador()+" a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 5);
                    cantTareasUsadas.setCantMedico(cantTareasUsadas.getCantMedico()+1);
                    return true;
                }
                
                if (tarea_a_realizar == Tarea.DORMITORIO){
                    if ((aleatorio.getBateria() - 15) < 0){
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Asear Dormitorio atendida por Robot:  "+aleatorio.getProcesador()+" a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 15);
                    cantTareasUsadas.setCantDormitorio(cantTareasUsadas.getCantDormitorio()+1);
                    return true;
                }
                
                if (tarea_a_realizar == Tarea.COMPRAR){
                    if ((aleatorio.getBateria() - 10) < 0){
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Comprar alimentos:  "+aleatorio.getProcesador()+" a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 10);
                    cantTareasUsadas.setCantAlimentos(cantTareasUsadas.getCantAlimentos()+1);
                    return true;
                }
                
                if (tarea_a_realizar == Tarea.REGAR){
                    if ((aleatorio.getBateria() - 5) < 0){
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Regar las plantas atendida por Robot:  "+aleatorio.getProcesador()+" a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 5);
                    cantTareasUsadas.setCantPlantas(cantTareasUsadas.getCantPlantas()+1);
                    return true;
                }
                
                if (tarea_a_realizar == Tarea.PASEO){
                    if ((aleatorio.getBateria() - 20) < 0){
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Pasear con Usuario atendida por Robot:  "+aleatorio.getProcesador()+" a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 20);
                    cantTareasUsadas.setCantPaseo(cantTareasUsadas.getCantPaseo()+1);
                    return true;
                }
                
                if (tarea_a_realizar == Tarea.REUNION){
                    if ((aleatorio.getBateria() - 25) < 0){
                        tareas_rechazadas += 1;
                        return false;
                    }
                    String detalle = "Tarea: Asistir Reunion con Usuario atendida por Robot:  "+aleatorio.getProcesador()+" a las: " + LocalDateTime.now();
                    historial.add(detalle);
                    tareas_hechas += 1;
                    aleatorio.setBateria(aleatorio.getBateria() - 25);
                    cantTareasUsadas.setCantReunion(cantTareasUsadas.getCantReunion()+1);
                    return true;
                }
            }
            if (aleatorio.getBateria() <= aleatorio.getValorMinimo()){
                aleatorio.setEncendido(false); //se apaga y luego admRobots revisa si se debe conectar a alguna estacion disponible
            }
           return false; //el robot no contiene la tarea a realizar 
        }
        return false; //el Ciudadano no posee robots
    }
    
    //To string
    @Override
    public String toString() {
        return "Ciudadano{" +
                "id='" + id + '\'' +
                ", robots_owned=" + robots_owned.size() +
                ", edificio_habitado=" + (edificio_habitado != null ? edificio_habitado.toString() : "null") +
                ", tareas_rechazadas=" + tareas_rechazadas +
                ", tareas_hechas=" + tareas_hechas + ", historial=" + historial +
                '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Ciudadano other = (Ciudadano) obj;
        return this.id != null && this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
