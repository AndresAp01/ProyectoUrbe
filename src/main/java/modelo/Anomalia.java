package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Anomalia {
    private Avenida avenida;
    private Calle calle;
    private TipoAnomalia tipoAnomalia;
    private boolean anomaliaActiva;
    private ArrayList<String> listaAcciones;

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

    public void setAnomaliaActiva(boolean anomaliaResuelta) {
        this.anomaliaActiva = anomaliaResuelta;
    }

    public boolean getAnomaliaActiva() {
        return anomaliaActiva;
    }

    public void setTipoAnimalia(TipoAnomalia tipoAnomalia) {
        this.tipoAnomalia = tipoAnomalia;
    }

    public TipoAnomalia getTipoAnomalia() {
        return tipoAnomalia;
    }

    public void setAvenida(Avenida avenida) {
        this.avenida = avenida;
    }

    public Avenida getAvenida() {
        return avenida;
    }

    public void  setCalle(Calle calle) {
        this.calle = calle;
    }

    public Calle getCalle() {
        return calle;
    }

    public ArrayList<String> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<String> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public String toString() {
        return "{Avenida: "+avenida+
                ", Calle: "+calle+
                ", Tipo Anomalia: "+tipoAnomalia+
                ", Anomalia Activa: "+anomaliaActiva+
                ", listaAcciones: "+listaAcciones+"}\n";
    }




}
package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Anomalia {
    private Avenida avenida;
    private Calle calle;
    private TipoAnomalia tipoAnomalia;
    private boolean anomaliaActiva;
    private ArrayList<String> listaAcciones;

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

    public void setAnomaliaActiva(boolean anomaliaResuelta) {
        this.anomaliaActiva = anomaliaResuelta;
    }

    public boolean getAnomaliaActiva() {
        return anomaliaActiva;
    }

    public void setTipoAnimalia(TipoAnomalia tipoAnomalia) {
        this.tipoAnomalia = tipoAnomalia;
    }

    public TipoAnomalia getTipoAnomalia() {
        return tipoAnomalia;
    }

    public void setAvenida(Avenida avenida) {
        this.avenida = avenida;
    }

    public Avenida getAvenida() {
        return avenida;
    }

    public void  setCalle(Calle calle) {
        this.calle = calle;
    }

    public Calle getCalle() {
        return calle;
    }

    public ArrayList<String> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<String> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public String toString() {
        return "{Avenida: "+avenida+
                ", Calle: "+calle+
                ", Tipo Anomalia: "+tipoAnomalia+
                ", Anomalia Activa: "+anomaliaActiva+
                ", listaAcciones: "+listaAcciones+"}\n";
    }
}
