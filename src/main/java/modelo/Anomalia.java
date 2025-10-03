package modelo;

public class Anomalia {
    private Avenida avenida;
    private Calle calle;
    private TipoAnomalia tipoAnomalia;
    private boolean anomaliaResuelta;

    public Anomalia(Avenida avenida, Calle calle, TipoAnomalia tipoAnomalia) {
        this.avenida = avenida;
        this.calle = calle;
        this.tipoAnomalia = tipoAnomalia;
        this.anomaliaResuelta = false;
    }

    public void setAnomaliaResuelta(boolean anomaliaResuelta) {
        this.anomaliaResuelta = anomaliaResuelta;
    }

    public boolean getAnomaliaResuelta() {
        return anomaliaResuelta;
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




}
