/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Dron {
    String procesador;
    int bateria;
    boolean estadoDeAlerta;
    boolean activo;
    boolean enPatrulla;
    Edificio edificioPatrullado;
    int horasVueloRestantes;
    int valorMinimo;

    public Dron(String procesador,int horasVueloRestantes, int bateria, boolean estadoAlerta, boolean activo, boolean enPatrullado){
        this.procesador = procesador;
        this.bateria = bateria;
        this.estadoDeAlerta = estadoAlerta;
        this.activo = activo;
        this.enPatrulla = enPatrullado;
        this.horasVueloRestantes = horasVueloRestantes;
        this.valorMinimo = 25;
    }

    public boolean enviarDronARecargar(){

        return true;
    }

    public String getProcesador() {
        return procesador;
    }
    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public boolean getEstadoDeAlerta() {
        return estadoDeAlerta;
    }

    public void setEstadoDeAlerta(boolean estadoDeAlerta) {
        this.estadoDeAlerta = estadoDeAlerta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getEnPatrulla() {
        return enPatrulla;
    }

    public void setEnPatrulla(boolean enPatrulla) {
        this.enPatrulla = enPatrulla;
    }

    public Edificio getEdificioPatrullado() {
        return edificioPatrullado;
    }

    public void setHorasVueloRestantes(int horasVueloRestantes) {
        this.horasVueloRestantes = horasVueloRestantes;
    }

    public int getHorasVueloRestantes() {
        return horasVueloRestantes;
    }
    @Override
    public String toString() {
        return "{Procesador " + procesador +
                ", bateria " + bateria +
                ", estadoDeAlerta " + estadoDeAlerta +
                ", activo " + activo +
                ", enPatrulla " + enPatrulla +
                ", horasVueloRestantes " + horasVueloRestantes +
                "}";
    }
}

