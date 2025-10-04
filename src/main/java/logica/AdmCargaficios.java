/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.util.ArrayList;
import java.util.Random;

import modelo.Cargaficio;
import modelo.Edificio;
import modelo.TStatus;
/**
 *
 * @author linuxman
 */
public class AdmCargaficios {
    private ArrayList<Cargaficio> listaCargaficios;
    private int Cargaficios_Disponibles;
    
    public AdmCargaficios(){
        this.listaCargaficios = new ArrayList<Cargaficio>();
        this.Cargaficios_Disponibles = 0;
    }

    public ArrayList<Cargaficio> getListaCargaficios() {
        return listaCargaficios;
    }

    public boolean agregarCargaficios(int cantidad){
        String[] descripcionesCargaficios = {"Centro de carga rápida para drones y robots de vigilancia urbana, con sistema de acople automático.", "Estación inteligente de recarga para flotas de robots asistentes en edificios corporativos.", "Punto de carga modular para drones de patrullaje en zonas residenciales y comerciales.", "Centro de recarga autónomo con gestión de energía para robots de limpieza y seguridad.", "Estación de carga con monitoreo remoto, diseñada para vehículos aéreos no tripulados y robots móviles.", "Centro de recarga eficiente en parqueaderos y patios de edificios, compatible con múltiples modelos de drones y robots.", "Punto de carga rápido para drones de inspección de infraestructura y robots de entrega.", "Estación de recarga sostenible con energía renovable, para flotas de patrullaje automatizado."};
        int i=0;
        Random rand =  new Random();
        if (cantidad==0){
            int tope=rand.nextInt(5,8);
            while (i < tope) {
                Cargaficio cargaficio = new Cargaficio("Cf"+(i+1),descripcionesCargaficios[i]);
                listaCargaficios.add(cargaficio);
                i++;
            }
        }
        else {
            while (i < cantidad) {
                Cargaficio cargaficio = new Cargaficio("Cf"+(i+1),descripcionesCargaficios[i]);
                listaCargaficios.add(cargaficio);
                i++;
            }
        }
        return true;
    }

    public ArrayList<Cargaficio> retornarListaCargaficios(){
        return listaCargaficios;
    }

    public Cargaficio consultar(String elId){
        for (int i = 0; i<listaCargaficios.size(); i++){
            Cargaficio Cargaficio = listaCargaficios.get(i);
            if (elId.equals(Cargaficio.getId()))
                return Cargaficio;
        } // for
        return null;   // no encontró el Cargaficio

    }

    public boolean modificar(Cargaficio nuevoCargaficio){
        for (int i = 0; i < listaCargaficios.size(); i++){
            if (listaCargaficios.get(i).equals(nuevoCargaficio)){
                listaCargaficios.set(i, nuevoCargaficio);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String elId){
        for (int i = 0; i < listaCargaficios.size(); i++){
            if (elId.equals(listaCargaficios.get(i).getId())){
                listaCargaficios.remove(i);
                return true;
            }
        }
        return false;
    }
    
    //funciones
    public int CargaficiosDisponibles(){
        for (Cargaficio CargaficioActual: listaCargaficios)
            if (CargaficioActual.getEstado() == TStatus.DISPONIBLE){
                Cargaficios_Disponibles += 1;
            }
        return Cargaficios_Disponibles;
    }
    
    public ArrayList CargaficiosEnMantenimientoConOcupacion(){
        ArrayList cargaficios = new ArrayList<>();
        for (Cargaficio CargaficioActual: listaCargaficios){
            if (CargaficioActual.getEstado() == TStatus.EN_MANTENIMIENTO && (CargaficioActual.getOcupacion() > 0)){
                cargaficios.add(CargaficioActual);
            }
        }
        return cargaficios;
    }
    
    public void cargarDispositivosConectados(){
        for (Cargaficio CargaficioActual: listaCargaficios){
            CargaficioActual.cargar_drones();
            CargaficioActual.cargar_robots();
        }
    }
    
    @Override
    public String toString() {
        return "lista de Cargaficios:\n" + listaCargaficios;
    }
}

    

