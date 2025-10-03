package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.Registro;

public class CInteligencia {
    private ArrayList<Registro> listaRegistros;

    public CInteligencia() {
        listaRegistros = new ArrayList<>();
    }
    public ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    public ArrayList<Accion> asignarAcciones(TipoAnomalia tipoAnomalia) {
        ArrayList<Accion> listaAcciones = new ArrayList<>();
        switch (tipoAnomalia) {
            case colisionVehicular:
                listaAcciones.add(Accion.llamar911);
                listaAcciones.add(Accion.contactarOficialesDeTransito);
                listaAcciones.add(Accion.convocarAmbulancias);
                break;
            case congestionVehicular:
                listaAcciones.add(Accion.contactarOficialesDeTransito);
                break;
            case desarrolloObraPublica:
                listaAcciones.add(Accion.contactarOficialesDeTransito);
                listaAcciones.add(Accion.contactarPolicia);
                break;
            case derramesSustanciasEnCarretera:
                listaAcciones.add(Accion.contactarOficialesDeTransito);
                listaAcciones.add(Accion.llamar911);
                listaAcciones.add(Accion.contactarBomberos);
                break;
            case incendio:
                listaAcciones.add(Accion.llamar911);
                listaAcciones.add(Accion.contactarBomberos);
                listaAcciones.add(Accion.contactarPolicia);
                listaAcciones.add(Accion.convocarAmbulancias);
                break;
            case presenciaHumo:
                listaAcciones.add(Accion.contactarBomberos);
                listaAcciones.add(Accion.contactarPolicia);
                listaAcciones.add(Accion.llamar911);
                listaAcciones.add(Accion.convocarAmbulancias);
                break;
            case presenciaGases:
                listaAcciones.add(Accion.contactarBomberos);
                listaAcciones.add(Accion.llamar911);
                listaAcciones.add(Accion.contactarPolicia);
                listaAcciones.add(Accion.convocarAmbulancias);
                break;
            case accidenteGrave:
                listaAcciones.add(Accion.llamar911);
                listaAcciones.add(Accion.contactarPolicia);
                listaAcciones.add(Accion.contactarOficialesDeTransito);
                listaAcciones.add(Accion.convocarAmbulancias);
                break;
            case presenciaAmbulanciasEstadoEmergencia:
                listaAcciones.add(Accion.contactarPolicia);
                listaAcciones.add(Accion.contactarBomberos);
                break;
        }
        return  listaAcciones;
    }

    public boolean crearRegistro(TipoAnomalia anomaliaDetectada, Dron dron){
        ArrayList<Accion> accionesTomadas = asignarAcciones(anomaliaDetectada);
        Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomaliaDetectada, dron, accionesTomadas);
        listaRegistros.add(registro);
        return true;
    }
}
