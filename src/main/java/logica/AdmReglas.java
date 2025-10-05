package logica;

import modelo.Regla;
import java.util.ArrayList;

/**
 * Clase que administra la lista de reglas del sistema.
 * Permite crear, activar, desactivar y consultar reglas relacionadas con el comportamiento de drones y robots.
 * Cada regla puede tener un valor mínimo de batería asociado.
 */
public class AdmReglas {

    /** Lista de reglas gestionadas por el sistema. */
    private ArrayList<Regla> listaReglas;

    /**
     * Constructor que inicializa la lista de reglas.
     */
    public AdmReglas() {
        this.listaReglas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de reglas.
     * @return ArrayList de objetos Regla.
     */
    public ArrayList<Regla> getListaReglas() {
        return listaReglas;
    }

    /**
     * Crea la lista inicial de reglas con dos reglas predefinidas.
     * @return true siempre que se ejecute.
     */
    public boolean crearListaReglas() {
        Regla regla1 = new Regla(0);
        Regla regla2 = new Regla(1, 0);
        listaReglas.add(regla1);
        listaReglas.add(regla2);
        return true;
    }

    /**
     * Activa una regla específica y le asigna un valor mínimo de batería.
     * @param op Índice de la regla (0 o 1).
     * @param valorMinimo Valor mínimo de batería a establecer.
     * @return true siempre que se ejecute.
     */
    public boolean activarReglas(int op, int valorMinimo) {
        if (op == 0) {
            listaReglas.get(0).setActiva(true);
            listaReglas.get(0).setValorMinimoBateria(valorMinimo);
        } else {
            listaReglas.get(1).setActiva(true);
            listaReglas.get(1).setValorMinimoBateria(valorMinimo);
        }
        return true;
    }

    /**
     * Desactiva una regla específica y reinicia su valor mínimo de batería a 0.
     * @param op Índice de la regla (0 o 1).
     * @return true siempre que se ejecute.
     */
    public boolean desactivarReglas(int op) {
        if (op == 0) {
            listaReglas.get(0).setActiva(false);
            listaReglas.get(0).setValorMinimoBateria(0);
        } else {
            listaReglas.get(1).setActiva(false);
            listaReglas.get(1).setValorMinimoBateria(0);
        }
        return true;
    }

    /**
     * Representación en String de la lista de reglas.
     * @return String que describe todas las reglas.
     */
    @Override
    public String toString() {
        return "Lista Reglas: " + listaReglas;
    }
}
