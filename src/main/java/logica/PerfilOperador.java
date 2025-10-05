/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
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

    public boolean crearRobots(int nRobots, AdmRobots AdmRobots, Regla regla){
        return AdmRobots.crearListaRobots(nRobots, regla);
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
    
    public boolean simulate(AdmCiudadanos admCiudadanos, AdmRobots admRobots, AdmReglas admReglas, AdmDron admDron, AdmCargaficios admCargaficios, AdmAnomalias admAnomalias, CInteligencia cInteligencia, CantTareasUsadas cantTareasUsadas  ){
        admCiudadanos.pedirTareas(cantTareasUsadas); //esta funcion recorre cada ciudadano y mediante un random luego pide tarea a los robots que posea (si es que posee) (Ya tarea y todo eso ya hay procesos de registro)
        admRobots.conectarRobots(admCargaficios); //se llama al AdmCargaficios especifico y se conectan los robots, si este posee disponible, es un booleano, si da false es que no se pudieron conectar todos los robots descargados
        int valorMinimo = 25;
        if (admReglas.getListaReglas().get(0).getActiva()==true){ valorMinimo=admReglas.getListaReglas().get(0).getValorMinimoBateria(); }
        admAnomalias.activarAnomalias();
        boolean huboDron=admDron.enviarDronesAPatrullar(valorMinimo); //Explicame esto Sara ={{ also, debe ser aleatorio, salen a patrullar solo por UNA hora, y si llegan a 0% se asignan a un cargaficio
        if(!huboDron){System.out.println("Ningún dron salió a patrullar ya que no cumplian con el porcentaje de batería.\n");}
        ArrayList<Anomalia> listaAnomalias=admAnomalias.getListaAnomalias();
        ArrayList<Dron> listaDrones = admDron.getListaDrones();
        if(huboDron) {
            for (Dron unDron : listaDrones) {
                if (unDron.getEnPatrulla()) {
                    for (Anomalia anomalia : listaAnomalias) {
                        if (anomalia.getAnomaliaActiva()) {
                            Map<Anomalia, Dron> datos = admDron.retornarAnomalias(anomalia, unDron);
                            for (Anomalia llave : datos.keySet()) {
                                cInteligencia.crearRegistro(anomalia, datos.get(llave));}}}}}System.out.println(cInteligencia.getListaRegistros().toString());}
        else{
            for (Anomalia anomalia : listaAnomalias) {
                if (anomalia.getAnomaliaActiva()) {
                    Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomalia.getTipoAnomalia());
                    admAnomalias.agregarRegistroAnomaliaNoDetectada(registro);}}}
        //ahi abajo habia un return lo cambia a adm Cargaficiso
        System.out.println(admAnomalias.toString2());
        return admCargaficios.cargarDispositivosConectados();
        //carga todo lo que este conectado en la lista interna de cada Cargaficio
    }
}