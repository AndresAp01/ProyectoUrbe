package logica;

import modelo.Anomalia;
import modelo.Registro;
import modelo.TipoAnomalia;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que administra las anomalías de la ciudad.
 * Permite crear, modificar y asignar acciones a anomalías, así como registrar
 * anomalías no detectadas y activar aleatoriamente anomalías.
 */
public class AdmAnomalias {

    private ArrayList<Anomalia> listaAnomalias;
    private ArrayList<Registro> listaAnomaliasNoDetectadas;

    /**
     * Constructor que inicializa las listas de anomalías y registros no detectados.
     */
    public AdmAnomalias() {
        this.listaAnomalias = new ArrayList<>();
        this.listaAnomaliasNoDetectadas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de anomalías existentes.
     * @return (ArrayList<Anomalia>) lista de anomalías.
     */
    public ArrayList<Anomalia> getListaAnomalias() {
        return listaAnomalias;
    }

    /**
     * Agrega un registro de anomalía no detectada.
     * @param registro (Registro) registro de anomalía no detectada.
     * @return (boolean) true siempre que se agregue correctamente.
     */
    public boolean agregarRegistroAnomaliaNoDetectada(Registro registro) {
        listaAnomaliasNoDetectadas.add(registro);
        return true;
    }

    /**
     * Obtiene la lista de anomalías no detectadas.
     * @return (ArrayList<Registro>) lista de registros no detectados.
     */
    public ArrayList<Registro> getListaAnomaliasNoDetectadas() {
        return listaAnomaliasNoDetectadas;
    }

    /**
     * Crea anomalías según los índices proporcionados.
     * @param listaIndex (int[]) índices de tipos de anomalías a crear.
     * @return (boolean) true si se agregaron correctamente.
     */
    public boolean crearListaAnomalias(int[] listaIndex) {
        TipoAnomalia[] tipoAnomalia = TipoAnomalia.values();
        for (int index : listaIndex) {
            int i = 0;
            while (i != index) {
                i++;
            }
            Anomalia nuevaAnomalia = new Anomalia(tipoAnomalia[index]);
            listaAnomalias.add(nuevaAnomalia);
        }
        return true;
    }

    /**
     * Asigna una nueva acción a un tipo de anomalía.
     * @param nuevaAccion (String) acción a agregar.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía.
     * @return (boolean) false si la acción ya existía, true si se agregó correctamente.
     */
    public boolean asignarNuevaAccion(String nuevaAccion, TipoAnomalia tipoAnomalia) {
        for (Anomalia anomalia : listaAnomalias) {
            if (anomalia.getTipoAnomalia().equals(tipoAnomalia)) {
                ArrayList<String> listaAcciones = anomalia.getListaAcciones();
                for (String accion : listaAcciones) {
                    if (accion.equals(nuevaAccion)) {
                        return false;
                    }
                }
                listaAcciones.add(nuevaAccion);
                anomalia.setListaAcciones(listaAcciones);
            }
        }
        return true;
    }

    /**
     * Elimina una acción de un tipo de anomalía según su índice.
     * @param indexEliminar (int) índice de acción a eliminar.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía.
     * @return (boolean) true si se eliminó, false si el índice no es válido.
     */
    public boolean eliminarAccion(int indexEliminar, TipoAnomalia tipoAnomalia) {
        for (Anomalia anomalia : listaAnomalias) {
            if (anomalia.getTipoAnomalia().equals(tipoAnomalia)) {
                ArrayList<String> listaAcciones = anomalia.getListaAcciones();
                if (indexEliminar >= listaAcciones.size()) {
                    return false;
                }
                listaAcciones.remove(indexEliminar);
                anomalia.setListaAcciones(listaAcciones);
                return true;
            }
        }
        return false;
    }

    /**
     * Modifica una acción de un tipo de anomalía según su índice.
     * @param indexModificar (int) índice de acción a modificar.
     * @param modificacion (String) nueva descripción de la acción.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía.
     * @return (boolean) true si se modificó correctamente, false si el índice no es válido.
     */
    public boolean modificarAccion(int indexModificar, String modificacion, TipoAnomalia tipoAnomalia) {
        for (Anomalia anomalia : listaAnomalias) {
            if (anomalia.getTipoAnomalia().equals(tipoAnomalia)) {
                ArrayList<String> listaAcciones = anomalia.getListaAcciones();
                if (indexModificar >= listaAcciones.size()) {
                    return false;
                }
                listaAcciones.set(indexModificar, modificacion);
                anomalia.setListaAcciones(listaAcciones);
                return true;
            }
        }
        return true;
    }

    /**
     * Activa aleatoriamente algunas anomalías.
     * @return (boolean) true siempre que se complete el proceso.
     */
    public boolean activarAnomalias() {
        int valorAComparar = 3;
        Random rand = new Random();
        for (Anomalia anomalia : listaAnomalias) {
            int probabilidad = rand.nextInt(1, 4);
            if (probabilidad == valorAComparar) {
                anomalia.setAnomaliaActiva(true);
                System.out.println(anomalia.toString());
            }
        }
        return true;
    }

    /**
     * Representación en cadena de las anomalías existentes.
     * @return (String) lista de anomalías.
     */
    @Override
    public String toString() {
        return "Lista Anomalias: \n" + listaAnomalias;
    }

    /**
     * Representación en cadena de las anomalías no detectadas.
     * @return (String) lista de anomalías no detectadas.
     */
    public String toString2() {
        return "Lista Anomalias no detectadas: \n" + listaAnomaliasNoDetectadas;
    }
}
