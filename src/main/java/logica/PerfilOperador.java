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

    private AdmEdificio AdmEdificio;
    private AdmDron AdmDron;
    private AdmRobots AdmRobots;
    private AdmCiudadanos AdmCiudadanos;
    private AdmCargaficios AdmCargaficios;

    public PerfilOperador(){
        this.AdmEdificio = new AdmEdificio();
        this.AdmDron = new AdmDron();
        this.AdmCiudadanos = new AdmCiudadanos();
        this.AdmRobots = new AdmRobots();
        this.AdmCargaficios = new AdmCargaficios();
    }

    public boolean crearDrones(){
        //se necesita en admEdificios una funcion que diga la cantidad de edificios
        return AdmDron.crearListaDrones(AdmEdificio.getListaEdificios().size()-1);
    }

    public boolean crearRobots(int nRobots){
        //se debe crear esta funcion en admRobots
        return AdmRobots.crearRobots(nRobots);
    }

    public boolean crearCiudadanos(int nCiudadanos){
        //se debe crear esta funcion en admCiudadanos
        return AdmCiudadanos.crearCiudadanos(nCiudadanos);
    }

    public boolean asignarRobotCiudadano(String id, String procesador){
        //crear esto y simulate, modificar uml pues solo se usa una funciones de crearCiudadanos/robots
        Ciudadano ciudadano = AdmCiudadanos.consultar(id);
        Robot robot = AdmRobots.consultar(procesador);

        return ciudadano.asignar_robot(robot);
    }

    public boolean simulate(){
        AdmCiudadanos.pedirTareas(); //esta funcion recorre cada ciudadano y mediante un random luego pide tarea a los robots que posea (si es que posee) (Ya tarea y todo eso ya hay procesos de registro)
        AdmDron.enviarDronesAPatrullar(0, listaAnomalias, listaRegistros); //Explicame esto Sara ={{ also, debe ser aleatorio, salen a patrullar solo por UNA hora, y si llegan a 0% se asignan a un cargaficio
        AdmCargaficios.cargarDispositivosConectados(); //carga todo lo que este conectado en la lista interna de cada Cargaficio
        AdmDron.encontrarAnomalias(listaAnomalias, listaRegistros); //explicame esta tambien Sara

    }

}