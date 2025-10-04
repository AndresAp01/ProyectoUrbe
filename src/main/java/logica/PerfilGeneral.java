package logica;
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
    private AdmEdificio AdmEdificio;
    private AdmDron AdmDron;
    private AdmRobots AdmRobots;
    private AdmCiudadanos AdmCiudadanos;
    private AdmCargaficios AdmCargaficios;
    private AdmReglas AdmReglas;

    public PerfilGeneral(){
        this.AdmEdificio = new AdmEdificio();
        this.AdmDron = new AdmDron();
        this.AdmCiudadanos = new AdmCiudadanos();
        this.AdmRobots = new AdmRobots();
        this.AdmCargaficios = new AdmCargaficios();
        this.AdmReglas = new AdmReglas();
    }
    //parte 1
    public int conseguirPorcentajeRobotsAlerta(){
        return AdmRobots.TotalRobotsAlerta()*100/AdmRobots.TotalRobots(); //divide los robots totales por los robots en alerta, esto se muestra en el dashboard como kpi
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

    //parte 3
    public int edificiosImpactados(AdmEdificio AdmEdificio){
        return AdmEdificio.edificiosImpactados(); //se requiere esta funcion que solo da el numero de edificios donde ha ocurrido 1 o mas problemas de cualquier tipo
    }
    
    //SARAAAAAA
    //Para los desgloses de esta parte requiero saber como funcionan los problemas y su contabilidad

    //Para los graficos
    //Tabla de edificios con incidentes, acciones ejecutadas y hora.
    //Strings puesto de manera en tabla no? Aunque requiero saber como funcan los problemas

    //Alertas: Edificio con mayor reincidencia.

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