package ProyectoFinalPoo;

import ProyectoFinalPoo.Servicio.GestionSistema;
import ProyectoFinalPoo.Servicio.Materia;
import ProyectoFinalPoo.Servicio.SistemaGestionEducativa;
import ProyectoFinalPoo.Usuarios.*;

import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        
        // Crear un sistema de gestión educativa
        GestionSistema gestionSistema = new GestionSistema();
        SistemaGestionEducativa sistema = gestionSistema.cargarDesdeArchivo("Gestion.ser");

        System.out.println("______________________________________________________________\n");
        System.out.println("     SISTEMA DE GESTION EDUCATIVA UNIVERSIDAD DEL CAUCA");
        System.out.println("______________________________________________________________");
        System.out.println("Bienvenido al sistema de gestión educativa.\n");
        System.out.println("**Informacion importante** ");
          
        Materia materiaPrevia = new Materia("Calculo 1", "MAT101");
        Materia materiaActual = new Materia("Algrebra Lineal", "FIS101");
        materiaActual.setMateriaPrevia(materiaPrevia);
        System.out.println(materiaActual.getMateriaPreviaToString());
        
        Persona admin = new Administrativo("Administrativo","","","admin","admin",sistema);
        sistema.agregarUsuario(admin);


//Profe debemos quitarle los (//) para correr el programa por primera vez, salimos del programa y se guardan.
//Puede volver a comentarlos 
        
//        Persona profesor = new Docente("","","","profesor", "profesor", sistema);
//        sistema.agregarUsuario(profesor);
//        
//        PadreDeFamilia padre1 = new PadreDeFamilia("", "", "o", "padre", "padre");
//        Estudiante estudiante = new Estudiante("","","","estudiante","estudiante");
//        sistema.agregarUsuario(padre1); 
//        sistema.agregarUsuario(estudiante);
//        estudiante.setPadre(padre1);
//        estudiante.addMateria(materiaActual);
//        estudiante.agregarNota("Calculo", 38.7);
//        estudiante.agregarNota("Calculo", 45);
       

        //Menú principal
        try (Scanner scanner = new Scanner(System.in)) {
            do{

                System.out.println("________________________________________________________________");
                System.out.println("\nINICIO DE SESION\n");
                System.out.print("Ingrese su nombre de usuario: ");
                String nombreUsuario = scanner.next();
                System.out.print("Ingrese su contraseña: ");
                String contrasena = scanner.next();

                Persona usuario = sistema.login(nombreUsuario, contrasena);

                if (usuario == null) {
                    System.out.println("Datos incorrectos. Inténtelo nuevamente.");
                } else {
                    System.out.println("Inicio de sesión exitoso como " + usuario.getNombre());
                    usuario.menu();
                    System.out.println("Cerrando sesión...");
                }
                //Pregunta al usuario si desea salir del programa
                System.out.print("");

            }while (!Readers.readString("¿Desea salir del programa? (S/N): ").toLowerCase().equals("s"));
            System.out.println("Saliendo del programa...");

        }catch (Exception ex){
            System.out.println("Ocurrio un error" + ex.getMessage());
        }
        gestionSistema.guardarEnArchivo(sistema, "Gestion.ser");
    }
}

    




