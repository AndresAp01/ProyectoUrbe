package logica;

import java.util.ArrayList;
import modelo.Edificio;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Optional;

/**
 * Clase que administra la colección de edificios y proporciona
 * métodos para agregar, modificar, eliminar y obtener estadísticas de los edificios.
 * También permite obtener rankings y cálculos relacionados con ocupación, ciudadanos y robots.
 * 
 * Autor: linuxman
 */
public class AdmEdificio {

    /** Lista de todos los edificios gestionados. */
    private ArrayList<Edificio> listaEdificios;

    /** Acumulador de problemas sucedidos en los edificios. */
    private int total_problemas_edificios;

    /**
     * Constructor que inicializa la lista de edificios.
     */
    public AdmEdificio(){
        this.listaEdificios = new ArrayList<Edificio>();
    }

    /**
     * Obtiene la lista completa de edificios.
     * @return ArrayList de Edificio.
     */
    public ArrayList<Edificio> getListaEdificios(){
        return this.listaEdificios;
    }

    // ------------------ CRUD de Edificios ------------------

    /**
     * Agrega edificios a la lista con nombres predefinidos.
     * Si cantidad = 0, agrega un número aleatorio entre 3 y 10.
     * @param cantidad Número de edificios a agregar.
     * @return true siempre que se ejecute.
     */
    public boolean agregarEdificios(int cantidad){
        String[] nombresEdificios = {"Residencial Los Robles", "Condominio Vista Del Valle", "Torres Del Lago", "Apartamentos La Hacienda", "Residencial El Mirador", "Condominio Jardines Del Sol", "Torres Altavista", "Residencial Palmas Del Sur", "Condominio Bosques De La Montaña", "Apartamentos Brisas Del Rio"};
        int i=0;
        Random rand =  new Random();
        if (cantidad==0){
            int tope=rand.nextInt(3,10);
            while (i < tope) {
                Edificio edificio = new Edificio("Ed" + (i + 1), nombresEdificios[i]);
                listaEdificios.add(edificio);
                i++;
            }
        }
        else {
            while (i < cantidad) {
                Edificio edificio = new Edificio("Ed" + (i + 1), nombresEdificios[i]);
                listaEdificios.add(edificio);
                i++;
            }
        }
       return true;
    }

    /**
     * Busca un edificio por su ID.
     * @param elId ID del edificio a consultar.
     * @return Edificio si se encuentra; null si no.
     */
    public Edificio consultar(String elId){
        for (int i = 0; i<listaEdificios.size(); i++){
            Edificio Edificio = listaEdificios.get(i);
            if (elId.equals(Edificio.getId()))
                return Edificio;
        }
        return null;
    }

    /**
     * Modifica un edificio existente.
     * @param nuevoEdificio Edificio con los datos actualizados.
     * @return true si se modificó; false si no se encontró.
     */
    public boolean modificar(Edificio nuevoEdificio){
        for (int i = 0; i < listaEdificios.size(); i++){
            if (listaEdificios.get(i).equals(nuevoEdificio)){
                listaEdificios.set(i, nuevoEdificio);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un edificio por su ID.
     * @param elId ID del edificio a eliminar.
     * @return true si se eliminó; false si no se encontró.
     */
    public boolean eliminar(String elId){
        for (int i = 0; i < listaEdificios.size(); i++){
            if (elId.equals(listaEdificios.get(i).getId())){
                listaEdificios.remove(i);
                return true;
            }
        }
        return false;
    }

    // ------------------ Estadísticas y Cálculos ------------------

    /**
     * Retorna la suma de problemas sucedidos en todos los edificios.
     * @return total de problemas de los edificios.
     */
    public int TotalProblemasEdificios(){
        for (Edificio EdificioActual: listaEdificios)
            total_problemas_edificios += EdificioActual.getN_problemas_sucedidos();
        return total_problemas_edificios;
    }

    /**
     * Retorna la cantidad total de edificios.
     * @return número de edificios.
     */
    public int retornaCantidadDeEdificios (){
        return listaEdificios.size();
    }

    /**
     * Calcula cuántos edificios tienen problemas registrados.
     * @return número de edificios impactados.
     */
    public int edificiosImpactados(){
        int problemas = 0;
        for (Edificio EdificioActual: listaEdificios){
            if (EdificioActual.getN_problemas_sucedidos() > 0){
                problemas += 1;
            }
        }
        return problemas;
    }

    // ------------------ Rankings Top 3 ------------------

    /**
     * Obtiene los 3 edificios con mayor ocupación.
     * @return ArrayList de los 3 edificios con mayor porcentaje de ocupación.
     */
    public ArrayList<Edificio> obtenerTop3EdificiosOcupacion() {
        return listaEdificios.stream()
                .sorted(Comparator.comparingInt(Edificio::porcentajeOcupacion).reversed())
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Obtiene los 3 edificios con más ciudadanos sin robot asignado.
     * @return ArrayList de los 3 edificios con más ciudadanos sin robot.
     */
    public ArrayList<Edificio> obtenerTop3EdificiosCiudadanosSinRobot() {
        return listaEdificios.stream()
                .sorted(Comparator.comparingInt(Edificio::porcentajeCiudadanosSinRobot).reversed())
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Obtiene los 3 edificios con más robots en alerta.
     * @return ArrayList de los 3 edificios con más robots en alerta.
     */
    public ArrayList<Edificio> obtenerTop3EdificiosRobotAlerta() {
        return listaEdificios.stream()
                .sorted(Comparator.comparingInt(Edificio::robots_en_alerta).reversed())
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // ------------------ Promedios ------------------

    /**
     * Calcula la media de robots por edificio.
     * @return promedio de robots.
     */
    public int mediaRobots(){
        int mediaR = 0;
        for (Edificio EdificioActual: listaEdificios){
            mediaR += EdificioActual.getN_robots_total();
        }
        mediaR = mediaR / listaEdificios.size();
        return mediaR;
    }

    /**
     * Calcula la media de ciudadanos por edificio.
     * @return promedio de ciudadanos.
     */
    public int mediaCiudadanos(){
        int mediaC = 0;
        for (Edificio EdificioActual: listaEdificios){
            mediaC += EdificioActual.getEspacios_ocupados();
        }
        mediaC = mediaC / listaEdificios.size();
        return mediaC;
    }

    // ------------------ Consultas por máximo ------------------

    /**
     * Obtiene el edificio con más robots.
     * @return Optional con el edificio que tiene más robots.
     */
    public Optional<Edificio> obtenerEdificioConMasRobots() {
        return listaEdificios.stream()
                .max(Comparator.comparingInt(Edificio::robots_totales));
    }

    /**
     * Obtiene el edificio con más ciudadanos.
     * @return Optional con el edificio que tiene más ciudadanos.
     */
    public Optional<Edificio> obtenerEdificioConMasCiudadanos() {
        return listaEdificios.stream()
                .max(Comparator.comparingInt(Edificio::getEspacios_ocupados));
    }

    /**
     * Retorna un edificio disponible que no esté lleno.
     * @return Edificio disponible o null si no hay ninguno.
     */
    public Edificio retornarEdificioDisponible(){
        for (Edificio EdificioActual: listaEdificios){
            if (!EdificioActual.isSi_esta_lleno()){
                return EdificioActual;
            }
        }
        return null;
    }

    /**
     * Representación en String de la lista de edificios.
     * @return String con todos los edificios.
     */
    @Override
    public String toString() {
        return "lista de Edificios:\n" + listaEdificios+"\n";
    }
}
