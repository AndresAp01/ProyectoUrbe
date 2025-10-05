package logica;

import modelo.Anomalia;
import modelo.Dron;
import modelo.Registro;
import modelo.TipoAnomalia;

import java.util.ArrayList;
import java.util.Random;

public class AdmAnomalias {
    private ArrayList<Anomalia> listaAnomalias;
    private ArrayList<Registro> listaAnomaliasNoDetectadas;

    public AdmAnomalias(){
        this.listaAnomalias = new ArrayList<>();
        this.listaAnomaliasNoDetectadas = new ArrayList<>();
    }

    public ArrayList<Anomalia> getListaAnomalias() {
        return listaAnomalias;
    }

    public boolean agregarRegistroAnomaliaNoDetectada(Registro registro){
        listaAnomaliasNoDetectadas.add(registro);
        return true;
    }

    public ArrayList<Registro> getListaAnomaliasNoDetectadas() {
        return listaAnomaliasNoDetectadas;
    }

    public boolean crearListaAnomalias(int[] listaIndex) {
        TipoAnomalia[] tipoAnomalia = TipoAnomalia.values();
        for (int index : listaIndex) {
            int i = 0;
            while (i != index) {
                i++;
            }
            Anomalia nuevaAnomalia = new Anomalia(tipoAnomalia[index]);
            listaAnomalias.add(nuevaAnomalia);
        }

        return true;
    }

    //si retorna false signiica que ya habia una accion con ese nombre
    public boolean asignarNuevaAccion(String nuevaAccion, TipoAnomalia tipoAnomalia) {
        for (Anomalia anomalia : listaAnomalias) {
            if (anomalia.getTipoAnomalia().equals(tipoAnomalia)) {
                ArrayList<String> listaAcciones= anomalia.getListaAcciones();
                for (String accion : listaAcciones){
                    if (accion.equals(nuevaAccion)){
                        return false;
                    }
                }
                listaAcciones.add(nuevaAccion);
                anomalia.setListaAcciones(listaAcciones);
            }
        }
        return true;
    }

    //retorna true cuando ya elimino la accion, retorna false si el index es mayor al largo de la lista
    public boolean eliminarAccion(int indexEliminar, TipoAnomalia tipoAnomalia) {
        for (Anomalia anomalia : listaAnomalias) {
            if (anomalia.getTipoAnomalia().equals(tipoAnomalia)) {
                int i = 0;
                ArrayList<String> listaAcciones= anomalia.getListaAcciones();
                if (indexEliminar >= listaAcciones.size()) {
                    return false;
                }
                while (i!= indexEliminar){
                    i++;
                }
                listaAcciones.remove(indexEliminar);
                anomalia.setListaAcciones(listaAcciones);
                return true;

            }
        }
        return false;
    }


    public boolean modificarAccion(int indexModificar, String modificacion, TipoAnomalia tipoAnomalia) {

        for (Anomalia anomalia : listaAnomalias) {
            if (anomalia.getTipoAnomalia().equals(tipoAnomalia)) {
                int i = 0;
                ArrayList<String> listaAcciones= anomalia.getListaAcciones();
                if (indexModificar >= listaAcciones.size()) {
                    return false;
                }
                else {
                    while (i != indexModificar) {
                        i++;
                    }

                    listaAcciones.set(indexModificar, modificacion);
                    anomalia.setListaAcciones(listaAcciones);
                    return true;
                }

            }
        }
        return true;
    }

    public boolean activarAnomalias(){
        int valorAComparar = 3;
        Random rand = new Random();
        for (Anomalia anomalia : listaAnomalias) {
            int probabilidad = rand.nextInt(1,4);
            if (probabilidad == valorAComparar) {
                anomalia.setAnomaliaActiva(true);
                System.out.println(anomalia.toString());
            }
        }
        return true;
    }

    @Override
    public String toString() { return "Lista Anomalias: \n" + listaAnomalias; }

    public String toString2() {return "Lista Anomalias no detectadas: \n"+listaAnomaliasNoDetectadas; }

}
