package logica;

import modelo.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase que representa el perfil del administrador del sistema.
 * Esta clase centraliza la gestión de entidades como edificios, cargaficios, drones, robots,
 * ciudadanos, anomalías y reglas. Además, integra perfiles de operador y general para permitir
 * operaciones administrativas, de simulación y de consulta de información.
 */
public class PerfilAdmin {

    private AdmEdificio admEdificio;
    private AdmCargaficios admCargaficios;
    private AdmDron admDron;
    private AdmRobots admRobots;
    private AdmCiudadanos admCiudadanos;
    private AdmAnomalias admAnomalias;
    private AdmReglas admReglas;
    private CInteligencia cInteligencia;
    private PerfilOperador perfilOperador;
    private PerfilGeneral perfilGeneral;
    private CantTareasUsadas cantTareasUsadas;

    /**
     * Constructor por defecto. Inicializa todos los administradores y perfiles del sistema.
     */
    public PerfilAdmin() {
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

    /** @return administrador de edificios */
    public AdmEdificio getAdmEdificio() { return admEdificio; }

    /** @return administrador de cargaficios */
    public AdmCargaficios getAdmCargaficios() { return admCargaficios; }

    /** @return administrador de drones */
    public AdmDron getAdmDron() { return admDron; }

    /** @return administrador de robots */
    public AdmRobots getAdmRobots() { return admRobots; }

    /** @return administrador de ciudadanos */
    public AdmCiudadanos getAdmCiudadanos() { return admCiudadanos; }

    /** @return administrador de anomalías */
    public AdmAnomalias getAdmAnomalias() { return admAnomalias; }

    /** @return administrador de reglas */
    public AdmReglas getAdmReglas() { return admReglas; }

    /** @return perfil del operador */
    public PerfilOperador getPerfilOperador() { return perfilOperador; }

    /** @return componente de inteligencia */
    public CInteligencia getCInteligencia() { return cInteligencia; }

    /** @return contador de tareas usadas */
    public CantTareasUsadas getCantTareasUsadas() { return cantTareasUsadas; }

    // --------------------------------------- PARTE EXCLUSIVAMENTE DE ADMINISTRADOR ---------------------------------------

    /**
     * Crea edificios según la opción especificada.
     * @param opcion (int) 0 para generar una cantidad aleatoria, o un número entre 3 y 10 para indicar una cantidad específica.
     * @return (boolean) true si la creación fue exitosa, false en caso contrario.
     */
    public boolean crearEdificios(int opcion) {
        return admEdificio.agregarEdificios(opcion);
    }

    /**
     * Muestra la información de los edificios existentes.
     * @return (String) representación textual de los edificios.
     */
    public String mostrarEdificios() {
        return admEdificio.toString();
    }

    /**
     * Crea cargaficios según la opción especificada.
     * @param opcion (int) 0 para generar una cantidad aleatoria o un número fijo de cargaficios.
     * @return (boolean) true si la creación fue exitosa.
     */
    public boolean crearCargaficios(int opcion) {
        return admCargaficios.agregarCargaficios(opcion);
    }

    /**
     * Retorna la lista de cargaficios para modificar su estado desde la interfaz.
     * @return (ArrayList&lt;Cargaficio&gt;) lista de cargaficios actuales.
     */
    public ArrayList<Cargaficio> modificarEstadoCargaficios() {
        return admCargaficios.retornarListaCargaficios();
    }

    /**
     * Crea una lista de anomalías con base en índices dados.
     * @param index (int[]) índices de anomalías a crear.
     * @return (boolean) true si las anomalías fueron creadas exitosamente.
     */
    public boolean crearAnomalias(int[] index) {
        return admAnomalias.crearListaAnomalias(index);
    }

    /**
     * Muestra las anomalías existentes.
     * @return (String) representación textual de las anomalías.
     */
    public String mostrarAnomalias() {
        return admAnomalias.toString();
    }

    /**
     * Crea una nueva acción asociada a un tipo de anomalía.
     * @param nuevaAccion (String) descripción de la acción.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía a la que se asigna la acción.
     * @return (boolean) true si la acción fue creada exitosamente.
     */
    public boolean crearNuevaAccion(String nuevaAccion, TipoAnomalia tipoAnomalia) {
        return admAnomalias.asignarNuevaAccion(nuevaAccion, tipoAnomalia);
    }

    /**
     * Elimina una acción de un tipo de anomalía.
     * @param indexEliminar (int) índice de la acción a eliminar.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía afectado.
     * @return (boolean) true si la eliminación fue exitosa.
     */
    public boolean eliminarAccion(int indexEliminar, TipoAnomalia tipoAnomalia) {
        return admAnomalias.eliminarAccion(indexEliminar, tipoAnomalia);
    }

    /**
     * Modifica una acción existente asociada a un tipo de anomalía.
     * @param indexModificar (int) índice de la acción a modificar.
     * @param modificacion (String) nueva descripción de la acción.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía afectado.
     * @return (boolean) true si la modificación fue exitosa.
     */
    public boolean modificarAccion(int indexModificar, String modificacion, TipoAnomalia tipoAnomalia) {
        return admAnomalias.modificarAccion(indexModificar, modificacion, tipoAnomalia);
    }

    /**
     * Crea la lista de reglas del sistema.
     * @return (boolean) true si se generó correctamente.
     */
    public boolean crearListaReglas() {
        return admReglas.crearListaReglas();
    }

    /**
     * Activa reglas según los parámetros dados.
     * @param op (int) número de la regla a activar.
     * @param valorMinimo (int) valor mínimo requerido para la activación.
     * @return (boolean) true si la regla fue activada correctamente.
     */
    public boolean activarReglas(int op, int valorMinimo) {
        return admReglas.activarReglas(op, valorMinimo);
    }

    /**
     * Desactiva una regla específica.
     * @param op (int) número de la regla a desactivar.
     * @return (boolean) true si fue desactivada correctamente.
     */
    public boolean desactivarReglas(int op) {
        return admReglas.desactivarReglas(op);
    }

    /**
     * Muestra la lista actual de reglas.
     * @return (String) representación textual de las reglas.
     */
    public String mostrarListaReglas() {
        return admReglas.toString();
    }

    /**
     * Muestra la información de todos los cargaficios.
     * @return (String) representación textual de los cargaficios.
     */
    public String mostrarCargaficios() {
        return admCargaficios.toString();
    }

    // --------------------------------------- PARTE DE OPERADOR ---------------------------------------

    /**
     * Crea drones mediante las funciones del perfil operador.
     * @return (boolean) true si los drones fueron creados correctamente.
     */
    public boolean crearDronesMedianteOperador() {
        return perfilOperador.crearDrones(getAdmEdificio(), getAdmDron());
    }

    /**
     * Ejecuta la simulación mediante el perfil operador.
     * @return (boolean) true si la simulación fue realizada correctamente.
     */
    public boolean simulateMedianteOperador() {
        return perfilOperador.simulate(
                getAdmCiudadanos(),
                getAdmRobots(),
                getAdmReglas(),
                getAdmDron(),
                getAdmCargaficios(),
                getAdmAnomalias(),
                getCInteligencia(),
                getCantTareasUsadas()
        );
    }

    /**
     * Muestra los drones creados mediante el operador.
     * @return (boolean) true si se mostraron correctamente.
     */
    public boolean mostrarDronesMedianteOperador() {
        return perfilOperador.mostrarDrones(getAdmDron());
    }

    /**
     * Crea robots mediante el perfil operador.
     * @param nrobots (int) número de robots a crear.
     * @return (boolean) true si los robots fueron creados exitosamente.
     */
    public boolean crearRobotsMedianteOperador(int nrobots) {
        return perfilOperador.crearRobots(nrobots, getAdmRobots(), getAdmReglas().getListaReglas().get(1));
    }

    /**
     * Crea ciudadanos mediante el perfil operador.
     * @param nCiudadanos (int) número de ciudadanos a crear.
     * @return (boolean) true si los ciudadanos fueron creados correctamente.
     */
    public boolean crearCiudadanosMedianteOperador(int nCiudadanos) {
        return perfilOperador.crearCiudadanos(nCiudadanos, getAdmCiudadanos(), getAdmEdificio());
    }

    /**
     * Asigna un robot a un ciudadano mediante el operador.
     * @param id (String) identificador del ciudadano.
     * @param procesador (String) procesador del robot a asignar.
     * @return (boolean) true si la asignación fue exitosa.
     */
    public boolean asignarRobotCiudadanoMedianteOperador(String id, String procesador) {
        return perfilOperador.asignarRobotCiudadano(id,
