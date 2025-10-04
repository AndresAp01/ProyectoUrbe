package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.Registro;

public class CInteligencia {
    private ArrayList<String>listaAcciones;
    private ArrayList<Registro> listaRegistros;
    private ArrayList<String>listaAccionesAsignadas;

    public CInteligencia() {
        listaRegistros = new ArrayList<>();
        listaAcciones = new ArrayList<>();
        listaAccionesAsignadas = new ArrayList<>();
    }
    public ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    //----------------------------------------- ACCIONES CRUD -----------------------------------------

    public boolean crearAccionesIniciales() {
        listaAcciones.add("Llamar911"); listaAcciones.add("Contactar oficiales de tránsito a apersonarse al lugar"); listaAcciones.add("Convocar ambulancias"); listaAcciones.add("Contactar a los bomberos");
        return true;
    }

    public boolean agregarAcciones(String accionAAgregar){
        listaAcciones.add(accionAAgregar);
        return true;
    }

    public boolean cambiarAccion(int posicion, String nuevaAccion){
        int i =0;
        while (i<posicion){
            i++;
        }
        listaAcciones.set(i, nuevaAccion);
        return true;
    }

    public boolean eliminarAccion(int posicion){
        int i =0;
        while (i<posicion){
            i++;
        }
        listaAcciones.remove(i);
        return true;
    }

    //----------------------------------------------------------------------------------------------------

    public boolean crearRegistro(TipoAnomalia anomaliaDetectada, Dron dron){
        //ArrayList<Accion> accionesTomadas = asignarAcciones(anomaliaDetectada);
        Registro registro = new Registro(LocalDate.now(), LocalTime.now(), anomaliaDetectada, dron/*, accionesTomadas*/);
        listaRegistros.add(registro);
        return true;
    }
}
