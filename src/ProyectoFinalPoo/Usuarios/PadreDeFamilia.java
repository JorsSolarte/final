package ProyectoFinalPoo.Usuarios;

import java.util.ArrayList;


public class PadreDeFamilia extends Persona {
    
    private ArrayList<Estudiante> hijo; // Relación uno a muchos con Estudiante
    
    //Constructor Parametrizado

    public PadreDeFamilia(String nombre, String apellido, String documento, String nombreUsuario, String contrasena) {
        super(nombre, apellido, documento, nombreUsuario, contrasena);
        this.hijo = new ArrayList<>();
    }
    
    //Getters
    public ArrayList<Estudiante> getHijo() {
        return hijo;
    }

    //Setters
    public void setHijo(ArrayList<Estudiante> hijo) {
        this.hijo = hijo;
    }
    
    public void agregarHijo(Estudiante estudiante) {
    if (!hijo.contains(estudiante)) {
        hijo.add(estudiante);
        estudiante.setPadre(this);
    }
    }
    
  
    //Método para ver las notas del hijo
    public void verNotasHijo() {
        if (hijo != null && !hijo.isEmpty()) {
            System.out.println("Notas del hijo o los hijos:");
            for (Estudiante estudiante : hijo) {
                System.out.println("Notas de " + estudiante.getNombre() + ":");
                estudiante.verNotas();
            }
        } else {
            System.out.println("No tiene hijos registrados o los hijos no tienen notas.");
        }
    }
    
    @Override
    public void menu() {
        if (!hijo.isEmpty()) {
            System.out.println("Hijos:");
            for (Estudiante estudiante : hijo) {
                System.out.println("- " + estudiante.getNombre());
                estudiante.verNotas();
            }
        } else {
            System.out.println("No tiene hijos registrados.");
        }
    }
}
