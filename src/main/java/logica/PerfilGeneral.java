package logica;

import java.lang.reflect.Array;
import java.util.ArrayList;
import modelo.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

/**
 * Clase que representa el perfil general del sistema.
 * Proporciona métodos para obtener métricas, KPIs, desgloses de datos
 * y estadísticas sobre edificios, robots, cargaficios y anomalías.
 */
public class PerfilGeneral {

    // --------------------------------------- PARTE 1: Robots y Tareas ---------------------------------------

    /**
     * Calcula el porcentaje de robots que se encuentran en alerta.
     * @param admRobots (AdmRobots) administrador de robots.
     * @return (int) porcentaje de robots en alerta.
     */
    public int conseguirPorcentajeRobotsAlerta(AdmRobots admRobots) {
        return admRobots.TotalRobotsAlerta() * 100 / admRobots.TotalRobots();
    }

    /**
     * Obtiene los datos de los robots agrupados por edificio.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (Map<Edificio, DatosRobotEdificio>) mapa con porcentaje, total y en alerta por edificio.
     */
    public Map<Edificio, DatosRobotEdificio> obtenerDatosPorEdificio(AdmEdificio AdmEdificio) {
        ArrayList<Edificio> listaEdificios = AdmEdificio.getListaEdificios();
        Map<Edificio, DatosRobotEdificio> mapa = new HashMap<>();

        for (Edificio edificio : listaEdificios) {
            int total = edificio.getN_robots_total();
            int enAlerta = edificio.getN_robots_en_alerta();
            int porcentaje = enAlerta * 100 / total;
            DatosRobotEdificio datos = new DatosRobotEdificio(porcentaje, total, enAlerta);
            mapa.put(edificio, datos);
        }

        return mapa;
    }

    /**
     * Obtiene la cantidad de veces que cada tipo de tarea fue usada.
     * Orden: [medico, dormitorio, listaAlimentos, regarPlantas, paseo, reunion]
     * @param cantTareasUsadas (CantTareasUsadas) registro de tareas usadas.
     * @return (ArrayList) lista con conteo de cada tarea.
     */
    public ArrayList cantTareasUsadas(CantTareasUsadas cantTareasUsadas) {
        ArrayList info = new ArrayList<>();
        info.add(cantTareasUsadas.getCantMedico());
        info.add(cantTareasUsadas.getCantDormitorio());
        info.add(cantTareasUsadas.getCantAlimentos());
        info.add(cantTareasUsadas.getCantPlantas());
        info.add(cantTareasUsadas.getCantPaseo());
        info.add(cantTareasUsadas.getCantReunion());
        return info;
    }

    // --------------------------------------- PARTE 2: Cargaficios ---------------------------------------

    /**
     * Obtiene la cantidad total de cargaficios disponibles.
     * @param AdmCargaficios (AdmCargaficios) administrador de cargaficios.
     * @return (int) cantidad de cargaficios disponibles.
     */
    public int cantidadCargaficiosDisponibles(AdmCargaficios AdmCargaficios) {
        return AdmCargaficios.CargaficiosDisponibles();
    }

    /**
     * Calcula el porcentaje de cargaficios disponibles.
     * @param AdmCargaficios (AdmCargaficios) administrador de cargaficios.
     * @return (int) porcentaje de cargaficios disponibles.
     */
    public int porcentajeCargaficiosDisponibles(AdmCargaficios AdmCargaficios) {
        ArrayList<Cargaficio> Cargaficios = AdmCargaficios.getListaCargaficios();
        return AdmCargaficios.CargaficiosDisponibles() * 100 / Cargaficios.size();
    }

    /**
     * Obtiene un desglose de información de un cargaficio.
     * @param unCargaficio (Cargaficio) cargaficio a analizar.
     * @return (ArrayList) lista con [id, estado, ocupación, servicios última hora].
     */
    public ArrayList desgloseEnergia(Cargaficio unCargaficio) {
        ArrayList info = new ArrayList<>();
        String id = unCargaficio.getId();
        TStatus estado = unCargaficio.getEstado();
        int ocupacion = unCargaficio.getOcupacion();
        int servicios = unCargaficio.getServiciosUltHora();
        info.add(id);
        info.add(estado);
        info.add(ocupacion);
        info.add(servicios);
        return info;
    }

