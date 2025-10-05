package logica;
import java.lang.reflect.Array;
import java.util.ArrayList;
import modelo.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
/**
 *
 * @author linuxman
 */
public class PerfilGeneral {

    //parte 1
    public int conseguirPorcentajeRobotsAlerta(AdmRobots admRobots){
        return admRobots.TotalRobotsAlerta()*100/admRobots.TotalRobots(); //divide los robots totales por los robots en alerta, esto se muestra en el dashboard como kpi
    }

    public Map<Edificio, DatosRobotEdificio> obtenerDatosPorEdificio(AdmEdificio AdmEdificio) {

        ArrayList<Edificio> listaEdificios = AdmEdificio.getListaEdificios(); //se requiere acceder a la lista de edificios

        Map<Edificio, DatosRobotEdificio> mapa = new HashMap<>();

        for (Edificio edificio : listaEdificios) {
            int total = edificio.getN_robots_total();
            int enAlerta = edificio.getN_robots_en_alerta();
            int porcentaje = enAlerta*100/total;

            DatosRobotEdificio datos = new DatosRobotEdificio(porcentaje, total, enAlerta);
            mapa.put(edificio, datos);
        }

        return mapa;
    }

    //RECORDAR
    //SI algun edificio posee un porcentaje de alerta mas grande que un umbral configurable salta alerta

    

    //parte 2
    public int cantidadCargaficiosDisponibles(AdmCargaficios AdmCargaficios){
        return AdmCargaficios.CargaficiosDisponibles(); //retorna la cantidad de cargaficios disponibles
    }

    public int porcentajeCargaficiosDisponibles(AdmCargaficios AdmCargaficios){
        ArrayList<Cargaficio> Cargaficios = AdmCargaficios.getListaCargaficios(); //se requiere esta funcion en AdmCargaficios
        return  AdmCargaficios.CargaficiosDisponibles() * 100 / Cargaficios.size() ;
    }

