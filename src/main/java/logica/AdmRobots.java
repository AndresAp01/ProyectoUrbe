/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;

import modelo.Robot;
import modelo.Cargaficio;
import modelo.Regla;
import java.util.Random;

/**
 *
 * @author linuxman
 * Solo para guardar la lista de los robots
 */


public class AdmRobots {
    private ArrayList<Robot> listaRobots;
    
    
    public AdmRobots(){
        this.listaRobots = new ArrayList<Robot>();
    }
    
    public boolean agregar(Robot unRobot){
        for (Robot RobotActual: listaRobots)
            if (RobotActual.equals(unRobot))
                return false;
        listaRobots.add(unRobot);
        return true;
    }

    public Robot consultar(String elProcesador){
        for (int i = 0; i<listaRobots.size(); i++){
            Robot Robot = listaRobots.get(i);
            if (elProcesador.equals(Robot.getProcesador()))
                return Robot;
        } // for
        return null;   // no encontró el Robot

    }

    public boolean modificar(Robot nuevoRobot){
        for (int i = 0; i < listaRobots.size(); i++){
            if (listaRobots.get(i).equals(nuevoRobot)){
                listaRobots.set(i, nuevoRobot);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String elProcesador){
        for (int i = 0; i < listaRobots.size(); i++){
            if (elProcesador.equals(listaRobots.get(i).getProcesador())){
                listaRobots.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // funciones
    
    public int TotalRobots(){
        int total_robots = 0;
        for (Robot RobotActual: listaRobots)
            total_robots += 1;
        return total_robots;
    }
    
    public int TotalRobotsAlerta(){
        int total_robots_alerta = 0;
        for (Robot RobotActual: listaRobots)
            if (RobotActual.isSi_esta_en_alerta()){
                total_robots_alerta += 1;
            }
        return total_robots_alerta;
    }
    
    public boolean conectarRobots(AdmCargaficios AdmCargaficios) {
        for (Robot RobotActual : listaRobots) {
            if (!RobotActual.isEncendido() && !RobotActual.isConectado()) {
                Cargaficio cargaficio = AdmCargaficios.retornarCargaficioDisponible();
                if (cargaficio == null) {
                    return false; // no hay cargaficio disponible, abortar
                }
                RobotActual.conectarse_estacion(cargaficio);
            }
        }
        return true; // todos se conectaron
    }

    
    public boolean crearListaRobots(int cant, Regla regla){
        int tamInicial = listaRobots.size();  // Tamaño fijo inicial
        for (int i = (tamInicial+1); i<=(cant+tamInicial); i++){
            String procesador = "IAAA-" + i;
            
            //Para crear al robot se le asignan una lista de tareas
            Random random = new Random();
            int numero = random.nextInt(6) + 1;
            
            if (regla.getActiva()){
                Robot nRobot = new Robot(procesador, numero, regla.getValorMinimoBateria());
                listaRobots.add(nRobot);
            
            } else {
                Robot nRobot = new Robot(procesador, numero, 5);
                listaRobots.add(nRobot);
            }
            
            
            
        }
    return true;
    }
    
    public boolean mostrarRobots(){
        for (Robot unRobot : listaRobots){
            System.out.println(unRobot.toString());
        }
        return true;
    }
    
    
    @Override
    public String toString() { return "lista de Robots:\n" + listaRobots ; }
}