    /**
     * Obtiene los datos de ocupación y estado de cada cargaficio.
     * @param AdmCargaficios (AdmCargaficios) administrador de cargaficios.
     * @return (Map<Cargaficio, DatosCargaficioOcupacionEstado>) mapa con ocupación y estado.
     */
    public Map<Cargaficio, DatosCargaficioOcupacionEstado> obtenerDatosPorCargaficio(AdmCargaficios AdmCargaficios) {
        ArrayList<Cargaficio> listaCargaficios = AdmCargaficios.getListaCargaficios();
        Map<Cargaficio, DatosCargaficioOcupacionEstado> mapa = new HashMap<>();

        for (Cargaficio cargaficio : listaCargaficios) {
            int ocupacion = cargaficio.getEspaciosOcupados() * 100 / cargaficio.getCapacidadMaxima();
            TStatus status = cargaficio.getEstado();
            DatosCargaficioOcupacionEstado datos = new DatosCargaficioOcupacionEstado(ocupacion, status);
            mapa.put(cargaficio, datos);
        }

        return mapa;
    }

    // --------------------------------------- PARTE 3: KPIs y Desgloses ---------------------------------------

    /**
     * Calcula la cantidad de edificios impactados por anomalías.
     * @param admEdificio (AdmEdificio) administrador de edificios.
     * @param admAnomalias (AdmAnomalias) administrador de anomalías.
     * @return (int) número de edificios afectados.
     */
    public int edificiosImpactados(AdmEdificio admEdificio, AdmAnomalias admAnomalias) {
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        int cuenta = 0;
        for (Edificio edificio : listaEdificios) {
            for (Anomalia anomalia : listaAnomalias) {
                if (anomalia.getAvenida() == edificio.getAvenida() || anomalia.getCalle() == edificio.getCalle()) {
                    cuenta++;
                }
            }
        }
        return cuenta;
    }

    /**
     * Verifica si existe alguna anomalía de un tipo dado en la lista.
     * @param tipoAnomalia (TipoAnomalia) tipo de anomalía.
     * @param anomalias (ArrayList<Anomalia>) lista de anomalías.
     * @return (boolean) true si existe.
     */
    public boolean anomaliaExiste(TipoAnomalia tipoAnomalia, ArrayList<Anomalia> anomalias) {
        for (Anomalia anomalia : anomalias) {
            if (anomalia.getTipoAnomalia() == tipoAnomalia) return true;
        }
        return false;
    }

    /**
     * Cuenta el total de anomalías de un tipo en un edificio.
     * @param anomaliaActual (TipoAnomalia) tipo de anomalía.
     * @param anomalias (ArrayList<Anomalia>) lista de anomalías.
     * @param edificioActual (Edificio) edificio a analizar.
     * @return (int) cantidad de ocurrencias.
     */
    public int cuentaTotal(TipoAnomalia anomaliaActual, ArrayList<Anomalia> anomalias, Edificio edificioActual) {
        int cuenta = 0;
        for (Anomalia anomalia : anomalias) {
            if (anomalia.getTipoAnomalia() == anomaliaActual &&
                (edificioActual.getCalle() == anomalia.getCalle() || edificioActual.getAvenida() == anomalia.getAvenida())) {
                cuenta++;
            }
        }
        return cuenta;
    }

    /**
     * Genera una matriz de conteo de anomalías por tipo y edificio.
     * @param admEdificio (AdmEdificio) administrador de edificios.
     * @param admAnomalias (AdmAnomalias) administrador de anomalías.
     * @return (ArrayList<ArrayList<Integer>>) matriz de conteos.
     */
    public ArrayList<ArrayList<Integer>> porTipoAnomaliaYEdificio(AdmEdificio admEdificio, AdmAnomalias admAnomalias) {
        ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();
        Integer cuenta = 0;
        TipoAnomalia[] tipoAnomalia = TipoAnomalia.values();
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();

        for (Edificio edificio : listaEdificios) {
            ArrayList<Integer> listas = new ArrayList<>();
            for (TipoAnomalia tAnomalia : tipoAnomalia) {
                if (anomaliaExiste(tAnomalia, listaAnomalias)) {
                    cuenta = cuentaTotal(tAnomalia, listaAnomalias, edificio);
                    listas.add(cuenta);
                }
            }
            matriz.add(listas);
        }
        return matriz;
    }

    /**
     * Cuenta cuántas veces se ejecutó una acción en la lista de registros.
     * @param accion (String) nombre de la acción.
     * @param listaRegistros (ArrayList<Registro>) registros de la inteligencia.
     * @return (int) cantidad de veces que se ejecutó la acción.
     */
    public int cuentaTotal(String accion, ArrayList<Registro> listaRegistros) {
        int cuenta = 0;
        for (Registro registro : listaRegistros) {
            if (accion.equals(registro.getAccionTomada())) cuenta++;
        }
        return cuenta;
    }

