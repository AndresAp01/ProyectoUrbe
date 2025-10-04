/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.Random;
import java.util.ArrayList;
import modelo.*;
/**
 *
 * @author linuxman
 */
public class PerfilOperador {

    public boolean crearDrones(AdmEdificio admEdificio, AdmDron admDron){
        //se necesita en admEdificios una funcion que diga la cantidad de edificios
        return admDron.crearListaDrones(admEdificio.getListaEdificios().size());
    }

    public boolean mostrarDrones(AdmDron admDron){
        return admDron.mostrarDrones();
    }

    public boolean crearRobots(int nRobots, AdmRobots AdmRobots){
        return AdmRobots.crearListaRobots(nRobots);
    }

    public boolean crearCiudadanos(int nCiudadanos, AdmCiudadanos AdmCiudadanos, AdmEdificio AdmEdificio){
       
        return AdmCiudadanos.crearListaCiudadanos(AdmEdificio, nCiudadanos); 
    }
    

    public boolean asignarRobotCiudadano(String id, String procesador, AdmCiudadanos AdmCiudadanos, AdmRobots AdmRobots){
        //crear esto y simulate, modificar uml pues solo se usa una funciones de crearCiudadanos/robots
        Ciudadano ciudadano = AdmCiudadanos.consultar(id);
        Robot robot = AdmRobots.consultar(procesador);

        return ciudadano.asignar_robot(robot);
    }
    
    public boolean simulate(AdmCiudadanos admCiudadanos,AdmRobots admRobots,AdmReglas admReglas,AdmDron admDron, AdmCargaficios admCargaficios){
        admCiudadanos.pedirTareas(); //esta funcion recorre cada ciudadano y mediante un random luego pide tarea a los robots que posea (si es que posee) (Ya tarea y todo eso ya hay procesos de registro)
        admRobots.conectarRobots(admCargaficios); //se llama al AdmCargaficios especifico y se conectan los robots, si este posee disponible, es un booleano, si da false es que no se pudieron conectar todos los robots descargados
        int valorMinimo = 25;
        for (Regla regla : admReglas.getListaReglas()){
            if (regla.getNombre().equals("Regla 1")&&regla.getActiva()==true){
                valorMinimo=regla.getValorMinimoBateria();
            }
        }
        //ahi abajo habia un return lo cambia a adm Cargaficiso
        admDron.enviarDronesAPatrullar(valorMinimo); //Explicame esto Sara ={{ also, debe ser aleatorio, salen a patrullar solo por UNA hora, y si llegan a 0% se asignan a un cargaficio
        return admCargaficios.cargarDispositivosConectados(); //carga todo lo que este conectado en la lista interna de cada Cargaficio
        //AdmDron.encontrarAnomalias(listaAnomalias, listaRegistros); //explicame esta tambien Sara


    }

}