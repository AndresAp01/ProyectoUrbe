package modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa una anomalía en la vía pública.
 * Cada anomalía está ubicada en una avenida y calle específicas,
 * tiene un tipo definido y una lista de acciones recomendadas.
 */
public class Anomalia {
    private Avenida avenida;
    private Calle calle;
    private TipoAnomalia tipoAnomalia;
    private boolean anomaliaActiva;
    private ArrayList<String> listaAcciones;

    /**
     * Genera la lista de acciones posibles según el tipo de anomalía.
     * @param tipoAnomalia Tipo de anomalía a evaluar.
     * @return Lista de acciones recomendadas para la anomalía.
     */
    public ArrayList<String> agregarAcciones(TipoAnomalia tipoAnomalia){
        listaAcciones = new ArrayList<String>();
        switch (tipoAnomalia){
            case colisionVehicular:
                listaAcciones.add("Llamar al 911");
                listaAcciones.add("Contactar a oficiales de tránsito a apersonarse al lugar");
                listaAcciones.add("Convocar ambulancias");
                break;
            case congestionVehicular:
                listaAcciones.add("Contactar a oficiales de tránsito a apersonarse al lugar");
                break;
            case  desarrolloObraPublica:
                listaAcciones.add("Contactar a fuerza publica");
                listaAcciones.add("Contactar a oficiales de tránsito a apersonarse al lugar");
                break;
            case derramesSustanciasEnCarretera:
                listaAcciones.add("Llamar al 911");
                listaAcciones.add("Contactar a fuerza publica");
                listaAcciones.add("Contactar a oficiales de tránsito a apersonarse al lugar");
                listaAcciones.add("Contactar a los bomberos");
                break;
            case incendio:
                listaAcciones.add("Llamar al 911");
                listaAcciones.add("Contactar a los bomberos");
                listaAcciones.add("Contactar a fuerza publica");
                listaAcciones.add("Convocar ambulancias");
                break;
            case presenciaHumo:
                listaAcciones.add("Llamar al 911");
                listaAcciones.add("Contactar a los bomberos");
                listaAcciones.add("Contactar a fuerza publica");
                listaAcciones.add("Convocar ambulancias");
                break;
            case presenciaGases:
                listaAcciones.add("Llamar al 911");
                listaAcciones.add("Contactar a los bomberos");
                listaAcciones.add("Contactar a fuerza publica");
                listaAcciones.add("Convocar ambulancias");
                break;
            case accidenteGrave:
                listaAcciones.add("Llamar al 911.");
                listaAcciones.add("Contactar a oficiales de tránsito a apersonarse al lugar");
                listaAcciones.add("Convocar ambulancias");
                break;
            case presenciaAmbulanciasEstadoEmergencia:
                listaAcciones.add("Llamar al 911");
                listaAcciones.add("Contactar a oficiales de tránsito a apersonarse al lugar");
                break;
        }
        return listaAcciones;
    }

    /**
     * Constructor que inicializa una anomalía con tipo definido.
     * Asigna avenida y calle aleatoriamente y genera la lista de acciones.
     * @param tipoAnomalia Tipo de anomalía.
     */
    public Anomalia(TipoAnomalia tipoAnomalia) {
        Random rand = new Random();
        Avenida[] avenidas = Avenida.values();
        Calle[] calles = Calle.values();
        this.avenida = avenidas[rand.nextInt(Avenida.values().length)];
        this.calle = calles[rand.nextInt(Calle.values().length)];
        this.tipoAnomalia = tipoAnomalia;
        this.anomaliaActiva = false;
        this.listaAcciones = agregarAcciones(tipoAnomalia);
    }

    /** Establece si la anomalía está activa. */
    public void setAnomaliaActiva(boolean anomaliaResuelta) {
        this.anomaliaActiva = anomaliaResuelta;
    }

    /** Obtiene si la anomalía está activa. */
    public boolean getAnomaliaActiva() {
        return anomaliaActiva;
    }

    /** Establece el tipo de anomalía. */
    public void setTipoAnimalia(TipoAnomalia tipoAnomalia) {
        this.tipoAnomalia = tipoAnomalia;
    }

    /** Obtiene el tipo de anomalía. */
    public TipoAnomalia getTipoAnomalia() {
        return tipoAnomalia;
    }

    /** Establece la avenida de la anomalía. */
    public void setAvenida(Avenida avenida) {
        this.avenida = avenida;
    }

    /** Obtiene la avenida de la anomalía. */
    public Avenida getAvenida() {
        return avenida;
    }

    /** Establece la calle de la anomalía. */
    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    /** Obtiene la calle de la anomalía. */
    public Calle getCalle() {
        return calle;
    }

    /** Obtiene la lista de acciones de la anomalía. */
    public ArrayList<String> getListaAcciones() {
        return listaAcciones;
    }

    /** Establece la lista de acciones de la anomalía. */
    public void setListaAcciones(ArrayList<String> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    /** Representación en cadena de la anomalía. */
    public String toString() {
        return "{Avenida: "+avenida+
                ", Calle: "+calle+
                ", Tipo Anomalia: "+tipoAnomalia+
                ", Anomalia Activa: "+anomaliaActiva+
                ", listaAcciones: "+listaAcciones+"}\n";
    }
}
