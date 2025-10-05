package modelo;

/**
 * Clase que representa una regla de control para los drones o robots,
 * basada en un valor mínimo de batería. Una regla puede estar activa o inactiva.
 * 
 * Permite establecer y consultar su nombre, valor mínimo de batería y estado de activación.
 */
public class Regla {
    private String nombre;
    private int valorMinimoBateria;
    private boolean activa;

    /**
     * Constructor de la primera regla.
     * Asigna un nombre predeterminado "Regla 1" y valor mínimo de batería.
     * 
     * @param valorMinimoBateria Valor mínimo de batería requerido para activar la regla.
     */
    public Regla(int valorMinimoBateria){
        this.nombre = "Regla 1";
        this.valorMinimoBateria = valorMinimoBateria;
        this.activa = false;
    }

    /**
     * Constructor de la segunda regla.
     * Asigna un nombre predeterminado "Regla 2" y valor mínimo de batería.
     * 
     * @param op Parámetro adicional para diferenciar el constructor.
     * @param valorMinimoBateria Valor mínimo de batería requerido para activar la regla.
     */
    public Regla(int op, int valorMinimoBateria){
        this.nombre = "Regla 2";
        this.valorMinimoBateria = valorMinimoBateria;
        this.activa = false;
    }

    // Getters y Setters

    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setValorMinimoBateria(int valorMinimoBateria){ this.valorMinimoBateria = valorMinimoBateria; }
    public String getNombre(){ return this.nombre; }
    public int getValorMinimoBateria(){ return this.valorMinimoBateria; }
    public boolean getActiva(){ return this.activa; }
    public void setActiva(boolean activa){ this.activa = activa; }

    // Representación en String

    /**
     * Representación del objeto Regla en formato String.
     * Incluye nombre, valor mínimo de batería y estado de activación.
     * 
     * @return Información completa de la regla en formato String.
     */
    public String toString(){
        return "Nombre: "+nombre+
                ", Valor minimo de bateria: "+valorMinimoBateria+
                ", activa: "+activa+
                "}\n";
    }
}
