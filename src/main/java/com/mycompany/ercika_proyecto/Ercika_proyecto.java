/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ercika_proyecto;
import logica.*;
import modelo.*;
import java.util.Map;
import java.util.ArrayList;

public class Ercika_proyecto {
    public static void main(String[] args) {
        PerfilAdmin admin = new PerfilAdmin();
        //*******************************************FUNCIONES ADMIN****************************************************************
        //seccion de edificios y cargaficios
        //e: INT
        admin.crearEdificios(0); //recibe un int, si es igual a 0 es aleatorio si no crea los edificios dichos (limite de 3 a 10)
        
        //e:int
        admin.crearCargaficios(0); //lo mismo que lo de arriba pero su limite es de 5 a 8 
        
        //String
        //S: String
        System.out.println(admin.mostrarCargaficios()); //string no tiene parametros
        
        //String
        //S: String
        System.out.println(admin.mostrarEdificios()); //lo de arriba x2
        
        //System.out.println(); lo tengo para copiar y pegar jsjs
        
        //SECCION DE ANOMALIAS
        //E: int[]
        admin.crearAnomalias(new int[]{1,2,3}); //crea anomalias, recibe un arreglo de integers, y crea de tipo 1, tipo2, y asi, si se vuelve a hacer 
        //crea las mismas anomalias
        
        
        System.out.println(admin.mostrarAnomalias()); //se muestran las creadas, lo mismo que los demas
        
        //E: string {accion en string} y TipoAnomalia tipoanomalia
        System.out.println(admin.crearNuevaAccion("Hola", TipoAnomalia.desarrolloObraPublica)); //se agrega una cualquiera, recibe un String y al tipo de anomalia que se desea agregar
        //da false si no existe la anomalia que se quiere agregar la accion en la listaAnomalia o si ya existe dicha accion
        
        
        System.out.println(admin.mostrarAnomalias());
        System.out.println(admin.crearNuevaAccion("Hola", TipoAnomalia.desarrolloObraPublica)); //aca da false para probar, pues no agrega dos del mismo en la misma anomalia
        
        //E: indice de lista y tipo de anomalia
        System.out.println(admin.eliminarAccion(2, TipoAnomalia.desarrolloObraPublica)); //elimina mediante el index, que inicia en 0, recibe el index y el tipo de anomalia
        //retorna false si no se encontro la anomalia o el integer es mayor a la lista
        
        //E: indice, String (problema), TipoAnonmalia tipoAnomalia
        //de paso se modifica una accion (se le agrega mas cosas al Llamar 911)
        System.out.println(admin.modificarAccion(0, "Llamar al numero de emergencia 911", TipoAnomalia.derramesSustanciasEnCarretera)); 
        //la funcion de arriba recibe el index, el String de modificacion y el tipo de anomalia, funciona igual a eliminar
        
        //no pide nada
        //RETORNA UN ARRAYLIST CON TODOS LOS CARGAFCIIOS
        admin.modificarEstadoCargaficios();
        
        System.out.println(admin.mostrarAnomalias());//muestra para ver si en efecto se hizo el cambio
        
        //SECCION REGLAS
        admin.crearListaReglas(); //crea dos reglas, sin parametros
        System.out.println(admin.mostrarListaReglas()); //la de index 0 son los drones la de index 1 los robots
        //OPERADOR PRUEBAAAAAAAAAAAAAAAAAAAAAAAAAAAS
        //SE CREARAN ROBOTS Y DRONES Y VER SI HAY EFECTO DE LAS REGLAS SOBRE ELLOS
        
        admin.crearDronesMedianteOperador(); //sin parametros, crea el doblo -1 de drones dependiendo de la cantidad de edificios
        admin.mostrarDronesMedianteOperador();// Imprime en consola el monton
        
        
        //E: int nCiudadanos
        System.out.println(admin.crearCiudadanosMedianteOperador(5)); //genera ciudadnos adecuadamente
        admin.mostrarCiudadanosMedianteOperador(); //Imprime en consola 
        
        //E: int nrobots
        System.out.println(admin.crearRobotsMedianteOperador(5)); //genera robots adecuadamente
        admin.mostrarRobotsMedianteOperador();//imprimir en consola
        
        //como se ve el valor minimo es de 5, veamos si con la regla cambia
        
        //Esta funcion activa las reglas
        //la op = 0 es la que cambia el valor minimo de drones y de op=1 es la de robots
        //E: int op y int ValorMinimo
        admin.activarReglas(1, 25);// 
        
        
        System.out.println(admin.crearRobotsMedianteOperador(5)); //genera robots adecuadamente
        admin.mostrarRobotsMedianteOperador();
        
        //la logica dentro de esto como fun fact, es que si esta activa la regla, el valorMinimo interno no cambia, pero cuando "debe ir a cargar" si lo hace
        //esto es para hacer mas eficiente las cosas en caso de robot y su hacer tareas
        
        admin.simulateMedianteOperador(); //no se hizo nada de los robots, debido a que no se asigno ninguno, asignemos unos cuantos
        
        
        //E: un id de un Ciudadano y un Procesador (String)
        admin.asignarRobotCiudadanoMedianteOperador("CEH-1", "IAAA-1");
        
        
        
        admin.asignarRobotCiudadanoMedianteOperador("CEH-1", "IAAA-2");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-2", "IAAA-3");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-1", "IAAA-4");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-4", "IAAA-5");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-3", "IAAA-6");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-2", "IAAA-7");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-4", "IAAA-8");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-3", "IAAA-9");
        admin.asignarRobotCiudadanoMedianteOperador("CEH-2", "IAAA-10");
        admin.mostrarRobotsMedianteOperador();
        
        
        
        admin.simulateMedianteOperador();
        admin.mostrarRobotsMedianteOperador();
        
        System.out.println("INICIO DE PRUEBA DE CARGAFCIOS");
        System.out.println("INICIO DE PRUEBA DE CARGAFCIOS");
        System.out.println("INICIO DE PRUEBA DE CARGAFCIOS");
        System.out.println("INICIO DE PRUEBA DE CARGAFCIOS");
        //se activara una regla con tal de ver si no solo afecta y manda a cargar los robots, si no que si se carguen estos
        admin.activarReglas(1, 90);
        admin.simulateMedianteOperador();
        admin.mostrarRobotsMedianteOperador();
        System.out.println(admin.cantTareasUsadas());
        admin.simulateMedianteOperador();
        admin.mostrarRobotsMedianteOperador();
        admin.simulateMedianteOperador();
        admin.mostrarRobotsMedianteOperador();
        System.out.println(admin.cantTareasUsadas());
        admin.simulateMedianteOperador();
        admin.mostrarRobotsMedianteOperador();
        admin.simulateMedianteOperador();
        admin.mostrarRobotsMedianteOperador();
        System.out.println(admin.cantTareasUsadas());
        System.out.println(admin.mostrarCargaficios()); //como se ve cargan los robots, al menos
        
        
        //PARTE DE GENERAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAL
        //S: int
        System.out.println(admin.porcentajeRobotsAlertaMedianteGeneral());
        
        //S: Map< edificio, Datos>
        System.out.println(admin.obtenerDatosPorEdificioMedianteGeneral());
        
        //S: arraylist<integer>
        //Desglose por tarea
        //retorna la cant de veces que una tarea fue usada en este orden
        //[medico, domitiroio, listaALimentos, RegarPlantas, Paseo, Reunion]
        System.out.println(admin.cantTareasUsadas());
        
        //System.out.println();
        //S: integer
        System.out.println(admin.cantCargaficiosDisponiblesMedianteGeneral());
        
        //S: integer
        System.out.println(admin.PorcentajeCargaficiosDisponiblesMedianteGeneral());
        
        //Recibe un id de cargaficio
        //Para el desglose que es
        //Por estación(String): estado(TSTATUS), capacidad(int), ocupación(int %), servicios en la última hora(int).
        //S: AttayList
        System.out.println(admin.desgloseEnergiaCargaficioMedianteGeneral("Cf1"));
        
        //S: map <cargaficio, datos>
        System.out.println(admin.obtenerDatosPorCargaficioMedianteGeneral());
        
        //S: int
        System.out.println(admin.edificiosImpactadosMedianteGeneral());
        
        //S: ArrayLis<ArrayList<Integer>>
        System.out.println(admin.porTipoAnomaliaYEdificioMedianteGeneral());
        
        //S: ArrayList<ArrayList>
        System.out.println(admin.porAccionEjecutadaMedianteGeneral());
        
        //S:ArrayList<ArrayList>
        System.out.println(admin.tablaEdificiosIncidentesMedianteGeneral());
        
        //S: ArrayList<StrinG>
        System.out.println(admin.mayorReincidenciaMedianteGeneral());
        
        //S:ArrayList
        //Va en orden de la lista de edificios
        System.out.println(admin.ocupacionPorEdificioMedianteGeneral());
        
        //S: 3 edificios por ocupacion
        System.out.println(admin.Top3MayorOcupacionMedianteGeneral());
        
        //S: 3 edificios por ciudadanos sin robot
        System.out.println(admin.Top3MayorCiudadanosSinRobotMedianteGeneral());
        
        //S: 3 edificios por robots en alerta
        System.out.println(admin.Top3MayorCantRobotsAlertaMedianteGeneral());
        
        //S: Salida: ArrayList
        System.out.println(admin.InfoSobreAsignacionMedianteGeneral());
        
        //Se hacen dos veces para ver si hay consistencia
        
        
        System.out.println(admin.porcentajeRobotsAlertaMedianteGeneral());
        
        //S: Map< edificio, Datos>
        System.out.println(admin.obtenerDatosPorEdificioMedianteGeneral());
        
        //S: arraylist<integer>
        //Desglose por tarea
        //retorna la cant de veces que una tarea fue usada en este orden
        //[medico, domitiroio, listaALimentos, RegarPlantas, Paseo, Reunion]
        System.out.println(admin.cantTareasUsadas());
        
        //System.out.println();
        //S: integer
        System.out.println(admin.cantCargaficiosDisponiblesMedianteGeneral());
        
        //S: integer
        System.out.println(admin.PorcentajeCargaficiosDisponiblesMedianteGeneral());
        
        //Recibe un id de cargaficio
        //Para el desglose que es
        //Por estación(String): estado(TSTATUS), capacidad(int), ocupación(int %), servicios en la última hora(int).
        //S: AttayList
        System.out.println(admin.desgloseEnergiaCargaficioMedianteGeneral("Cf1"));
        
        //S: map <cargaficio, datos>
        System.out.println(admin.obtenerDatosPorCargaficioMedianteGeneral());
        
        //S: int
        System.out.println(admin.edificiosImpactadosMedianteGeneral());
        
        //S: ArrayLis<ArrayList<Integer>>
        System.out.println(admin.porTipoAnomaliaYEdificioMedianteGeneral());
        
        //S: ArrayList<ArrayList>
        System.out.println(admin.porAccionEjecutadaMedianteGeneral());
        
        //S:ArrayList<ArrayList>
        System.out.println(admin.tablaEdificiosIncidentesMedianteGeneral());
        
        //S: ArrayList<StrinG>
        System.out.println(admin.mayorReincidenciaMedianteGeneral());
        
        //S:ArrayList
        //Va en orden de la lista de edificios
        System.out.println(admin.ocupacionPorEdificioMedianteGeneral());
        
        //S: 3 edificios por ocupacion
        System.out.println(admin.Top3MayorOcupacionMedianteGeneral());
        
        //S: 3 edificios por ciudadanos sin robot
        System.out.println(admin.Top3MayorCiudadanosSinRobotMedianteGeneral());
        
        //S: 3 edificios por robots en alerta
        System.out.println(admin.Top3MayorCantRobotsAlertaMedianteGeneral());
        
        //S: Salida: ArrayList
        System.out.println(admin.InfoSobreAsignacionMedianteGeneral());
        
        
        
        
    }
}

