package logica;

import modelo.Anomalia;
import modelo.Dron;
import modelo.Edificio;
import modelo.Registro;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

public class AdmDron {
    private ArrayList<Dron> listaDrones;

    public AdmDron(){ this.listaDrones = new ArrayList<>(); }

    public ArrayList<Dron> getListaDrones() {
        return listaDrones;
    }

    public boolean crearListaDrones(int cantEdificios){
        int i =0;
        Random rand = new Random();
        while (i<rand.nextInt(cantEdificios*2)){
            int horasVuelo = rand.nextInt(4)+1;
            int bateria = 25*horasVuelo;
            boolean estadoAlerta = false;
            boolean activo = true;
            if (bateria<=25){
                estadoAlerta = true;
                activo=false;
            }
            Dron nuevoDron = new Dron("NVIDIAJetson"+(i+1), horasVuelo, bateria, estadoAlerta, activo,false);
            i++;
        }
    return true;
    }

    public boolean asignarDronesAEdificios(ArrayList<Edificio> listaEdificios){
        int i = 0;
        while (i<listaDrones.size()){
            for (Edificio unEdificio : listaEdificios) {
                if (unEdificio.getDrones().isEmpty()) {
                    ArrayList<Dron> nlista = unEdificio.getDrones();
                    nlista.add(listaDrones.get(i));
                    unEdificio.setDrones(nlista);
                    i++;
                }
            }
            for (Edificio unEdificio : listaEdificios){
                ArrayList<Dron> nlista = unEdificio.getDrones();
                nlista.add(listaDrones.get(i));
                unEdificio.setDrones(nlista);
                i++;
            }
        }
        return true;
    }

    public Dron encontrarAnomalias(ArrayList<Anomalia> listaAnomalias,ArrayList<Registro> listaRegistros){

        for (Dron unDron : listaDrones){
            if (unDron.getEnPatrulla()){
                for (Anomalia anomalia : listaAnomalias){
                    /*
                    if (anomalia.getCalle()==unDron.getEdificioPatrullado().getCalle()||anomalia.getAvenida()==unDron.getEdificioPatrullado().getAvenida()){
                        return unDron;

                    }
                    */
                    System.out.println(anomalia);
                }
            }
        }
        return null;
    }

    public boolean enviarDronesAPatrullar(int valorMinimoBateria, ArrayList<Anomalia> listaAnomalias, ArrayList<Registro> listaRegistros){
        for (Dron unDron : listaDrones){
            if ((unDron.getBateria()-25)<=valorMinimoBateria){
                unDron.enviarDronARecargar();
            }
            else{
                unDron.setEnPatrulla(true);
                unDron.setBateria(unDron.getBateria()-25);
            }
        }
        return true;
    }

    public boolean mostrarDrones(){
        for (Dron unDron : listaDrones){
            System.out.println(unDron.toString());
        }
        return true;
    }




}
