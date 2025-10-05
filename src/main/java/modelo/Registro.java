package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa un registro de anomalía detectada por un dron.
 * Cada registro incluye fecha, hora, tipo de anomalía, dron que la detectó
 * y la acción tomada para resolverla (si aplica).
 * 
 * Esta clase permite crear registros completos o solo registros básicos sin acción ni dron.
 * Mantiene métodos para acceder y modificar sus atributos y representaciones en String.
 */
public class Registro {
    private LocalDate fecha;
    private LocalTime hora;
    private TipoAnomalia tipoAnomalia;
    private Dron dronDetectoAnomalia;
    private String accionTomada;

    /**
     * Constructor completo de un registro.
     * 
     * @param fecha Fecha en que se detectó la anomalía.
     * @param hora Hora en que se detectó la anomalía.
     * @param tipoAnomalia Tipo de anomalía detectada.
     * @param dronDetectoAnomalia Dron que detectó la anomalía.
     * @param accionTomada Acción tomada para resolver la anomalía.
     */
    public Registro(LocalDate fecha, LocalTime hora, TipoAnomalia tipoAnomalia, Dron dronDetectoAnomalia, String accionTomada) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAnomalia = tipoAnomalia;
        this.dronDetectoAnomalia = dronDetectoAnomalia;
        this.accionTomada = accionTomada;
    }

    /**
     * Constructor parcial sin dron ni acción tomada.
     * 
     * @param fecha Fecha en que se detectó la anomalía.
     * @param hora Hora en que se detectó la anomalía.
     * @param tipoAnomalia Tipo de anomalía detectada.
     */
    public Registro(LocalDate fecha, LocalTime hora, TipoAnomalia tipoAnomalia) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAnomalia = tipoAnomalia;
    }

    // Getters y Setters

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public TipoAnomalia getTipoAnomalia() { return tipoAnomalia; }
    public void setTipoAnomalia(TipoAnomalia tipoAnomalia) { this.tipoAnomalia = tipoAnomalia; }

    public Dron getDronDetectoAnomalia() { return dronDetectoAnomalia; }

    public String getAccionTomada() { return accionTomada; }
    public void setAccionTomada(String accionTomada) { this.accionTomada = accionTomada; }

    // Representación en String

    /**
     * Representación completa del registro.
     * Incluye fecha, hora, tipo de anomalía, dron que la detectó y acción tomada.
     * 
     * @return Información completa del registro en formato String.
     */
    public String toString() {
        return "{Fecha: "+fecha+
                ", hora: "+hora+
                ", tipo de anomalia: "+tipoAnomalia+
                ", dron que la detecto: "+dronDetectoAnomalia+
                ", accion tomada: "+accionTomada+
                "}\n";
    }

    /**
     * Representación parcial del registro.
     * Incluye solo fecha, hora y tipo de anomalía.
     * 
     * @return Información parcial del registro en formato String.
     */
    public String toString2() {
        return "{Fecha: "+fecha+
                ", hora: "+hora+
                ", tipo de anomalia: "+tipoAnomalia+
                "}\n";
    }
}
