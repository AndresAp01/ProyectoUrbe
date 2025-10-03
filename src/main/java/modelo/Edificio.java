/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author linuxman
 */ //max_capacidad = 20
public class Edificio {
    private String nombre;
    private String id;
    private int capacidad_maxima;
    private int espacios_ocupados;
    private boolean siendo_patrullado;
    private boolean si_esta_lleno;
    private Avenida avenida;
    private Calle calle;
    private ArrayList<Ciudadano> habitantes;
    private ArrayList<Dron> drones;
    private int n_robots_en_alerta;
    private int n_robots_total;
    private int nProblemasSucedidos;



    public Edificio (String id, String nombre){
        Random rand = new Random();
        Avenida[] avenidas = Avenida.values();
        Calle[] calles = Calle.values();
        this.id = id;
        this.nombre = nombre;
        this.capacidad_maxima = 20;
        this.espacios_ocupados = 0;
        this.siendo_patrullado = false;
        this.si_esta_lleno = false;
        this.avenida = avenidas[rand.nextInt(Avenida.values().length)];
        this.calle = calles[rand.nextInt(Calle.values().length)];
        this.habitantes = new ArrayList<Ciudadano>();
        this.drones = new ArrayList<Dron>();
        this.n_robots_en_alerta = 0;
        this.n_robots_total = 0;
        this.nProblemasSucedidos = 0;
    }
    
    //setters y getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public int getEspacios_ocupados() {
        return espacios_ocupados;
    }

    public void setEspacios_ocupados(int espacios_ocupados) {
        this.espacios_ocupados = espacios_ocupados;
    }

    public boolean isSiendo_patrullado() {
        return siendo_patrullado;
    }

    public void setSiendo_patrullado(boolean siendo_patrullado) {
        this.siendo_patrullado = siendo_patrullado;
    }

    public boolean isSi_esta_lleno() {
        return si_esta_lleno;
    }

    public void setSi_esta_lleno(boolean si_esta_lleno) {
        this.si_esta_lleno = si_esta_lleno;
    }

    public Avenida getAvenida() {
        return avenida;
    }

    public void setAvenida(Avenida avenida) {
        this.avenida = avenida;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    public ArrayList<Ciudadano> getHabitantes() {
        return habitantes;
    }

    public ArrayList<Dron> getDrones() {
        return drones;
    }

    public void setDrones(ArrayList<Dron> drones) {
        this.drones = drones;
    }

    public void setHabitantes(ArrayList<Ciudadano> habitantes) {
        this.habitantes = habitantes;
    }

    public int getN_robots_en_alerta() {
        return n_robots_en_alerta;
    }

    public void setN_robots_en_alerta(int n_robots_en_alerta) {
        this.n_robots_en_alerta = n_robots_en_alerta;
    }

    public int getN_robots_total() {
        return n_robots_total;
    }

    public void setN_robots_total(int n_robots_total) {
        this.n_robots_total = n_robots_total;
    }

    public int getN_problemas_sucedidos() {
        return nProblemasSucedidos;
    }

    public void setNProblemasSucedidos(int nProblemasSucedidos) {
        this.nProblemasSucedidos = nProblemasSucedidos;
    }
    
    //funciones
    public boolean agregarCiudadano(Ciudadano unCiudadano){
        if (!si_esta_lleno){
            for (Ciudadano CiudadanoActual: habitantes){
                if (CiudadanoActual.equals(unCiudadano)){
                    return false; //repetido
                }
            espacios_ocupados += 1;
            if (capacidad_maxima == espacios_ocupados){
                si_esta_lleno = true;
            }
            habitantes.add(unCiudadano);
            return true; //todo bien
            }
        }
        return false; //esta lleno
    }
    
    public boolean eliminarCiudadano(Ciudadano unCiudadano){
        if (!habitantes.isEmpty()){
            for (Ciudadano CiudadanoActual: habitantes){
                if (CiudadanoActual.equals(unCiudadano)){
                    habitantes.remove(unCiudadano);
                    espacios_ocupados -= 1;
                    if (capacidad_maxima != espacios_ocupados){
                        si_esta_lleno = false;
                    }
                    return true; //se elimina 
                }
            return false; //no se encontro
            }
        }
        return false; //lista vacia
    }
    
    public int porcentaje_ocupacion(){
        return capacidad_maxima/espacios_ocupados;
    }
    
    public int robots_totales(){
        for (Ciudadano CiudadanoActual: habitantes){
            if (!CiudadanoActual.getRobots_owned().isEmpty()){
                n_robots_total += CiudadanoActual.getRobots_owned().size();
            }
        }
        return n_robots_total;
    }
    
    public int robots_en_alerta(){
        for (Ciudadano CiudadanoActual: habitantes){
            if (!CiudadanoActual.getRobots_owned().isEmpty()){
                for (Robot RobotActual : CiudadanoActual.getRobots_owned()){
                    if (RobotActual.isSi_esta_en_alerta()){
                        n_robots_en_alerta += 1;
                    }
                }
            }
        }
        return n_robots_en_alerta;
    }
    
    public int porcentaje_alerta_robots(){
        return robots_totales()/robots_en_alerta();
    }
    
    //to string
    @Override
    public String toString() {
        return "Edificio{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", capacidad_maxima=" + capacidad_maxima +
                ", espacios_ocupados=" + espacios_ocupados +
                ", siendo_patrullado=" + siendo_patrullado +
                ", si_esta_lleno=" + si_esta_lleno +
                ", avenida=" + avenida +
                ", calle=" + calle +
                ", habitantes=" + habitantes.size() +
                ", n_robots_en_alerta=" + n_robots_en_alerta +
                ", n_robots_total=" + n_robots_total +
                ", n_problemas_sucedidos=" + nProblemasSucedidos +
                "}\n";
    }
    
    //equals usando id y hasmap
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Edificio other = (Edificio) obj;
        return this.id != null && this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
