/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;
import modelo.Robot;

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
    
    @Override
    public String toString() { return "lista de Robots:\n" + listaRobots ; }
}


