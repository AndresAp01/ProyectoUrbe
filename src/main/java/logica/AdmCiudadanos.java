/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;
import modelo.Ciudadano;
import java.util.Random;
import modelo.Edificio;
import modelo.Regla;
/**
 *
 * @author linuxman
 */
public class AdmCiudadanos {
    private ArrayList<Ciudadano> listaCiudadanos;
    private int total_tareas;
    private int total_tareas_rechazadas;
    
    public AdmCiudadanos(){
        this.listaCiudadanos = new ArrayList<Ciudadano>();
    }
    
    public boolean agregar(Ciudadano unCiudadano){
        for (Ciudadano CiudadanoActual: listaCiudadanos)
            if (CiudadanoActual.equals(unCiudadano))
                return false;
        listaCiudadanos.add(unCiudadano);
        return true;
    }

    public Ciudadano consultar(String elId){
        for (int i = 0; i<listaCiudadanos.size(); i++){
            Ciudadano Ciudadano = listaCiudadanos.get(i);
            if (elId.equals(Ciudadano.getId()))
                return Ciudadano;
        } // for
        return null;   // no encontró el Ciudadano

    }

    public boolean modificar(Ciudadano nuevoCiudadano){
        for (int i = 0; i < listaCiudadanos.size(); i++){
            if (listaCiudadanos.get(i).equals(nuevoCiudadano)){
                listaCiudadanos.set(i, nuevoCiudadano);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String elId){
        for (int i = 0; i < listaCiudadanos.size(); i++){
            if (elId.equals(listaCiudadanos.get(i).getId())){
                listaCiudadanos.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // funciones
    
    public int TotalTareasHechas(){
        for (Ciudadano CiudadanoActual: listaCiudadanos)
            total_tareas += CiudadanoActual.getTareas_hechas();
        return total_tareas;
    }
    
    public int TotalTareasRechazadas(){
        for (Ciudadano CiudadanoActual: listaCiudadanos)
            total_tareas_rechazadas += CiudadanoActual.getTareas_rechazadas();
        return total_tareas_rechazadas;
    }
    
    public void pedirTareas(CantTareasUsadas cantTareasUsadas, Regla regla){
        Random rand = new Random();
        for (Ciudadano CiudadanoActual: listaCiudadanos){
            int numeroRandom = rand.nextInt(2);
            if (numeroRandom == 0){
                CiudadanoActual.pedir_tarea(cantTareasUsadas, regla);
            }
        }
    }
    
    public boolean crearListaCiudadanos(AdmEdificio AdmEdificio, int cant){
        int tamInicial = listaCiudadanos.size();  // Tamaño fijo inicial
        for (int i = (tamInicial+1); i<=(cant+tamInicial); i++){
            
            Edificio EdificioDisponible = AdmEdificio.retornarEdificioDisponible();
            if (EdificioDisponible == null){ //si noe xiste ningun edificio disponible
                return false; //retorna falso y no crea los ciudadanos o no crea a todos los ciudadanos
            } 
            String id = "CEH-"+i;
            Ciudadano nCiudadano = new Ciudadano(id, EdificioDisponible);
            
            EdificioDisponible.agregarCiudadano(nCiudadano); //crea ciudadano y lo agrega al edificio
            listaCiudadanos.add(nCiudadano);
            
            
            
            
        }
        
        return true; //todo salio bien
    }
    
    public boolean mostrarCiudadanos(){
        for (Ciudadano unCiudadano : listaCiudadanos){
            System.out.println(unCiudadano.toString());
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "lista de Robots:\n" + listaCiudadanos ;
    }
}
