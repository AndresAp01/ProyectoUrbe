/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;
import modelo.Edificio;
import java.util.Random;
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
    
    @Override
    public String toString() {
        return "lista de Edificios:\n" + listaEdificios+"\n" ;
    }
}


