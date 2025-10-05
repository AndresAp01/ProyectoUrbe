/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author linuxman
 */
public class CantTareasUsadas {
    private int cantMedico;
    private int cantDormitorio;
    private int cantAlimentos;
    private int cantPlantas;
    private int cantPaseo;
    private int cantReunion;
    
    public CantTareasUsadas() {}
    
    // Getters
    public int getCantMedico() {
        return cantMedico;
    }

    public int getCantDormitorio() {
        return cantDormitorio;
    }

    public int getCantAlimentos() {
        return cantAlimentos;
    }

    public int getCantPlantas() {
        return cantPlantas;
    }

    public int getCantPaseo() {
        return cantPaseo;
    }

    public int getCantReunion() {
        return cantReunion;
    }

    // Setters
    public void setCantMedico(int cantMedico) {
        this.cantMedico = cantMedico;
    }

    public void setCantDormitorio(int cantDormitorio) {
        this.cantDormitorio = cantDormitorio;
    }

    public void setCantAlimentos(int cantAlimentos) {
        this.cantAlimentos = cantAlimentos;
    }

    public void setCantPlantas(int cantPlantas) {
        this.cantPlantas = cantPlantas;
    }

    public void setCantPaseo(int cantPaseo) {
        this.cantPaseo = cantPaseo;
    }

    public void setCantReunion(int cantReunion) {
        this.cantReunion = cantReunion;
    }

    // Método toString
    @Override
    public String toString() {
        return "CantTareasUsadas{" +
                "cantMedico=" + cantMedico +
                ", cantDormitorio=" + cantDormitorio +
                ", cantAlimentos=" + cantAlimentos +
                ", cantPlantas=" + cantPlantas +
                ", cantPaseo=" + cantPaseo +
                ", cantReunion=" + cantReunion +
                '}';
    }
    
}