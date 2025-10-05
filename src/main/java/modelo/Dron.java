package modelo;

/**
 * Clase que representa un Dron dentro de la ciudad.
 * Cada dron posee un procesador, nivel de batería, estado de alerta, estado de actividad
 * y puede patrullar un edificio por un número limitado de horas.
 * 
 * Los drones tienen un valor mínimo de batería, debajo del cual deben recargarse.
 */
public class Dron {
    private String procesador;
    private int bateria;
    private boolean estadoDeAlerta;
    private boolean activo;
    private boolean enPatrulla;
    private Edificio edificioPatrullado;
    private int horasVueloRestantes;
    private int valorMinimo;

    /**
     * Constructor del dron.
     * 
     * @param procesador Nombre o identificador del procesador del dron.
     * @param horasVueloRestantes Cantidad de horas de vuelo restantes.
     * @param bateria Nivel de batería inicial del dron.
     * @param estadoAlerta Estado de alerta inicial del dron.
     * @param activo Estado activo inicial del dron.
     * @param enPatrullado Indica si el dron está actualmente en patrulla.
     */
    public Dron(String procesador, int horasVueloRestantes, int bateria, boolean estadoAlerta, boolean activo, boolean enPatrullado) {
        this.procesador = procesador;
        this.bateria = bateria;
        this.estadoDeAlerta = estadoAlerta;
        this.activo = activo;
        this.enPatrulla = enPatrullado;
        this.horasVueloRestantes = horasVueloRestantes;
        this.valorMinimo = 25;
    }

    /**
     * Método para enviar el dron a recargar.
     * Actualmente retorna true de manera fija.
     * 
     * @return true indicando que el dron fue enviado a recargar.
     */
    public boolean enviarDronARecargar() {
        return true;
    }

    // Getters y Setters

    /** @return Nombre del procesador del dron */
    public String getProcesador() { return procesador; }

    /** @param procesador Nuevo nombre o identificador del procesador */
    public void setProcesador(String procesador) { this.procesador = procesador; }

    /** @return Nivel de batería del dron */
    public int getBateria() { return bateria; }

    /** @param bateria Nuevo nivel de batería del dron */
    public void setBateria(int bateria) { this.bateria = bateria; }

    /** @return true si el dron está en estado de alerta */
    public boolean getEstadoDeAlerta() { return estadoDeAlerta; }

    /** @param estadoDeAlerta Nuevo estado de alerta */
    public void setEstadoDeAlerta(boolean estadoDeAlerta) { this.estadoDeAlerta = estadoDeAlerta; }

    /** @return true si el dron está activo */
    public boolean getActivo() { return activo; }

    /** @param activo Nuevo estado de actividad del dron */
    public void setActivo(boolean activo) { this.activo = activo; }

    /** @return true si el dron está en patrulla */
    public boolean getEnPatrulla() { return enPatrulla; }

    /** @param enPatrulla Nuevo estado de patrulla */
    public void setEnPatrulla(boolean enPatrulla) { this.enPatrulla = enPatrulla; }

    /** @return Edificio que está siendo patrullado por el dron */
    public Edificio getEdificioPatrullado() { return edificioPatrullado; }

    /** @param horasVueloRestantes Nuevo valor de horas de vuelo restantes */
    public void setHorasVueloRestantes(int horasVueloRestantes) { this.horasVueloRestantes = horasVueloRestantes; }

    /** @return Horas de vuelo restantes del dron */
    public int getHorasVueloRestantes() { return horasVueloRestantes; }

    /**
     * Representación en String del dron.
     * Incluye procesador, batería, estado de alerta, activo, patrulla y horas de vuelo.
     * 
     * @return Información del dron en formato String
     */
    @Override
    public String toString() {
        return "{Procesador " + procesador +
                ", bateria " + bateria +
                ", estadoDeAlerta " + estadoDeAlerta +
                ", activo " + activo +
                ", enPatrulla " + enPatrulla +
                ", horasVueloRestantes " + horasVueloRestantes +
                "}";
    }
}
