package logica;

import modelo.Anomalia;
import modelo.Dron;
import modelo.Edificio;
import modelo.Registro;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
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
        int tope = rand.nextInt(3,(cantEdificios*2)-1);
        while (i<tope){
            int horasVuelo = rand.nextInt(4)+1;
            int bateria = 25*horasVuelo;
            boolean estadoAlerta = false;
            boolean activo = true;
            if (bateria<=25){
                estadoAlerta = true;
                activo=false;
            }
            Dron nuevoDron = new Dron("NVIDIAJetson"+(i+1), horasVuelo, bateria, estadoAlerta, activo,false);
            listaDrones.add(nuevoDron);
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

    public Map retornarAnomalias(Anomalia anomalia, Dron unDron){
        Map<Anomalia,Dron> datos =new HashMap<>();
        System.out.println(anomalia.getAnomaliaActiva());
        datos.put(anomalia,unDron);
        return datos;
    }

    public Dron buscarDronApto(ArrayList<Dron> listaMomentanea){
        Random rand = new Random();
        Dron unDron = listaDrones.get(rand.nextInt(1,listaDrones.size()));
        for (Dron otroDron : listaMomentanea) {
            if (unDron.equals(otroDron)) {
                return null;
            }
        }
        return  unDron;
    }

    public ArrayList<Dron> crearListaMomentanea(){
        Random rand = new Random();
        int limite = rand.nextInt(1,listaDrones.size());
        ArrayList<Dron> listaMomentanea = new ArrayList<>();
        while(listaMomentanea.size()<limite){
            Dron elDron = buscarDronApto(listaMomentanea);

            while (elDron==null){
                elDron= buscarDronApto(listaMomentanea);
            }
            listaMomentanea.add(elDron);
        }
        return listaMomentanea;
    }

    public boolean enviarDronesAPatrullar(int valorMinimoBateria){
        boolean hubo=false;
        ArrayList <Dron> listaDeDronesRandom=crearListaMomentanea();
        for (Dron unDron : listaDeDronesRandom) {
            if ((unDron.getBateria() - 25) <= valorMinimoBateria) {
                unDron.enviarDronARecargar();
            } else {
                unDron.setEnPatrulla(true);
                unDron.setBateria(unDron.getBateria() - 25);
                unDron.setHorasVueloRestantes(unDron.getHorasVueloRestantes()-1);
                hubo=true;
                System.out.println(unDron.toString());
            }
        }
        return hubo;
    }

    public boolean mostrarDrones(){
        for (Dron unDron : listaDrones){
            System.out.println(unDron.toString());
        }
        return true;
    }
}