    /**
     * Obtiene la lista de acciones únicas de todas las anomalías.
     * @param listaAnomalias (ArrayList<Anomalia>) lista de anomalías.
     * @return (ArrayList<String>) lista de acciones únicas.
     */
    public ArrayList<String> listaAcciones(ArrayList<Anomalia> listaAnomalias) {
        ArrayList acciones = new ArrayList<>();
        for (Anomalia anomalia : listaAnomalias) {
            ArrayList<String> listaAcciones = anomalia.getListaAcciones();
            for (String accion : listaAcciones) {
                if (!acciones.contains(accion)) acciones.add(accion);
            }
        }
        return acciones;
    }

    /**
     * Obtiene una matriz de conteo de acciones ejecutadas.
     * @param cInteligencia (CInteligencia) componente de inteligencia.
     * @param admAnomalias (AdmAnomalias) administrador de anomalías.
     * @return (ArrayList<ArrayList>) matriz de [acción, cantidad].
     */
    public ArrayList<ArrayList> porAccionEjecutada(CInteligencia cInteligencia, AdmAnomalias admAnomalias) {
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        ArrayList<Registro> listaRegistros = cInteligencia.getListaRegistros();
        ArrayList<String> listaAcciones = listaAcciones(listaAnomalias);
        ArrayList matriz = new ArrayList<>();
        for (String accion : listaAcciones) {
            ArrayList listas = new ArrayList<>();
            listas.add(accion);
            listas.add(cuentaTotal(accion, listaRegistros));
            matriz.add(listas);
        }
        return matriz;
    }

    /**
     * Obtiene la acción y la hora en que se ejecutó para un tipo de anomalía.
     * @param listaRegistros (ArrayList<Registro>) lista de registros.
     * @param tAnomalia (TipoAnomalia) tipo de anomalía.
     * @return (ArrayList) [acción, hora] o null si no se encuentra.
     */
    public ArrayList accionYHora(ArrayList<Registro> listaRegistros, TipoAnomalia tAnomalia) {
        ArrayList datos = new ArrayList<>();
        for (Registro registro : listaRegistros) {
            if (registro.getTipoAnomalia() == tAnomalia) {
                datos.add(registro.getAccionTomada());
                datos.add(registro.getHora());
                return datos;
            }
        }
        return null;
    }

    /**
     * Genera una tabla de edificios con incidentes y acciones ejecutadas.
     * @param admEdificio (AdmEdificio) administrador de edificios.
     * @param admAnomalias (AdmAnomalias) administrador de anomalías.
     * @param cInteligencia (CInteligencia) componente de inteligencia.
     * @return (ArrayList<ArrayList>) matriz con información de incidentes.
     */
    public ArrayList<ArrayList> tablaEdificiosIncidentes(AdmEdificio admEdificio, AdmAnomalias admAnomalias, CInteligencia cInteligencia) {
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        ArrayList<Registro> listaRegistros = cInteligencia.getListaRegistros();
        ArrayList matriz = new ArrayList<>();
        TipoAnomalia[] tipoAnomalia = TipoAnomalia.values();

        for (Edificio edificio : listaEdificios) {
            ArrayList listas = new ArrayList<>();
            for (TipoAnomalia tAnomalia : tipoAnomalia) {
                for (Anomalia anomalia : listaAnomalias) {
                    if (anomalia.getTipoAnomalia() == tAnomalia &&
                        (edificio.getCalle().equals(anomalia.getCalle()) || edificio.getAvenida().equals(anomalia.getAvenida()))) {
                        ArrayList accionYHora = accionYHora(listaRegistros, tAnomalia);
                        if (accionYHora != null && !listas.contains(edificio.getNombre())) {
                            listas.add(edificio.getNombre());
                            listas.add(edificio.getId());
                            listas.add(tAnomalia);
                            listas.add(accionYHora.get(0));
                            listas.add(accionYHora.get(1));
                        }
                    }
                }
            }
            if (listas.size() > 0) {
                matriz.add(listas);
            }
        }
        return matriz;
    }

    /**
     * Obtiene los edificios con mayor reincidencia de anomalías.
     * @param admEdificio (AdmEdificio) administrador de edificios.
     * @param admAnomalias (AdmAnomalias) administrador de anomalías.
     * @return (ArrayList<String>) [nombreEdificio, idEdificio] con mayor reincidencia.
     */
    public ArrayList<String> mayorReincidencia(AdmEdificio admEdificio, AdmAnomalias admAnomalias) {
        ArrayList<ArrayList<Integer>> matriz = porTipoAnomaliaYEdificio(admEdificio, admAnomalias);
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<String> resultado = new ArrayList<>();
        int mayor = 0;
        int indice = 0;
        for (int i = 0; i < matriz.size(); i++) {
            int cuenta = 0;
            for (int j = 0; j < matriz.get(i).size(); j++) {
                cuenta += matriz.get(i).get(j);
            }
            if (cuenta > mayor) {
                mayor = cuenta;
                indice = i;
            }
        }
        resultado.add(listaEdificios.get(indice).getNombre());
        resultado.add(listaEdificios.get(indice).getId());
        return resultado;
    }

