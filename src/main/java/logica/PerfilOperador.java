package logica;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import modelo.*;

/**
 * Clase que representa el perfil de operador.
 * Proporciona métodos para la creación de drones, robots, ciudadanos,
 * asignación de robots a ciudadanos y simulación de operaciones.
 */
public class PerfilOperador {

    /**
     * Crea drones según la cantidad de edificios existentes.
     * @param admEdificio (AdmEdificio) administrador de edificios.
     * @param admDron (AdmDron) administrador de drones.
     * @return (boolean) true si se crearon correctamente.
     */
    public boolean crearDrones(AdmEdificio admEdificio, AdmDron admDron) {
        return admDron.crearListaDrones(admEdificio.getListaEdificios().size());
    }

    /**
     * Muestra los drones existentes.
     * @param admDron (AdmDron) administrador de drones.
     * @return (boolean) true si la operación fue exitosa.
     */
    public boolean mostrarDrones(AdmDron admDron) {
        return admDron.mostrarDrones();
    }

    /**
     * Crea una cantidad específica de robots asociados a una regla.
     * @param nRobots (int) cantidad de robots a crear.
     * @param AdmRobots (AdmRobots) administrador de robots.
     * @param regla (Regla) regla para la creación de robots.
     * @return (boolean) true si los robots se crearon correctamente.
     */
    public boolean crearRobots(int nRobots, AdmRobots AdmRobots, Regla regla) {
        return AdmRobots.crearListaRobots(nRobots, regla);
    }

    /**
     * Crea ciudadanos y los asigna a edificios.
     * @param nCiudadanos (int) cantidad de ciudadanos a crear.
     * @param AdmCiudadanos (AdmCiudadanos) administrador de ciudadanos.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (boolean) true si los ciudadanos se crearon correctamente.
     */
    public boolean crearCiudadanos(int nCiudadanos, AdmCiudadanos AdmCiudadanos, AdmEdificio AdmEdificio) {
        return AdmCiudadanos.crearListaCiudadanos(AdmEdificio, nCiudadanos);
    }

    /**
     * Asigna un robot a un ciudadano específico.
     * @param id (String) ID del ciudadano.
     * @param procesador (String) ID del robot/procesador.
     * @param AdmCiudadanos (AdmCiudadanos) administrador de ciudadanos.
     * @param AdmRobots (AdmRobots) administrador de robots.
     * @return (boolean) true si la asignación fue exitosa.
     */
    public boolean asignarRobotCiudadano(String id, String procesador, AdmCiudadanos AdmCiudadanos, AdmRobots AdmRobots) {
        Ciudadano ciudadano = AdmCiudadanos.consultar(id);
        Robot robot = AdmRobots.consultar(procesador);
        return ciudadano.asignar_robot(robot);
    }

    /**
     * Simula la operación de la ciudad: asignación de tareas, patrullaje de drones
     * y registros de anomalías detectadas o no detectadas.
     * @param admCiudadanos (AdmCiudadanos) administrador de ciudadanos.
     * @param admRobots (AdmRobots) administrador de robots.
     * @param admReglas (AdmReglas) administrador de reglas.
     * @param admDron (AdmDron) administrador de drones.
     * @param admCargaficios (AdmCargaficios) administrador de cargaficios.
     * @param admAnomalias (AdmAnomalias) administrador de anomalías.
     * @param cInteligencia (CInteligencia) componente de inteligencia para registros.
     * @param cantTareasUsadas (CantTareasUsadas) registro de tareas usadas.
     * @return (boolean) true si los dispositivos conectados fueron cargados correctamente.
     */
    public boolean simulate(AdmCiudadanos admCiudadanos, AdmRobots admRobots, AdmReglas admReglas, 
                            AdmDron admDron, AdmCargaficios admCargaficios, AdmAnomalias admAnomalias, 
                            CInteligencia cInteligencia, CantTareasUsadas cantTareasUsadas) {

        // Los ciudadanos piden tareas a los robots que poseen
        admCiudadanos.pedirTareas(cantTareasUsadas);

        // Se conectan los robots a los cargaficios disponibles
        admRobots.conectarRobots(admCargaficios);

        // Configuración del valor mínimo de batería para patrullaje
        int valorMinimo = 25;
        if (admReglas.getListaReglas().get(0).getActiva()) {
            valorMinimo = admReglas.getListaReglas().get(0).getValorMinimoBateria();
        }

        // Activación de anomalías
        admAnomalias.activarAnomalias();

        // Envío de drones a patrullar
        boolean huboDron = admDron.enviarDronesAPatrullar(valorMinimo);
        if (!huboDron) {
            System.out.println("Ningún dron salió a patrullar ya que no cumplían con el porcentaje de batería.\n");
        }

        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        ArrayList<Dron> listaDrones = admDron.getListaDrones();

        if (huboDron) {
            for (Dron unDron : listaDrones) {
                if (unDron.getEnPatrulla()) {
                    for (Anomalia anomalia : listaAnomalias) {
                        if (anomalia.getAnomaliaActiva()) {
                            Map<Anomalia, Dron> datos = admDron.retornarAnomalias(anomalia, unDron);
                            for (Anomalia llave : datos.keySet()) {
                                cInteligencia.crearRegistro(anomalia, datos.get(llave));
                            }
                        }
                    }
                }
            }
            System.out.println(cInteligencia.getListaRegistros().toString());
        } else {
            for (Anomalia anomalia : listaAnomalias) {
                if (anomalia.getAnomaliaActiva()) {
                    Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomalia.getTipoAnomalia());
                    admAnomalias.agregarRegistroAnomaliaNoDetectada(registro);
                }
            }
        }

        System.out.println(admAnomalias.toString2());

        // Carga los dispositivos conectados en los cargaficios
        return admCargaficios.cargarDispositivosConectados();
    }
}
