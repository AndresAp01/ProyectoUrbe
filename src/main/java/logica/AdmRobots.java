/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;

import modelo.Robot;
import modelo.Cargaficio;

import java.util.Random;

/**
 *
 * @author linuxman
 * Solo para guardar la lista de los robots
 */


public class AdmRobots {
    private ArrayList<Robot> listaRobots;
    private int total_robots;
    private int total_robots_alerta;
    
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
        for (Robot RobotActual: listaRobots)
            total_robots += 1;
        return total_robots;
    }
    
    public int TotalRobotsAlerta(){
        for (Robot RobotActual: listaRobots)
            if (RobotActual.isSi_esta_en_alerta()){
                total_robots_alerta += 1;
            }
        return total_robots_alerta;
    }
    
    public boolean conectarRobots(AdmCargaficios AdmCargaficios){
        for (Robot RobotActual: listaRobots){
            if (!RobotActual.isEncendido() && !RobotActual.isConectado()){
                Cargaficio cargaficio = AdmCargaficios.retornarCargaficioDisponible();
                RobotActual.conectarse_estacion(cargaficio); //lo conecta al que esta disponible
            } else {
                return false; //si nos e conecta hay mas robots que cargaficios disponibles y retorna false
            }
        }
        return true; //todos se conectaron hay suficientes cargaficios para todos.
    }
    
    public boolean crearListaRobots(int cant){
        
        for (int i = (listaRobots.size()+1); i<=(cant+listaRobots.size()+1); i++){
            String procesador = "IAAA-" + i;
            
            //Para crear al robot se le asignan una lista de tareas
            Random random = new Random();
            int numero = random.nextInt(6) + 1;
            
            
            Robot nRobot = new Robot(procesador, numero);
            i++;
            
        }
    return true;
    }
    
    
    
    @Override
    public String toString() { return "lista de Robots:\n" + listaRobots ; }
}


