package logica;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cargaficio;
import modelo.TStatus;

/**
 * Clase que administra los Cargaficios (estaciones de carga para drones y robots).
 * Permite crear, consultar, modificar, eliminar y gestionar la disponibilidad
 * de los Cargaficios.
 */
public class AdmCargaficios {

    private ArrayList<Cargaficio> listaCargaficios;
    private int Cargaficios_Disponibles;

    /**
     * Constructor que inicializa la lista de Cargaficios y el contador de disponibles.
     */
    public AdmCargaficios() {
        this.listaCargaficios = new ArrayList<>();
        this.Cargaficios_Disponibles = 0;
    }

    /**
     * Obtiene la lista de Cargaficios.
     * @return (ArrayList<Cargaficio>) lista de Cargaficios.
     */
    public ArrayList<Cargaficio> getListaCargaficios() {
        return listaCargaficios;
    }

    /**
     * Agrega Cargaficios a la lista.
     * @param cantidad (int) cantidad a crear. Si es 0, se genera una cantidad aleatoria entre 5 y 7.
     * @return (boolean) true siempre que se agreguen correctamente.
     */
    public boolean agregarCargaficios(int cantidad) {
        String[] descripcionesCargaficios = {
                "Centro de carga rápida para drones y robots de vigilancia urbana, con sistema de acople automático.",
                "Estación inteligente de recarga para flotas de robots asistentes en edificios corporativos.",
                "Punto de carga modular para drones de patrullaje en zonas residenciales y comerciales.",
                "Centro de recarga autónomo con gestión de energía para robots de limpieza y seguridad.",
                "Estación de carga con monitoreo remoto, diseñada para vehículos aéreos no tripulados y robots móviles.",
                "Centro de recarga eficiente en parqueaderos y patios de edificios, compatible con múltiples modelos de drones y robots.",
                "Punto de carga rápido para drones de inspección de infraestructura y robots de entrega.",
                "Estación de recarga sostenible con energía renovable, para flotas de patrullaje automatizado."
        };
        int i = 0;
        Random rand = new Random();
        if (cantidad == 0) {
            int tope = rand.nextInt(5, 8);
            while (i < tope) {
                Cargaficio cargaficio = new Cargaficio("Cf" + (i + 1), descripcionesCargaficios[i]);
                listaCargaficios.add(cargaficio);
                i++;
            }
        } else {
            while (i < cantidad) {
                Cargaficio cargaficio = new Cargaficio("Cf" + (i + 1), descripcionesCargaficios[i]);
                listaCargaficios.add(cargaficio);
                i++;
            }
        }
        return true;
    }

    /**
     * Retorna la lista completa de Cargaficios.
     * @return (ArrayList<Cargaficio>) lista de Cargaficios.
     */
    public ArrayList<Cargaficio> retornarListaCargaficios() {
        return listaCargaficios;
    }

    /**
     * Consulta un Cargaficio por su ID.
     * @param elId (String) ID del Cargaficio a consultar.
     * @return (Cargaficio) Cargaficio encontrado o null si no existe.
     */
    public Cargaficio consultar(String elId) {
        for (Cargaficio Cargaficio : listaCargaficios) {
            if (elId.equals(Cargaficio.getId()))
                return Cargaficio;
        }
        return null;
    }

    /**
     * Modifica un Cargaficio existente en la lista.
     * @param nuevoCargaficio (Cargaficio) Cargaficio con los datos actualizados.
     * @return (boolean) true si se modificó, false si no se encontró.
     */
    public boolean modificar(Cargaficio nuevoCargaficio) {
        for (int i = 0; i < listaCargaficios.size(); i++) {
            if (listaCargaficios.get(i).equals(nuevoCargaficio)) {
                listaCargaficios.set(i, nuevoCargaficio);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un Cargaficio por su ID.
     * @param elId (String) ID del Cargaficio a eliminar.
     * @return (boolean) true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(String elId) {
        for (int i = 0; i < listaCargaficios.size(); i++) {
            if (elId.equals(listaCargaficios.get(i).getId())) {
                listaCargaficios.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula la cantidad de Cargaficios disponibles.
     * @return (int) número de Cargaficios con estado DISPONIBLE.
     */
    public int CargaficiosDisponibles() {
        Cargaficios_Disponibles = 0; 
        for (Cargaficio CargaficioActual : listaCargaficios)
            if (CargaficioActual.getEstado() == TStatus.DISPONIBLE) {
                Cargaficios_Disponibles += 1;
            }
        return Cargaficios_Disponibles;
    }

    /**
     * Retorna los Cargaficios en mantenimiento que tienen ocupación mayor a 0.
     * @return (ArrayList) lista de Cargaficios en mantenimiento ocupados.
     */
    public ArrayList CargaficiosEnMantenimientoConOcupacion() {
        ArrayList cargaficios = new ArrayList<>();
        for (Cargaficio CargaficioActual : listaCargaficios) {
            if (CargaficioActual.getEstado() == TStatus.EN_MANTENIMIENTO && (CargaficioActual.getOcupacion() > 0)) {
                cargaficios.add(CargaficioActual);
            }
        }
        return cargaficios;
    }

    /**
     * Carga los drones y robots conectados en cada Cargaficio.
     * @return (boolean) true siempre que se complete el proceso.
     */
    public boolean cargarDispositivosConectados() {
        for (Cargaficio CargaficioActual : listaCargaficios) {
            CargaficioActual.cargar_drones();
            CargaficioActual.cargar_robots();
        }
        return true;
    }

    /**
     * Retorna un Cargaficio disponible que no esté lleno.
     * @return (Cargaficio) primer Cargaficio disponible o null si no hay ninguno.
     */
    public Cargaficio retornarCargaficioDisponible() {
        for (Cargaficio CargaficioActual : listaCargaficios) {
            if (!CargaficioActual.getEstaLleno()) {
                return CargaficioActual;
            }
        }
        return null;
    }

    /**
     * Representación en cadena de la lista de Cargaficios.
     * @return (String) lista de Cargaficios.
     */
    @Override
    public String toString() {
        return "lista de Cargaficios:\n" + listaCargaficios;
    }
}
