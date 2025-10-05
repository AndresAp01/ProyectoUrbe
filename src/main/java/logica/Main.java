package logica;
//MANTENER ENTRADAS DE UN SOLO VALOR EN LOS ADMINISTRADORES ESPECIFICADO LO MEJOR POSIBLE


import modelo.TipoAnomalia;

import javax.sound.midi.SysexMessage;

import static modelo.TipoAnomalia.congestionVehicular;
import static modelo.TipoAnomalia.desarrolloObraPublica;

public class Main {
    public static void main(String[] args) {

        //----------------------------------------- PERFIL ADMINISTRADOR -----------------------------------------
        PerfilAdmin controlAdmin = new PerfilAdmin();
        PerfilOperador controlOperador = new PerfilOperador();
        //control.crearDrones(25);
        //control.mostrarDrones();
        //opcion 0 significa random entre 3 y 10
        System.out.println(controlAdmin.crearEdificios(0));
        System.out.println(controlAdmin.mostrarEdificios());

        //opcion 0 significa random entre 5 y 8
        System.out.println(controlAdmin.crearCargaficios(8));
        System.out.println(controlAdmin.modificarEstadoCargaficios());
        System.out.println(controlAdmin.mostrarCargaficios());

        //SE DEBE PODER MODIFICAR EL ESTADO DE CADA UNO DE LOS CARGAFICIOS

        //En la parte de asignar las respuestas a cada anomalía, primero se llama a la funcion crearAnomalias
        //luego se procede a la asignacion, primero se debe crear el objeto anomalía.

        //Crea las anomalías:
        int[]index={2,3,4,6};
        System.out.println(controlAdmin.crearAnomalias(index));
        System.out.println(controlAdmin.mostrarAnomalias());

        //Agrega nuevas acciones a las anomalias.
        System.out.println(controlAdmin.crearNuevaAccion("Comer sopita de pollo", congestionVehicular));
        System.out.println(controlAdmin.mostrarAnomalias());

        System.out.println(controlAdmin.eliminarAccion(0, congestionVehicular));
        System.out.println(controlAdmin.mostrarAnomalias());

        System.out.println(controlAdmin.modificarAccion(1, "Llamar a Teletica", desarrolloObraPublica));
        System.out.println(controlAdmin.mostrarAnomalias());

        System.out.println(controlAdmin.crearListaReglas());
        System.out.println(controlAdmin.activarReglas(0, 20));
        System.out.println(controlAdmin.mostrarListaReglas());
        System.out.println(controlAdmin.activarReglas(1, 20));
        System.out.println(controlAdmin.mostrarListaReglas());

        System.out.println(controlAdmin.desactivarReglas(0));
        System.out.println(controlAdmin.mostrarListaReglas());

        //----------------------------------------- CIERRE PERFIL ADMINISTRADOR -----------------------------------------

        //----------------------------------------- PERFIL OPERADOR  -----------------------------------------

        System.out.println(controlAdmin.crearDronesMedianteOperador());
        //System.out.println(controlAdmin.mostrarDronesMedianteOperador());
        System.out.println(controlAdmin.simulateMedianteOperador());

        //----------------------------------------- CIERRE PERFIL OPERADOR  -----------------------------------------

        //----------------------------------------- PERFIL GENERAL  -----------------------------------------

        //retorna int de cantidad
        System.out.println(controlAdmin.edificiosImpactadosMedianteGeneral());

        //                                   edificio            edificio    edificio
        //retorna matriz, formato [[int cantidad por anomalia],[.........],[.........]]
        System.out.println(controlAdmin.porTipoAnomaliaYEdificioMedianteGeneral());

        //retorna matriz, formato [[String accion tomada, int cantidad]]
        System.out.println(controlAdmin.porAccionEjecutadaMedianteGeneral());

        //PUEDE RETORNAR UNA LISTA VACIA SI NINGUN DRON SALIO A PATRULLAR
        //retorna matriz, formato [[String nombre del edificio, String id edificio, String accion tomada, LocalDate.time hora]]
        System.out.println(controlAdmin.tablaEdificiosIncidentesMedianteGeneral());

        //retorna lista, formato [String nombre del edificio, String id ediicio]
        System.out.println(controlAdmin.mayorReincidenciaMedianteGeneral());
        
        //----------------------------------------- CIERRE PERFIL GENERAL  -----------------------------------------


    }
}
