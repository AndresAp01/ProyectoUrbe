package logica;

import modelo.Cargaficio;

import java.util.ArrayList;

public class PerfilAdmin {
    private AdmEdificio admEdificio;
    private AdmCargaficios admCargaficios;
    private AdmDron admDron;
    private AdmRobots admRobots;
    private AdmCiudadanos  admCiudadanos;

    PerfilAdmin(){
        this.admEdificio = new AdmEdificio();
        this.admCargaficios = new AdmCargaficios();
        this.admDron = new AdmDron();
        this.admRobots = new AdmRobots();
        this.admCiudadanos = new AdmCiudadanos();
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

    public String mostrarCargaficios(){
        return admCargaficios.toString();
    }

    public boolean crearDrones(int valorMinimoBateria){
        return admDron.crearListaDrones(admEdificio.retornaCantidadDeEdificios(),valorMinimoBateria);
    }

    public boolean asignarDronesAEdificios(){
        return admDron.asignarDronesAEdificios(admEdificio.getListaEdificios());
    }

    public boolean mostrarDrones(){
        return admDron.mostrarDrones();
    }



}
