package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Registro {
    private LocalDate fecha;
    private LocalTime hora;
    private TipoAnomalia tipoAnomalia;
    private Dron dronDetectoAnomalia;
    private ArrayList<Accion> accionesTomadas;

    public Registro(LocalDate fecha, LocalTime hora, TipoAnomalia tipoAnomalia, Dron  dronDetectoAnomalia, ArrayList<Accion> accionesTomadas) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAnomalia = tipoAnomalia;
        this.dronDetectoAnomalia = dronDetectoAnomalia;
        this.accionesTomadas = new ArrayList<>();
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
    public ArrayList<Accion> getAccionesTomadas() {
        return accionesTomadas;
    }
}
