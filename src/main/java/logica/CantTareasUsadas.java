package logica;

/**
 * Clase que almacena la cantidad de tareas utilizadas por categoría.
 * Cada instancia mantiene el conteo de tareas médicas, de dormitorio,
 * de alimentos, plantas, paseo y reuniones.
 * Sirve para controlar y limitar las tareas que los ciudadanos pueden realizar.
 * 
 * @author linuxman
 */
public class CantTareasUsadas {

    /** Cantidad de tareas médicas usadas. */
    private int cantMedico;

    /** Cantidad de tareas de dormitorio usadas. */
    private int cantDormitorio;

    /** Cantidad de tareas de alimentos usadas. */
    private int cantAlimentos;

    /** Cantidad de tareas de plantas usadas. */
    private int cantPlantas;

    /** Cantidad de tareas de paseo usadas. */
    private int cantPaseo;

    /** Cantidad de tareas de reuniones usadas. */
    private int cantReunion;

    /**
     * Constructor por defecto que inicializa todas las cantidades en 0.
     */
    public CantTareasUsadas() {}

    // Getters

    /** @return la cantidad de tareas médicas usadas. */
    public int getCantMedico() { return cantMedico; }

    /** @return la cantidad de tareas de dormitorio usadas. */
    public int getCantDormitorio() { return cantDormitorio; }

    /** @return la cantidad de tareas de alimentos usadas. */
    public int getCantAlimentos() { return cantAlimentos; }

    /** @return la cantidad de tareas de plantas usadas. */
    public int getCantPlantas() { return cantPlantas; }

    /** @return la cantidad de tareas de paseo usadas. */
    public int getCantPaseo() { return cantPaseo; }

    /** @return la cantidad de tareas de reuniones usadas. */
    public int getCantReunion() { return cantReunion; }

    // Setters
    
    /**
     * Establece la cantidad de tareas médicas usadas.
     * @param cantMedico cantidad de tareas médicas.
     */
    public void setCantMedico(int cantMedico) { this.cantMedico = cantMedico; }

    /**
     * Establece la cantidad de tareas de dormitorio usadas.
     * @param cantDormitorio cantidad de tareas de dormitorio.
     */
    public void setCantDormitorio(int cantDormitorio) { this.cantDormitorio = cantDormitorio; }

    /**
     * Establece la cantidad de tareas de alimentos usadas.
     * @param cantAlimentos cantidad de tareas de alimentos.
     */
    public void setCantAlimentos(int cantAlimentos) { this.cantAlimentos = cantAlimentos; }

    /**
     * Establece la cantidad de tareas de plantas usadas.
     * @param cantPlantas cantidad de tareas de plantas.
     */
    public void setCantPlantas(int cantPlantas) { this.cantPlantas = cantPlantas; }

    /**
     * Establece la cantidad de tareas de paseo usadas.
     * @param cantPaseo cantidad de tareas de paseo.
     */
    public void setCantPaseo(int cantPaseo) { this.cantPaseo = cantPaseo; }

    /**
     * Establece la cantidad de tareas de reuniones usadas.
     * @param cantReunion cantidad de tareas de reuniones.
     */
    public void setCantReunion(int cantReunion) { this.cantReunion = cantReunion; }

    // Métodos adicionales

    /**
     * Representación en String de todas las cantidades de tareas usadas.
     * @return String con la información completa de la instancia.
     */
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
