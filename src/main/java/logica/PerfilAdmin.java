package logica;

import modelo.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class PerfilAdmin {
    private AdmEdificio admEdificio;
    private AdmCargaficios admCargaficios;
    private AdmDron admDron;
    private AdmRobots admRobots;
    private AdmCiudadanos  admCiudadanos;
    private AdmAnomalias admAnomalias;
    private AdmReglas admReglas;
    private CInteligencia  cInteligencia;
    private PerfilOperador perfilOperador;
    private PerfilGeneral perfilGeneral;
    private CantTareasUsadas cantTareasUsadas;

    public PerfilAdmin(){
        this.admEdificio = new AdmEdificio();
        this.admCargaficios = new AdmCargaficios();
        this.admDron = new AdmDron();
        this.admRobots = new AdmRobots();
        this.admCiudadanos = new AdmCiudadanos();
        this.admAnomalias = new AdmAnomalias();
        this.admReglas = new AdmReglas();
        this.cInteligencia = new CInteligencia();
        this.perfilOperador = new PerfilOperador();
        this.perfilGeneral = new PerfilGeneral();
        this.cantTareasUsadas = new CantTareasUsadas();
    }

    public AdmEdificio getAdmEdificio() {
        return admEdificio;
    }
    public AdmCargaficios getAdmCargaficios() {
        return admCargaficios;
    }
    public AdmDron getAdmDron() {
        return admDron;
    }
    public AdmRobots getAdmRobots() {
        return admRobots;
    }
    public AdmCiudadanos getAdmCiudadanos() {
        return admCiudadanos;
    }
    public AdmAnomalias getAdmAnomalias() {
        return admAnomalias;
    }
    public AdmReglas getAdmReglas() {
        return admReglas;
    }
    public  PerfilOperador getPerfilOperador() {
        return perfilOperador;
    }
    public CInteligencia getCInteligencia() {
        return cInteligencia;
    }
    public CantTareasUsadas getCantTareasUsadas(){
        return cantTareasUsadas;
    }

    //--------------------------------------- PARTE EXLUSIVAMENTE DE ADMINISTRADOR ---------------------------------------

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

    public boolean crearAnomalias(int[] index){
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

    //--------------------------------------- PARTE DE OPERADOR  ---------------------------------------

    public boolean crearDronesMedianteOperador(){
        return perfilOperador.crearDrones(getAdmEdificio(),getAdmDron());
    }

    public boolean simulateMedianteOperador(){
        return perfilOperador.simulate(getAdmCiudadanos(),getAdmRobots(),getAdmReglas(),getAdmDron(),getAdmCargaficios(), getAdmAnomalias(), getCInteligencia(), getCantTareasUsadas(), getAdmEdificio());
    }

    public boolean mostrarDronesMedianteOperador(){
        return perfilOperador.mostrarDrones(getAdmDron());
    }
    
    public boolean crearRobotsMedianteOperador(int nrobots){
        return perfilOperador.crearRobots(nrobots, getAdmRobots(), getAdmReglas().getListaReglas().get(1));
    }
    
    public boolean crearCiudadanosMedianteOperador(int nCiudadanos){
        return perfilOperador.crearCiudadanos(nCiudadanos, getAdmCiudadanos(), getAdmEdificio());
    }
    
    public boolean asignarRobotCiudadanoMedianteOperador(String id, String procesador){
        return perfilOperador.asignarRobotCiudadano(id, procesador, getAdmCiudadanos(), getAdmRobots());
    }
    
    public boolean mostrarRobotsMedianteOperador(){
        return perfilOperador.mostrarRobots(getAdmRobots());
    }
    
    public boolean mostrarCiudadanosMedianteOperador(){
        return perfilOperador.mostrarCiudadanos(getAdmCiudadanos());
    }

    //--------------------------------------- PARTE DE GENERAL  ---------------------------------------

    public int porcentajeRobotsAlertaMedianteGeneral() { return perfilGeneral.conseguirPorcentajeRobotsAlerta(getAdmRobots()); }

    public Map<Edificio, DatosRobotEdificio> obtenerDatosPorEdificioMedianteGeneral() { return perfilGeneral.obtenerDatosPorEdificio(getAdmEdificio()); }
    
    public ArrayList cantTareasUsadas(){return perfilGeneral.cantTareasUsadas(cantTareasUsadas);}
        
    public int cantCargaficiosDisponiblesMedianteGeneral() { return perfilGeneral.cantidadCargaficiosDisponibles(getAdmCargaficios()); }

    public int PorcentajeCargaficiosDisponiblesMedianteGeneral() { return perfilGeneral.porcentajeCargaficiosDisponibles(admCargaficios);}
    
    public ArrayList desgloseEnergiaCargaficioMedianteGeneral(String idCargaficio) { return perfilGeneral.desgloseEnergia(getAdmCargaficios(), idCargaficio);}
    
    public Map <Cargaficio, DatosCargaficioOcupacionEstado> obtenerDatosPorCargaficioMedianteGeneral() { return perfilGeneral.obtenerDatosPorCargaficio(admCargaficios);}
    
    public int edificiosImpactadosMedianteGeneral() { return perfilGeneral.edificiosImpactados(getAdmEdificio(),getAdmAnomalias()); }

    public ArrayList<ArrayList<Integer>> porTipoAnomaliaYEdificioMedianteGeneral () { return perfilGeneral.porTipoAnomaliaYEdificio(getAdmEdificio(), getAdmAnomalias()); }

    public ArrayList<ArrayList> porAccionEjecutadaMedianteGeneral() { return perfilGeneral.porAccionEjecutada(getCInteligencia(), getAdmAnomalias()); }

    public ArrayList<ArrayList> tablaEdificiosIncidentesMedianteGeneral() { return perfilGeneral.tablaEdificiosIncidentes(getAdmEdificio(), getAdmAnomalias(), getCInteligencia()); }

    public ArrayList<String> mayorReincidenciaMedianteGeneral() { return perfilGeneral.mayorReincidencia(getAdmEdificio(), getAdmAnomalias()); }
    
    public ArrayList ocupacionPorEdificioMedianteGeneral(){ return perfilGeneral.ocupacionPorEdificio(admEdificio);}
    
    public ArrayList desglosesEdificiosMedianteGeneral(String idEdificio){ return perfilGeneral.desgloseEdificios(getAdmEdificio(), idEdificio);}
    
    public ArrayList<Edificio> Top3MayorOcupacionMedianteGeneral(){ return perfilGeneral.ConseguirTop3MayorOcupacion(admEdificio);}
    
    public ArrayList<Edificio> Top3MayorCiudadanosSinRobotMedianteGeneral() { return perfilGeneral.ConseguirTop3MayorCiudadanosSinRobot(admEdificio); }
    
    public ArrayList<Edificio> Top3MayorCantRobotsAlertaMedianteGeneral() { return perfilGeneral.ConseguirTop3MayorRobotsAlerta(admEdificio);}
    
    public ArrayList InfoSobreAsignacionMedianteGeneral() { return perfilGeneral.InfoSobreAsignacion(admEdificio); }
    
}
