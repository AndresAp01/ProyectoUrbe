package logica;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import modelo.Anomalia;
import modelo.Dron;
import modelo.Registro;

public class CInteligencia {
    private ArrayList<Registro> listaRegistros;

    public CInteligencia() {
        listaRegistros = new ArrayList<>();
    }
    public ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    //----------------------------------------------------------------------------------------------------

    public String tomarDesicion(Anomalia anomaliaDetectada){
        Random rand = new Random();
        int largo = anomaliaDetectada.getListaAcciones().size();package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import modelo.Anomalia;
import modelo.Dron;
import modelo.Registro;

/**
 * Clase que representa el centro de inteligencia encargado de gestionar
 * decisiones tomadas ante anomalías detectadas por drones.
 * Permite crear registros de acciones y mantener un historial de decisiones.
 */
public class CInteligencia {

    /** Lista de registros de decisiones tomadas por los drones ante anomalías. */
    private ArrayList<Registro> listaRegistros;

    /**
     * Constructor que inicializa la lista de registros vacía.
     */
    public CInteligencia() {
        listaRegistros = new ArrayList<>();
    }

    /**
     * Obtiene la lista completa de registros.
     * @return Lista de registros.
     */
    public ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    // ----------------------------------------------------------------------------------------------------
    // Funciones principales

    /**
     * Selecciona aleatoriamente una acción de la lista de acciones disponibles
     * para la anomalía detectada.
     * @param anomaliaDetectada La anomalía sobre la cual se debe tomar la acción.
     * @return La acción seleccionada aleatoriamente.
     */
    public String tomarDesicion(Anomalia anomaliaDetectada){
        Random rand = new Random();
        int largo = anomaliaDetectada.getListaAcciones().size();
        int index = rand.nextInt(largo);
        return anomaliaDetectada.getListaAcciones().get(index);
    }

    /**
     * Crea un registro de la acción tomada por un dron ante una anomalía.
     * La anomalía se desactiva después de registrar la acción.
     * @param anomaliaDetectada La anomalía detectada.
     * @param dron El dron que detectó la anomalía y ejecutó la acción.
     * @return true si el registro se creó correctamente.
     */
    public boolean crearRegistro(Anomalia anomaliaDetectada, Dron dron){
        String accionTomada = tomarDesicion(anomaliaDetectada);
        Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomaliaDetectada.getTipoAnomalia(), dron, accionTomada);
        listaRegistros.add(registro);
        anomaliaDetectada.setAnomaliaActiva(false);
        return true;
    }

    /**
     * Representación en String de todos los registros almacenados.
     * @return String con la lista de registros.
     */
    @Override
    public String toString(){
        return "Lista de Registros: "+listaRegistros;
    }
}

        int index = rand.nextInt(largo);
        return anomaliaDetectada.getListaAcciones().get(index);
    }

    public boolean crearRegistro(Anomalia anomaliaDetectada, Dron dron){
        String accionTomada = tomarDesicion(anomaliaDetectada);
        Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomaliaDetectada.getTipoAnomalia(), dron, accionTomada);
        listaRegistros.add(registro);
        anomaliaDetectada.setAnomaliaActiva(false);
        return true;
    }

    public String toString(){
        return "Lista de Registros: "+listaRegistros;
    }
}
