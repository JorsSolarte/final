package ProyectoFinalPoo.Usuarios;

import ProyectoFinalPoo.Readers;
import ProyectoFinalPoo.Servicio.Materia;
import ProyectoFinalPoo.Servicio.SistemaGestionEducativa;

import java.util.Map;
import java.util.Scanner;

import static ProyectoFinalPoo.Servicio.SistemaGestionEducativa.crearEstudiante;
import static ProyectoFinalPoo.Servicio.SistemaGestionEducativa.crearMateria;
import static ProyectoFinalPoo.Servicio.SistemaGestionEducativa.crearPadreFamilia;

public class Administrativo extends Persona {
    SistemaGestionEducativa sistemaGestionEducativa;

    //Constructor Parametrizado
    public Administrativo(String nombre, String apellido, String documento, String nombreUsuario, String contrasena, SistemaGestionEducativa sistemaGestionEducativa) {
        super(nombre, apellido, documento, nombreUsuario, contrasena);
        this.sistemaGestionEducativa = sistemaGestionEducativa;
    }

    //procedimiento para registrar un nuevo estudiante en el sistema
    public void registrarEstudiante(Estudiante estudiante, Map<String, Estudiante> estudiantes) {
        estudiantes.put(estudiante.getNombreUsuario(), estudiante);
        System.out.println("Estudiante registrado: " + estudiante.getNombre());
    }

    //Procedimiento para actalizar  los datos del estudiante en el sistema
    public static void actualizarDatosEstudiante(Estudiante estudiante) {
        String nuevoNombre = Readers.readString("Ingrese el nuevo nombre del estudiante: ");
        estudiante.setNombre(nuevoNombre);
        String nuevoApellido = Readers.readString("Ingrese el nuevo apellido del estudiante: ");
        estudiante.setApellido(nuevoApellido);
        System.out.println("El nuevo nombre " + estudiante.getNombre() + " y el nuevo apellido es " + estudiante.getApellido());
    }

    // no esta en sistema de gestion educativa, devido a un error de ultimo momento en el programa
    public Docente crearDocente() {
        System.out.println("Ingrese los datos del profesor:");
        String nombre = Readers.readString("Nombre: ");
        String apellido = Readers.readString("Apellido: ");
        String documento = Readers.readString("Documento: ");
        String nombreUsuario = Readers.readString("Nombre de usuario: ");
        String contrasena = Readers.readString("Contraseña: ");
        

        return new Docente(nombre, apellido, documento, nombreUsuario, contrasena, sistemaGestionEducativa);     
    }
    
    private void associationSubToStd() {
        String StudentID, SubjectID;
        System.out.println("Dime el numero de identificacion del estudiante: ");
        StudentID = new Scanner(System.in).next();
        Estudiante estudiante = sistemaGestionEducativa.buscarEstudiantePorCodigo(StudentID);
        System.out.println("Dime el numero de identificacion de la materia: ");
        String MateriaCodigo = new Scanner(System.in).next();
        Materia materia = sistemaGestionEducativa.buscarMateriaPorCodigo(MateriaCodigo);
        if (estudiante != null && materia != null) {
            if (!estudiante.getMaterias().contains(materia)) {
                estudiante.addMateria(materia); //Guardar materia
            }
            return;
        }
        System.out.println("Registra un estudiante o crea la materia, para empezar");
    }


    @Override
    public void menu() {
        while (true) {
switch (Readers.readInt("\nMenú Administrativo\n1. Registrar estudiante\n2. Actualizar datos de estudiante\n3. Subir materia\n4. Registrar Profesor\n5. Asignar materia al estudiante\n6. Volver al menú principal")) {
            case 1 -> {
                // Registrar estudiante
                Estudiante nuevoEstudiante = crearEstudiante();
                
                if (!Readers.readString("¿Tienes un papá ya creado? Escribe 'No' para registrar, Si para continuar").toLowerCase().equals("no")) {
                    // El usuario tiene un padre de familia ya creado
                    PadreDeFamilia padre = sistemaGestionEducativa.buscarPadreDeFamiliaPorCodigo(Readers.readString("Dime el número de identificación del usuario Padre de Familia"));
                    
                    if (padre != null) {
                        nuevoEstudiante.setPadre(padre);
                        sistemaGestionEducativa.agregarUsuario(nuevoEstudiante);
                        System.out.println("Padre de familia Agregado Correctamente");
                    } else {
                        System.out.println("No se encontró un padre con ese número de identificación.");
                    }
                } else {
                    // El usuario no tiene un padre de familia creado
                    PadreDeFamilia padreDeFamilia = crearPadreFamilia();
                    sistemaGestionEducativa.agregarUsuario(padreDeFamilia);
                    nuevoEstudiante.setPadre(padreDeFamilia);
                    sistemaGestionEducativa.agregarUsuario(nuevoEstudiante);
                }
            }      
                case 2 -> {
                    // Actualizar datos de estudiante
                    String numDoc = Readers.readString("Ingrese el numero de documento del estudiante a actualizar: ");
                    Estudiante actualizarEstudiante = (Estudiante) sistemaGestionEducativa.buscarEstudiantePorCodigo(numDoc);
                    if (actualizarEstudiante != null) {
                        System.out.println("Los antiguos datos eran " + actualizarEstudiante.getNombre() + " apellido " + actualizarEstudiante.getApellido());
                        actualizarDatosEstudiante(actualizarEstudiante);
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                }
                case 3 -> {
                    // Subir
                    Materia nuevaMateria = crearMateria();
                    sistemaGestionEducativa.agregarMateria(nuevaMateria);
                }
                case 4 -> {
                    Docente docente = crearDocente();
                    sistemaGestionEducativa.agregarUsuario(docente);
                }
                case 5 -> associationSubToStd();
                case 6 -> {
                    System.out.println("Se ha elegido salir");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}

