package logica;
//MANTENER ENTRADAS DE UN SOLO VALOR EN LOS ADMINISTRADORES ESPECIFICADO LO MEJOR POSIBLE
//holas

import modelo.TipoAnomalia;

import static modelo.TipoAnomalia.congestionVehicular;
import static modelo.TipoAnomalia.desarrolloObraPublica;

public class Main {
    public static void main(String[] args) {

        //----------------------------------------- PERFIL ADMINISTRADOR -----------------------------------------
        PerfilAdmin control = new PerfilAdmin();
        //control.crearDrones(25);
        //control.mostrarDrones();
        //opcion 0 significa random entre 3 y 10
        System.out.println(control.crearEdificios(0));
        System.out.println(control.mostrarEdificios());

        //opcion 0 significa random entre 5 y 8
        System.out.println(control.crearCargaficios(8));
        System.out.println(control.modificarEstadoCargaficios());
        System.out.println(control.mostrarCargaficios());

        //SE DEBE PODER MODIFICAR EL ESTADO DE CADA UNO DE LOS CARGAFICIOS

        //En la parte de asignar las respuestas a cada anomalía, primero se llama a la funcion crearAnomalias
        //luego se procede a la asignacion, primero se debe crear el objeto anomalía.

        //Crea las anomalías:
        System.out.println(control.crearAnomalias());
        System.out.println(control.mostrarAnomalias());

        //Agrega nuevas acciones a las anomalias.
        System.out.println(control.crearNuevaAccion("Comer sopita de pollo", congestionVehicular));
        System.out.println(control.mostrarAnomalias());

        System.out.println(control.eliminarAccion(0, congestionVehicular));
        System.out.println(control.mostrarAnomalias());

        System.out.println(control.modificarAccion(1, "Llamar a Teletica", desarrolloObraPublica));
        System.out.println(control.mostrarAnomalias());

        System.out.println(control.crearListaReglas());
        System.out.println(control.activarReglas(0, 20));
        System.out.println(control.mostrarListaReglas());
        System.out.println(control.activarReglas(1, 20));
        System.out.println(control.mostrarListaReglas());

        System.out.println(control.desactivarReglas(0));
        System.out.println(control.mostrarListaReglas());


        //----------------------------------------- CIERRE PERFIL ADMINISTRADOR -----------------------------------------



    }
}
