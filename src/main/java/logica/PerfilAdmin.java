package logica;

import modelo.Cargaficio;
import modelo.TipoAnomalia;

import java.util.ArrayList;

public class PerfilAdmin {
    private AdmEdificio admEdificio;
    private AdmCargaficios admCargaficios;
    private AdmDron admDron;
    private AdmRobots admRobots;
    private AdmCiudadanos  admCiudadanos;
    private AdmAnomalias admAnomalias;
    private AdmReglas admReglas;

    PerfilAdmin(){
        this.admEdificio = new AdmEdificio();
        this.admCargaficios = new AdmCargaficios();
        this.admDron = new AdmDron();
        this.admRobots = new AdmRobots();
        this.admCiudadanos = new AdmCiudadanos();
        this.admAnomalias = new AdmAnomalias();
        this.admReglas = new AdmReglas();
    }

    //la variable opcion sera 0 si decide que quiere generar una cantidad random,
    // cualquier numero entre 3 y 10 indica la cantidad especifica de edificios a crear
    public boolean crearEdificios(int opcion){
        if (opcion==0){
            return admEdificio.agregarEdificios(opcion);
        }
        else{
            return admEdificio.agregarEdificios(opcion);
        }
    }

    public String mostrarEdificios(){
        return admEdificio.toString();
    }

    public boolean crearCargaficios(int opcion){
        if (opcion==0){
        return admCargaficios.agregarCargaficios(opcion);
    }
        else {
            return admCargaficios.agregarCargaficios(opcion);
        }
    }

    //Devuelve la lista para hacer un for y modificar desde la interfaz el estado de cada cargaficio generado
    public ArrayList<Cargaficio> modificarEstadoCargaficios(){
        return admCargaficios.retornarListaCargaficios();
    }

    public boolean crearAnomalias(/*int[] index*/){
        int[] index = {1,2,3};
        return admAnomalias.crearListaAnomalias(index);
    }

    public String mostrarAnomalias(){
        return  admAnomalias.toString();
    }

    public boolean crearNuevaAccion(String nuevaAccion, TipoAnomalia tipoAnomalia){
        return admAnomalias.asignarNuevaAccion(nuevaAccion, tipoAnomalia);
    }

    public boolean eliminarAccion(int indexEliminar, TipoAnomalia tipoAnomalia) {
        return admAnomalias.eliminarAccion(indexEliminar, tipoAnomalia);
    }

    public boolean modificarAccion(int indexModificar, String modificacion, TipoAnomalia tipoAnomalia) {
        return admAnomalias.modificarAccion(indexModificar, modificacion, tipoAnomalia);
    }

    public boolean crearListaReglas(){
        return admReglas.crearListaReglas();
    }

    public boolean activarReglas(int op, int valorMinimo){
        return admReglas.activarReglas(op, valorMinimo);
    }

    public boolean desactivarReglas(int op) {
        return admReglas.desactivarReglas(op);
    }

    public String mostrarListaReglas(){
        return admReglas.toString();
    }

    public String mostrarCargaficios(){
        return admCargaficios.toString();
    }

}
