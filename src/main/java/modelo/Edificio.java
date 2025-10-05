package modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa un Edificio dentro de la ciudad.
 * Cada edificio tiene una capacidad máxima de habitantes, ubicación (avenida y calle),
 * y puede contener drones y ciudadanos. También mantiene estadísticas sobre robots
 * totales y en alerta dentro del edificio.
 * 
 * Esta clase permite agregar y eliminar ciudadanos, calcular porcentajes de ocupación
 * y de alerta de robots, y obtener información resumida del edificio.
 * 
 * @author linuxman
 */
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

    /**
     * Constructor de la clase Edificio.
     * Asigna valores iniciales y ubicación aleatoria.
     * 
     * @param id Identificador único del edificio.
     * @param nombre Nombre del edificio.
     */
    public Edificio(String id, String nombre){
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
        this.habitantes = new ArrayList<>();
        this.drones = new ArrayList<>();
        this.n_robots_en_alerta = 0;
        this.n_robots_total = 0;
        this.nProblemasSucedidos = 0;
    }

    // Getters y Setters
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getId() { re