    //Para el desglose que es
    //Por estación: estado, capacidad, ocupación, servicios en la última hora.
    //Se propone usar un ArrayList de Strings, donde solo se pondrian strings de eso.
    public ArrayList desgloseEnergia(Cargaficio unCargaficio){
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
    
    


    

    public Map<Cargaficio, DatosCargaficioOcupacionEstado> obtenerDatosPorCargaficio(AdmCargaficios AdmCargaficios) {

        ArrayList<Cargaficio> listaCargaficios = AdmCargaficios.getListaCargaficios(); //se requiere acceder a la lista de Cargaficios

        Map<Cargaficio, DatosCargaficioOcupacionEstado> mapa = new HashMap<>();

        for (Cargaficio cargaficio : listaCargaficios) {
            int ocupacion = cargaficio.getEspaciosOcupados()*100/cargaficio.getCapacidadMaxima();
            TStatus status = cargaficio.getEstado();

            DatosCargaficioOcupacionEstado datos = new DatosCargaficioOcupacionEstado(ocupacion , status);
            mapa.put(cargaficio, datos);
        }

        return mapa;
    }
    //Se hacen mapas para relacion 1 a 1 y acceso facil por edificio a esos datos




    //PARTE 3

    //*************************** KPI ***************************
    public int edificiosImpactados(AdmEdificio admEdificio, AdmAnomalias admAnomalias) {
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        int cuenta = 0;
        for (Edificio edificio : listaEdificios) {
            for(Anomalia anomalia : listaAnomalias) {
                if(anomalia.getAvenida() == edificio.getAvenida() || anomalia.getCalle() == edificio.getCalle()) {
                    cuenta++;
                }
            }
        }
        return cuenta;
    }

    //*************************** DESGLOCES ***************************
    //Desgloce 1

    public boolean anomaliaExiste(TipoAnomalia tipoAnomalia, ArrayList<Anomalia> anomalias) {
        for (Anomalia anomalia: anomalias) {
            if(anomalia.getTipoAnomalia() == tipoAnomalia) {
                return true;
            }
        }
        return false;
    }

    public int cuentaTotal(TipoAnomalia anomaliaActual, ArrayList<Anomalia> anomalias, Edificio edificioActual) {
        int cuenta=0;
        for(Anomalia anomalia : anomalias) {
            if (anomalia.getTipoAnomalia()==anomaliaActual && edificioActual.getCalle()==anomalia.getCalle() || edificioActual.getAvenida()==anomalia.getAvenida()) {
                cuenta++;
            }
        }
        return cuenta;
    }

    public ArrayList<ArrayList<Integer>> porTipoAnomaliaYEdificio(AdmEdificio admEdificio, AdmAnomalias admAnomalias) {
        ArrayList<ArrayList<Integer>> matriz =  new ArrayList();
        Integer cuenta = 0;
        TipoAnomalia[] tipoAnomalia = TipoAnomalia.values();
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        for(Edificio edificio : listaEdificios) {
            ArrayList<Integer> listas =  new ArrayList();
            for (TipoAnomalia tAnomalia : tipoAnomalia) {
                boolean existe=anomaliaExiste(tAnomalia, listaAnomalias);
                if (existe){
                    cuenta = cuentaTotal(tAnomalia, listaAnomalias, edificio);
                    listas.add(cuenta);

                }
            }
            matriz.add(listas);
        }
        //la cantidad de elementos de la matriz depende de la cantidad de edificios.
        return matriz;
    }

    //Desgloce 2

    public int cuentaTotal(String accion, ArrayList<Registro> listaRegistros) {
        int cuenta=0;
        for (Registro registro : listaRegistros) {
            if (accion.equals(registro.getAccionTomada())){
                cuenta++;
            }
        }
        return cuenta;
    }

    public ArrayList<String> listaAcciones(ArrayList<Anomalia> listaAnomalias){
        ArrayList acciones = new ArrayList();
        for (Anomalia anomalia : listaAnomalias) {
            ArrayList<String> listaAcciones = anomalia.getListaAcciones();
            for (String accion : listaAcciones) {
                if (!(acciones.contains(accion))) {
                    acciones.add(accion);
                }
            }
        }
        return  acciones;
    }

    public ArrayList<ArrayList> porAccionEjecutada(CInteligencia cInteligencia, AdmAnomalias admAnomalias) {
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        ArrayList<Registro> listaRegistros = cInteligencia.getListaRegistros();
        ArrayList<String> listaAcciones = listaAcciones(listaAnomalias);
        ArrayList matriz =  new ArrayList();
        for(String accion : listaAcciones) {
            ArrayList listas =  new ArrayList();
            listas.add(accion);
            listas.add(cuentaTotal(accion, listaRegistros));
            matriz.add(listas);
        }
        return matriz;
    }

    //*************************** GRAFICO ***************************

    public ArrayList accionYHora(ArrayList<Registro> listaRegistros, TipoAnomalia tAnomalia){
        ArrayList datos= new ArrayList();
        for (Registro registro : listaRegistros) {
            if (registro.getTipoAnomalia()==tAnomalia){
                datos.add(registro.getAccionTomada());
                datos.add(registro.getHora());
                return datos;
            }
        }
        return null;
    }

    public ArrayList<ArrayList> tablaEdificiosIncidentes(AdmEdificio admEdificio, AdmAnomalias admAnomalias, CInteligencia cInteligencia) {
        ArrayList<Edificio> listaEdificios = admEdificio.getListaEdificios();
        ArrayList<Anomalia> listaAnomalias = admAnomalias.getListaAnomalias();
        ArrayList<Registro> listaRegistros = cInteligencia.getListaRegistros();
        ArrayList matriz =  new ArrayList();
        int cuenta = 0;
        TipoAnomalia[] tipoAnomalia = TipoAnomalia.values();
        for(Edificio edificio : listaEdificios) {
            ArrayList listas =  new ArrayList();
            for (TipoAnomalia tAnomalia : tipoAnomalia) {
                for (Anomalia anomalia: listaAnomalias) {
                    if(anomalia.getTipoAnomalia() == tAnomalia && edificio.getCalle().equals(anomalia.getCalle()) || edificio.getAvenida().equals(anomalia.getAvenida())) {
                        ArrayList accionYHora=accionYHora(listaRegistros,tAnomalia);
                        if (accionYHora!=null && !listas.contains(edificio.getNombre())) {
                            listas.add(edificio.getNombre());
                            listas.add(edificio.getId());
                            listas.add(tAnomalia);
                            listas.add(accionYHora.get(0));
                            listas.add(accionYHora.get(1));}}}}
            if(listas.size()>0){
                matriz.add(listas);
            }
        }
        return matriz;
    }

    //*************************** ALERTA ***************************

    public ArrayList<String> mayorReincidencia(AdmEdificio admEdificio, AdmAnomalias admAnomalias){
        ArrayList<ArrayList<Integer>> matriz = porTipoAnomaliaYEdificio(admEdificio,  admAnomalias);
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



















    //PARTE 4

    public ArrayList ocupacionPorEdificio(AdmEdificio AdmEdificio){
        ArrayList<Edificio> listaEdificios = AdmEdificio.getListaEdificios(); //se requiere acceder a la lista de edificios
        ArrayList ocupacionEdificio = new ArrayList<>();
        for (Edificio edificio : listaEdificios) {
            int ocupacion = edificio.porcentajeOcupacion();
            ocupacionEdificio.add(ocupacion);
        }
        return ocupacionEdificio; //Pues no cambia el orden de edificios
    }
    
    //Retorna lista con info especifica por edificio
    public ArrayList desgloseEdificios(Edificio unEdificio){
        ArrayList info = new ArrayList<>();
        String id = unEdificio.getId();
        int capacidad = unEdificio.getCapacidad_maxima();
        int residente = unEdificio.getEspacios_ocupados();
        int ocupacion = unEdificio.porcentajeOcupacion();
        int ciudadanosConRobot = unEdificio.CiudadanosConRobot();
        int ciudadanosSinRobot = residente - ciudadanosConRobot;
        int robotsAsignados = unEdificio.getN_robots_total();
        int RobotsComparadoCiudadanos = robotsAsignados/residente;
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
    
    
    

    
    
    //Para los top-3 
    //Mayor Ocupacion
    //Mayor Cidadanos SIn robot
    //Mayor Robots Alerta
    //Se devolvera los 3 primeros en ArrayList
    public ArrayList<Edificio> ConseguirTop3MayorOcupacion(AdmEdificio AdmEdificio){
        return AdmEdificio.obtenerTop3EdificiosOcupacion();
    }
    
    public ArrayList<Edificio> ConseguirTop3MayorCiudadanosSinRobot(AdmEdificio AdmEdificio){
        return AdmEdificio.obtenerTop3EdificiosCiudadanosSinRobot();
    }
    
    public ArrayList<Edificio> ConseguirTop3MayorRobotsAlerta(AdmEdificio AdmEdificio){
        return AdmEdificio.obtenerTop3EdificiosRobotAlerta();
    }
    
    //Barras por edificio (ocupación %).Ya fue conseguida por la funcion pasada

    //Alerta, puede hacerse en el GUI no es configurable
    //SobreAsignacion
    //Se consigue la media de ciuddanos en todos los edificios, y la media de robots en todos los edificios
    //Se regresa el edificio con mas robots y el edificio con mas ciudadanos
    //[media robots, media ciudadanos, edificio con mas robots, edificio con mas ciudadanos]
    //si no existen los edificios por a o b sera un string que diga No Hay
    
    public ArrayList InfoSobreAsignacion(AdmEdificio AdmEdificio){
        ArrayList info = new ArrayList<>();
        int mediaR = AdmEdificio.mediaRobots();
        int mediaC = AdmEdificio.mediaCiudadanos();
        Optional<Edificio> edificioR = AdmEdificio.obtenerEdificioConMasRobots();
        Optional<Edificio> edificioC = AdmEdificio.obtenerEdificioConMasCiudadanos();
        info.add(mediaR);
        info.add(mediaC);
        if (edificioR.isPresent() && edificioC.isPresent()){
            info.add(edificioR.get());
            info.add(edificioC.get());
        } else {
            info.add("No existe");
            info.add("No existe");
        }
        return info;
    }




}
