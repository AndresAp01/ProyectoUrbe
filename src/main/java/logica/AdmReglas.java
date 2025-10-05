package logica;

import modelo.Regla;
import java.util.ArrayList;

public class AdmReglas {
    private ArrayList<Regla> listaReglas;

    public AdmReglas(){ this.listaReglas = new ArrayList<>(); }

    public ArrayList<Regla> getListaReglas() { return listaReglas; }

    public boolean crearListaReglas(){
        Regla regla1=new Regla(0);
        Regla regla2=new Regla(1,0);
        listaReglas.add(regla1);
        listaReglas.add(regla2);
        return true;
    }

    public boolean activarReglas(int op, int valorMinimo) {
        if (op==0){
            listaReglas.get(0).setActiva(true);
            listaReglas.get(0).setValorMinimoBateria(valorMinimo);
        }
        else{
            listaReglas.get(1).setActiva(true);
            listaReglas.get(1).setValorMinimoBateria(valorMinimo);
        }
        return true;
    }

    public boolean desactivarReglas(int op) {
        if (op==0){
            listaReglas.get(0).setActiva(false);
            listaReglas.get(0).setValorMinimoBateria(0);
        }
        else{
            listaReglas.get(1).setActiva(false);
            listaReglas.get(1).setValorMinimoBateria(0);
        }
        return true;
    }

    public String toString(){
        return "Lista Reglas: "+listaReglas;
    }

}
