package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Registro {
    private LocalDate fecha;
    private LocalTime hora;
    private TipoAnomalia tipoAnomalia;
    private Dron dronDetectoAnomalia;
    private String accionTomada;

    public Registro(LocalDate fecha, LocalTime hora, TipoAnomalia tipoAnomalia, Dron  dronDetectoAnomalia, String accionTomada) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAnomalia = tipoAnomalia;
        this.dronDetectoAnomalia = dronDetectoAnomalia;
        this.accionTomada = accionTomada;
    }

    public Registro(LocalDate fecha, LocalTime hora,  TipoAnomalia tipoAnomalia) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAnomalia = tipoAnomalia;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public TipoAnomalia getTipoAnomalia() {
        return tipoAnomalia;
    }
    public void setTipoAnomalia(TipoAnomalia tipoAnomalia) {
        this.tipoAnomalia = tipoAnomalia;
    }
    public Dron getDronDetectoAnomalia() {
        return dronDetectoAnomalia;
    }

    public void setAccionTomada(String accionTomada) {
        this.accionTomada = accionTomada;
    }
    public String getAccionTomada() {
        return accionTomada;
    }

    public String toString(){
        return "{Fecha: "+fecha+
                ", hora: "+hora+
                ", tipo de anomalia: "+tipoAnomalia+
                ", dron que la detecto: "+dronDetectoAnomalia+
                ", accion tomada: "+accionTomada
                +"}\n";
    }

    public String toString2(){
        return "{Fecha: "+fecha+
                ", hora: "+hora+
                ", tipo de anomalia: "+tipoAnomalia+
                "}\n";
    }

}
