package ProyectoFinalPoo.Usuarios;

import ProyectoFinalPoo.Readers;
import ProyectoFinalPoo.Servicio.Materia;
import ProyectoFinalPoo.Servicio.SistemaGestionEducativa;

import java.util.Scanner;

public class Docente extends Persona implements ICalificar {

    private double calificacion;
    private Materia materia; //Relacion de asociacion con Materia
    SistemaGestionEducativa sistemaGestionEducativa;

    //Constructor Parametrizado
    public Docente(String nombre, String apellido, String documento, String nombreUsuario, String contrasena, SistemaGestionEducativa sistemaGestionEducativa) {
        super(nombre, apellido, documento, nombreUsuario, contrasena);
        this.sistemaGestionEducativa = sistemaGestionEducativa;
    }
    //Getters
    public double getCalificacion() {
        return calificacion;
    }

    public Materia getMateria() {
        return materia;
    }

    //Setters
    public SistemaGestionEducativa getSistemaGestionEducativa() {
        return sistemaGestionEducativa;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setSistemaGestionEducativa(SistemaGestionEducativa sistemaGestionEducativa) {
        this.sistemaGestionEducativa = sistemaGestionEducativa;
    }
    
    public void setCalificacion(double nota) {
        this.calificacion = (this.calificacion + nota) / 2;
    }

    public void asignarMateria(Materia materia) {
        this.materia = materia;
        materia.asignarDocente(this); // Asignar el docente a la materia también
    }

    //Procedimiento para calificar a un estudiante en una materia espesifica
    public void calificarEstudiante(Estudiante estudiante, String materia, double calificacion) {
        estudiante.agregarNota(materia, calificacion);
    }

    @Override
    public void calificar(Persona persona, String[] arg) {
        try {
            Estudiante estudiante = (Estudiante) persona;
            estudiante.agregarNota(arg[0], Integer.parseInt(arg[1]));
        } catch (Exception ex) {
            System.out.println("Imposible hacer el cast, el objeto no es un estudiante, intentalo de nuevo");
        }

    }
    
    @Override
    public void menu() {
        while (true) {
            //Meú Docente
            System.out.println("\nMenú Docente");
            System.out.println("1. Calificar estudiante");
            System.out.println("2. Volver al menú principal");

  
            switch (Readers.readInt("Menu")) {
                case 1 -> {
                    // Calificar estudiante
                    String codigoID = Readers.readString("Ingrese el numero de documento del estudiante a calificar: ");
                    Estudiante estudiante = (Estudiante) sistemaGestionEducativa.buscarEstudiantePorCodigo(codigoID);
                    if (estudiante != null) {
                        try{
                            System.out.print("Ingrese la materia a calificar: ");
                            String materia = Readers.readString("Ingrese la materia a calificar: ");
                            System.out.print("Ingrese la calificación: ");
                            double calificacion = new Scanner(System.in).nextDouble();

                            calificarEstudiante(estudiante, materia, calificacion);
                        }catch(Exception ex){
                            System.out.println("¡Escribe solo numeros enteros "  + ex.getMessage());
                        }
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                }
                case 2 -> {
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

}



