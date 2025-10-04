package logica;

import modelo.Anomalia;
import modelo.TipoAnomalia;

import java.util.ArrayList;

public class AdmAnomalias {
    private ArrayList<Anomalia> listaAnomalias;

    public AdmAnomalias(){ this.listaAnomalias = new ArrayList<>(); }

    public ArrayList<Anomalia> getListaAnomalias() {
        return listaAnomalias;
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

    public boolean agregarAccionAnomalia(String accion) {

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

    @Override
    public String toString() { return "Lista Anomalias: \n" + listaAnomalias; }

}
