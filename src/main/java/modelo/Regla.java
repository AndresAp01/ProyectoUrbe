package modelo;

public class Regla {
    String nombre;
    int valorMinimoBateria;
    boolean activa;

    public Regla(int valorMinimoBateria){
        this.nombre = "Regla 1";
        this.valorMinimoBateria = valorMinimoBateria;
        this.activa = false;
    }
    public Regla(int op, int valorMinimoBateria){
        this.nombre = "Regla 2";
        this.valorMinimoBateria = valorMinimoBateria;
        this.activa = false;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setValorMinimoBateria(int valorMinimoBateria){
        this.valorMinimoBateria = valorMinimoBateria;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getValorMinimoBateria(){
        return this.valorMinimoBateria;
    }
    public boolean getActiva(){
        return this.activa;
    }
    public void setActiva(boolean activa){
        this.activa = activa;
    }

    public String toString(){
        return "Nombre: "+nombre+
                ", Valor minimo de bateria: "+valorMinimoBateria+
                ", activa: "+activa+
                "}\n";
    }
}
