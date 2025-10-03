package logica;
//MANTENER ENTRADAS DE UN SOLO VALOR EN LOS ADMINISTRADORES ESPECIFICADO LO MEJOR POSIBLE


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




        //----------------------------------------- CIERRE PERFIL ADMINISTRADOR -----------------------------------------



    }
}
