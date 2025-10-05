package logica;

import modelo.Anomalia;
import modelo.Dron;
import modelo.Edificio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Clase que administra los drones de la ciudad.
 * Permite crear drones, asignarlos a edificios, enviarlos a patrullar y obtener información sobre ellos.
 */
public class AdmDron {
    private ArrayList<Dron> listaDrones;

    /**
     * Constructor que inicializa la lista de drones.
     */
    public AdmDron(){
        this.listaDrones = new ArrayList<>();
    }

    /**
     * Devuelve la lista de drones existentes.
     * @return (ArrayList<Dron>) lista de drones.
     */
    public ArrayList<Dron> getListaDrones() {
        return listaDrones;
    }

    /**
     * Crea una lista de drones aleatoria según la cantidad de edificios.
     * @param cantEdificios (int) cantidad de edificios para calcular cantidad de drones.
     * @return (boolean) true siempre que se cree la lista.
     */
    public boolean crearListaDrones(int cantEdificios){
        int i = 0;
        Random rand = new Random();
        int tope = rand.nextInt(3,(cantEdificios*2)-1);
        while (i < tope){
            int horasVuelo = rand.nextInt(4)+1;
            int bateria = 25*horasVuelo;
            boolean estadoAlerta = false;
            boolean activo = true;
            if (bateria <= 25){
                estadoAlerta = true;
                activo = false;
            }
            Dron nuevoDron = new Dron("NVIDIAJetson"+(i+1), horasVuelo, bateria, estadoAlerta, activo,false);
            listaDrones.add(nuevoDron);
            i++;
        }
        return true;
    }

    /**
     * Asigna drones a los edificios de manera secuencial y aleatoria.
     * @param listaEdificios (ArrayList<Edificio>) lista de edificios.
     * @return (boolean) true cuando se asignan correctamente.
     */
    public boolean asignarDronesAEdificios(ArrayList<Edificio> listaEdificios){
        int i = 0;
        while (i < listaDrones.size()){
            for (Edificio unEdificio : listaEdificios) {
                if (unEdificio.getDrones().isEmpty()) {
                    ArrayList<Dron> nlista = unEdificio.getDrones();
                    nlista.add(listaDrones.get(i));
                    unEdificio.setDrones(nlista);
                    i++;
                }
            }
            for (Edificio unEdificio : listaEdificios){
                ArrayList<Dron> nlista = unEdificio.getDrones();
                nlista.add(listaDrones.get(i));
                unEdificio.setDrones(nlista);
                i++;
            }
        }
        return true;
    }

    /**
     * Retorna un mapa asociando una anomalía con un dron.
     * @param anomalia (Anomalia) anomalía detectada.
     * @param unDron (Dron) dron que detecta la anomalía.
     * @return (Map<Anomalia,Dron>) mapa con la anomalía y el dron.
     */
    public Map<Anomalia, Dron> retornarAnomalias(Anomalia anomalia, Dron unDron){
        Map<Anomalia,Dron> datos = new HashMap<>();
        System.out.println(anomalia.getAnomaliaActiva());
        datos.put(anomalia, unDron);
        return datos;
    }

    /**
     * Busca un dron apto que no esté en una lista temporal.
     * @param listaMomentanea (ArrayList<Dron>) lista de drones ya seleccionados.
     * @return (Dron) dron disponible, null si no hay.
     */
    public Dron buscarDronApto(ArrayList<Dron> listaMomentanea){
        Random rand = new Random();
        Dron unDron = listaDrones.get(rand.nextInt(1, listaDrones.size()));
        for (Dron otroDron : listaMomentanea) {
            if (unDron.equals(otroDron)) {
                return null;
            }
        }
        return unDron;
    }

    /**
     * Crea una lista temporal de drones aleatorios para patrullaje.
     * @return (ArrayList<Dron>) lista de drones seleccionados.
     */
    public ArrayList<Dron> crearListaMomentanea(){
        Random rand = new Random();
        int limite = rand.nextInt(1, listaDrones.size());
        ArrayList<Dron> listaMomentanea = new ArrayList<>();
        while(listaMomentanea.size() < limite){
            Dron elDron = buscarDronApto(listaMomentanea);
            while (elDron == null){
                elDron = buscarDronApto(listaMomentanea);
            }
            listaMomentanea.add(elDron);
        }
        return listaMomentanea;
    }

    /**
     * Envía drones a patrullar según el valor mínimo de batería.
     * @param valorMinimoBateria (int) batería mínima para patrullar.
     * @return (boolean) true si al menos un dron salió a patrullar.
     */
    public boolean enviarDronesAPatrullar(int valorMinimoBateria){
        boolean hubo = false;
        ArrayList<Dron> listaDeDronesRandom = crearListaMomentanea();
        for (Dron unDron : listaDeDronesRandom) {
            if ((unDron.getBateria() - 25) <= valorMinimoBateria) {
                unDron.enviarDronARecargar();
            } else {
                unDron.setEnPatrulla(true);
                unDron.setBateria(unDron.getBateria() - 25);
                unDron.setHorasVueloRestantes(unDron.getHorasVueloRestantes()-1);
                hubo = true;
                System.out.println(unDron.toString());
            }
        }
        return hubo;
    }

    /**
     * Muestra todos los drones por consola.
     * @return (boolean) true siempre.
     */
    public boolean mostrarDrones(){
        for (Dron unDron : listaDrones){
            System.out.println(unDron.toString());
        }
        return true;
    }
}
