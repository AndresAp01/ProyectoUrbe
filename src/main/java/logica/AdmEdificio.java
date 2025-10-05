/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;
import modelo.Edificio;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Optional;


/**
 *
 * @author linuxman
 */
public class AdmEdificio {
    private ArrayList<Edificio> listaEdificios;
    private int total_problemas_edificios;
    
    public AdmEdificio(){
        this.listaEdificios = new ArrayList<Edificio>();
    }

    public ArrayList<Edificio> getListaEdificios(){
        return this.listaEdificios;
    }

    //metodo de edificios impactados

    public boolean agregarEdificios(int cantidad){
        String[] nombresEdificios = {"Residencial Los Robles", "Condominio Vista Del Valle", "Torres Del Lago", "Apartamentos La Hacienda", "Residencial El Mirador", "Condominio Jardines Del Sol", "Torres Altavista", "Residencial Palmas Del Sur", "Condominio Bosques De La Montaña", "Apartamentos Brisas Del Rio"};
        int i=0;
        Random rand =  new Random();
        if (cantidad==0){
            int tope=rand.nextInt(3,10);
            while (i < tope) {
                //System.out.println("Ed" + (i + 1) +", "+nombresEdificios[i]);
                Edificio edificio = new Edificio("Ed" + (i + 1), nombresEdificios[i]);
                listaEdificios.add(edificio);
                i++;
            }
        }
        else {
            while (i < cantidad) {
                //System.out.println("Ed" + (i + 1) +", "+nombresEdificios[i]);
                Edificio edificio = new Edificio("Ed" + (i + 1), nombresEdificios[i]);
                listaEdificios.add(edificio);
                i++;
            }
        }
       return true;
    }

    public Edificio consultar(String elId){
        for (int i = 0; i<listaEdificios.size(); i++){
            Edificio Edificio = listaEdificios.get(i);
            if (elId.equals(Edificio.getId()))
                return Edificio;
        } // for
        return null;   // no encontró el Ciudadano

    }

    public boolean modificar(Edificio nuevoEdificio){
        for (int i = 0; i < listaEdificios.size(); i++){
            if (listaEdificios.get(i).equals(nuevoEdificio)){
                listaEdificios.set(i, nuevoEdificio);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String elId){
        for (int i = 0; i < listaEdificios.size(); i++){
            if (elId.equals(listaEdificios.get(i).getId())){
                listaEdificios.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // funciones
    
    public int TotalProblemasEdificios(){
        for (Edificio EdificioActual: listaEdificios)
            total_problemas_edificios += EdificioActual.getN_problemas_sucedidos();
        return total_problemas_edificios;
    }

    public int retornaCantidadDeEdificios (){
        return listaEdificios.size();
    }
  
    public int edificiosImpactados(){
        int problemas = 0;
        for (Edificio EdificioActual: listaEdificios){
            if (EdificioActual.getN_problemas_sucedidos() > 0){
                problemas += 1;
            }
        }
        return problemas;
    }
    
    public ArrayList<Edificio> obtenerTop3EdificiosOcupacion() {
        return listaEdificios.stream()
                .sorted(Comparator.comparingInt(Edificio::porcentajeOcupacion).reversed())
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Edificio> obtenerTop3EdificiosCiudadanosSinRobot() {
        return listaEdificios.stream()
                .sorted(Comparator.comparingInt(Edificio::porcentajeCiudadanosSinRobot).reversed())
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Edificio> obtenerTop3EdificiosRobotAlerta() {
        return listaEdificios.stream()
                .sorted(Comparator.comparingInt(Edificio::robots_en_alerta).reversed())
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public int mediaRobots(){
        int mediaR = 0;
        for (Edificio EdificioActual: listaEdificios){
            mediaR += EdificioActual.getN_robots_total();
        }
        mediaR = mediaR / listaEdificios.size();
        return mediaR;
    }
    
    public int mediaCiudadanos(){
        int mediaC = 0;
        for (Edificio EdificioActual: listaEdificios){
            mediaC += EdificioActual.getEspacios_ocupados();
        }
        mediaC = mediaC / listaEdificios.size();
        return mediaC;
    }

    public Optional<Edificio> obtenerEdificioConMasRobots() {
    return listaEdificios.stream()
            .max(Comparator.comparingInt(Edificio::robots_totales));
    }
    
    public Optional<Edificio> obtenerEdificioConMasCiudadanos() {
    return listaEdificios.stream()
            .max(Comparator.comparingInt(Edificio::getEspacios_ocupados));
    }
    
    public Edificio retornarEdificioDisponible(){
        for (Edificio EdificioActual: listaEdificios){
            if (!EdificioActual.isSi_esta_lleno()){
                return EdificioActual;
            }
        }
        return null;
    }






    @Override
    public String toString() {
        return "lista de Edificios:\n" + listaEdificios+"\n";
    }
}


