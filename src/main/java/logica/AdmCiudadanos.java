package logica;

import java.util.ArrayList;
import java.util.Random;
import modelo.Ciudadano;
import modelo.Edificio;

/**
 * Clase que administra los ciudadanos de la ciudad.
 * Permite agregar, consultar, modificar, eliminar ciudadanos,
 * generar ciudadanos automáticamente y gestionar sus tareas.
 */
public class AdmCiudadanos {
    private ArrayList<Ciudadano> listaCiudadanos;
    private int total_tareas;
    private int total_tareas_rechazadas;

    /**
     * Constructor que inicializa la lista de ciudadanos.
     */
    public AdmCiudadanos(){
        this.listaCiudadanos = new ArrayList<Ciudadano>();
    }

    /**
     * Agrega un ciudadano si no existe previamente en la lista.
     * @param unCiudadano (Ciudadano) ciudadano a agregar.
     * @return (boolean) true si se agregó, false si ya existía.
     */
    public boolean agregar(Ciudadano unCiudadano){
        for (Ciudadano CiudadanoActual: listaCiudadanos)
            if (CiudadanoActual.equals(unCiudadano))
                return false;
        listaCiudadanos.add(unCiudadano);
        return true;
    }

    /**
     * Consulta un ciudadano por su ID.
     * @param elId (String) ID del ciudadano.
     * @return (Ciudadano) el ciudadano encontrado, null si no existe.
     */
    public Ciudadano consultar(String elId){
        for (Ciudadano Ciudadano : listaCiudadanos){
            if (elId.equals(Ciudadano.getId()))
                return Ciudadano;
        }
        return null;
    }

    /**
     * Modifica un ciudadano existente.
     * @param nuevoCiudadano (Ciudadano) ciudadano con los datos actualizados.
     * @return (boolean) true si se modificó correctamente, false si no se encontró.
     */
    public boolean modificar(Ciudadano nuevoCiudadano){
        for (int i = 0; i < listaCiudadanos.size(); i++){
            if (listaCiudadanos.get(i).equals(nuevoCiudadano)){
                listaCiudadanos.set(i, nuevoCiudadano);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un ciudadano por su ID.
     * @param elId (String) ID del ciudadano a eliminar.
     * @return (boolean) true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(String elId){
        for (int i = 0; i < listaCiudadanos.size(); i++){
            if (elId.equals(listaCiudadanos.get(i).getId())){
                listaCiudadanos.remove(i);
                return true;
            }
        }
        return false;
    }

    // ---------------- Funciones adicionales ----------------

    /**
     * Calcula el total de tareas realizadas por todos los ciudadanos.
     * @return (int) total de tareas hechas.
     */
    public int TotalTareasHechas(){
        for (Ciudadano CiudadanoActual: listaCiudadanos)
            total_tareas += CiudadanoActual.getTareas_hechas();
        return total_tareas;
    }

    /**
     * Calcula el total de tareas rechazadas por todos los ciudadanos.
     * @return (int) total de tareas rechazadas.
     */
    public int TotalTareasRechazadas(){
        for (Ciudadano CiudadanoActual: listaCiudadanos)
            total_tareas_rechazadas += CiudadanoActual.getTareas_rechazadas();
        return total_tareas_rechazadas;
    }

    /**
     * Solicita tareas a algunos ciudadanos aleatoriamente.
     * @param cantTareasUsadas (CantTareasUsadas) contador para registrar tareas usadas.
     */
    public void pedirTareas(CantTareasUsadas cantTareasUsadas){
        Random rand = new Random();
        for (Ciudadano CiudadanoActual: listaCiudadanos){
            int numeroRandom = rand.nextInt(5);
            if (numeroRandom == 3){
                CiudadanoActual.pedir_tarea(cantTareasUsadas);
            }
        }
    }

    /**
     * Crea automáticamente una cantidad de ciudadanos y los asigna a edificios disponibles.
     * @param AdmEdificio (AdmEdificio) administración de edificios.
     * @param cant (int) cantidad de ciudadanos a crear.
     * @return (boolean) true si se crearon todos correctamente, false si no había edificios disponibles.
     */
    public boolean crearListaCiudadanos(AdmEdificio AdmEdificio, int cant){
        for (int i = (listaCiudadanos.size()+1); i <= (cant + listaCiudadanos.size()); i++){
            Edificio EdificioDisponible = AdmEdificio.retornarEdificioDisponible();
            if (EdificioDisponible == null){
                return false;
            } 
            String id = "CEH-" + i;
            Ciudadano nCiudadano = new Ciudadano(id, EdificioDisponible);
            EdificioDisponible.agregarCiudadano(nCiudadano);
        }
        return true;
    }

    @Override
    public String toString() {
        return "lista de Ciudadanos:\n" + listaCiudadanos;
    }
}