    // --------------------------------------- PARTE 4: Ocupación y Top Edificios ---------------------------------------

    /**
     * Obtiene el porcentaje de ocupación de todos los edificios.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (ArrayList) porcentaje de ocupación por edificio.
     */
    public ArrayList ocupacionPorEdificio(AdmEdificio AdmEdificio) {
        ArrayList<Edificio> listaEdificios = AdmEdificio.getListaEdificios();
        ArrayList ocupacionEdificio = new ArrayList<>();
        for (Edificio edificio : listaEdificios) {
            int ocupacion = edificio.porcentajeOcupacion();
            ocupacionEdificio.add(ocupacion);
        }
        return ocupacionEdificio;
    }

    /**
     * Obtiene un desglose completo de información de un edificio.
     * @param unEdificio (Edificio) edificio a analizar.
     * @return (ArrayList) [id, capacidad, residentes, ocupación%, ciudadanos con robot, ciudadanos sin robot, robots/residentes, robots en alerta].
     */
    public ArrayList desgloseEdificios(Edificio unEdificio) {
        ArrayList info = new ArrayList<>();
        String id = unEdificio.getId();
        int capacidad = unEdificio.getCapacidad_maxima();
        int residente = unEdificio.getEspacios_ocupados();
        int ocupacion = unEdificio.porcentajeOcupacion();
        int ciudadanosConRobot = unEdificio.CiudadanosConRobot();
        int ciudadanosSinRobot = residente - ciudadanosConRobot;
        int robotsAsignados = unEdificio.getN_robots_total();
        int RobotsComparadoCiudadanos = robotsAsignados / residente;
        int robotsAlerta = unEdificio.getN_robots_en_alerta();

        info.add(id);
        info.add(capacidad);
        info.add(residente);
        info.add(ocupacion);
        info.add(ciudadanosConRobot);
        info.add(ciudadanosSinRobot);
        info.add(RobotsComparadoCiudadanos);
        info.add(robotsAlerta);
        return info;
    }

    /**
     * Obtiene los top 3 edificios con mayor ocupación.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (ArrayList<Edificio>) top 3 edificios por ocupación.
     */
    public ArrayList<Edificio> ConseguirTop3MayorOcupacion(AdmEdificio AdmEdificio) {
        return AdmEdificio.obtenerTop3EdificiosOcupacion();
    }

    /**
     * Obtiene los top 3 edificios con más ciudadanos sin robot.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (ArrayList<Edificio>) top 3 edificios.
     */
    public ArrayList<Edificio> ConseguirTop3MayorCiudadanosSinRobot(AdmEdificio AdmEdificio) {
        return AdmEdificio.obtenerTop3EdificiosCiudadanosSinRobot();
    }

    /**
     * Obtiene los top 3 edificios con más robots en alerta.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (ArrayList<Edificio>) top 3 edificios.
     */
    public ArrayList<Edificio> ConseguirTop3MayorRobotsAlerta(AdmEdificio AdmEdificio) {
        return AdmEdificio.obtenerTop3EdificiosRobotAlerta();
    }

    /**
     * Obtiene información sobre la sobreasignación de robots y ciudadanos.
     * @param AdmEdificio (AdmEdificio) administrador de edificios.
     * @return (ArrayList) [media robots, media ciudadanos, edificio con más robots, edificio con más ciudadanos].
     */
    public ArrayList InfoSobreAsignacion(AdmEdificio AdmEdificio) {
        ArrayList info = new ArrayList<>();
        int mediaR = AdmEdificio.mediaRobots();
        int mediaC = AdmEdificio.mediaCiudadanos();
        Optional<Edificio> edificioR = AdmEdificio.obtenerEdificioConMasRobots();
        Optional<Edificio> edificioC = AdmEdificio.obtenerEdificioConMasCiudadanos();
        info.add(mediaR);
        info.add(mediaC);
        if (edificioR.isPresent() && edificioC.isPresent()) {
            info.add(edificioR.get());
            info.add(edificioC.get());
        } else {
            info.add("No existe");
            info.add("No existe");
        }
        return info;
    }
}
