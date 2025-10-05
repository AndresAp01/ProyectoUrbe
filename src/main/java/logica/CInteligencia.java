package logica;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import modelo.Anomalia;
import modelo.Dron;
import modelo.Registro;

public class CInteligencia {
    private ArrayList<Registro> listaRegistros;

    public CInteligencia() {
        listaRegistros = new ArrayList<>();
    }
    public ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    //----------------------------------------------------------------------------------------------------

    public String tomarDesicion(Anomalia anomaliaDetectada){
        Random rand = new Random();
        int largo = anomaliaDetectada.getListaAcciones().size();
        int index = rand.nextInt(largo);
        return anomaliaDetectada.getListaAcciones().get(index);
    }

    public boolean crearRegistro(Anomalia anomaliaDetectada, Dron dron){
        String accionTomada = tomarDesicion(anomaliaDetectada);
        Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomaliaDetectada.getTipoAnomalia(), dron, accionTomada);
        listaRegistros.add(registro);
        anomaliaDetectada.setAnomaliaActiva(false);
        return true;
    }

    public String toString(){
        return "Lista de Registros: "+listaRegistros;
    }
}
