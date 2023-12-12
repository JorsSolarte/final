package ProyectoFinalPoo.Servicio;

import ProyectoFinalPoo.Readers;
import ProyectoFinalPoo.Usuarios.Docente;
import ProyectoFinalPoo.Usuarios.Estudiante;
import ProyectoFinalPoo.Usuarios.PadreDeFamilia;
import ProyectoFinalPoo.Usuarios.Persona;

import java.io.Serializable;
import java.util.*;


public class SistemaGestionEducativa implements Serializable {
    
    private final List<Persona> usuarios;
    private final ArrayList<Materia> materias;
    SistemaGestionEducativa sistemaGestionEducativa;
    //Constructor Parametrizado
    public SistemaGestionEducativa() {
        usuarios = new ArrayList<>();
        materias = new ArrayList<>();
    }
    
    //Getters
    public List<Persona> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Materia> getMaterias() {
        return  materias;
    }
    
    
    //Procedimiento para el inicio de sesión de usuarios
    public Persona login(String nombreUsuario, String contrasena) {
        for (Persona usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null; // Datos incorrectos
    }
    
    //Materia
    
    //Procedimiento para crear  una materia con los datos ingresados por el admininistrativo
    public static Materia crearMateria() {
        System.out.println("Ingrese los datos de la materia:");
        String nombre = Readers.readString("Nombre: ");
        String codigo = Readers.readString("Codigo: ");
        return new Materia(nombre, codigo);
    }
    
    public void agregarMateria(Materia materia){ materias.add(materia); }
    //Procedimiento para agregar un usuario al sistema
    public void agregarUsuario(Persona usuario) {
        usuarios.add(usuario);
    }
    
    //Método para buscar la materia por un código
    public Materia buscarMateriaPorCodigo(String Codigo){
        Optional<Materia> theSubject = materias.stream().filter(std -> std.getCodigo().equals(Codigo)).findFirst();
        return theSubject.orElse(null);
    }
    
    //Estudiante
    
    //Procedimiento para crear un estudiante con los datos ingresados por el administrativo
    public static Estudiante crearEstudiante() {
        System.out.println("Ingrese los datos del estudiante:");
        String nombre = Readers.readString("Nombre: ");
        String apellido = Readers.readString("Apellido: ");
        String documento = Readers.readString("Documento: ");
        String nombreUsuario = Readers.readString("Nombre de usuario: ");
        String contrasena = Readers.readString("Contraseña: ");

        return new Estudiante(nombre, apellido, documento, nombreUsuario, contrasena);
    }
    
        //Método para buscar estudiante por un código
    public Estudiante buscarEstudiantePorCodigo(String Documento){
        Optional<Persona> theStudent = usuarios.stream().filter(std -> std.getDocumento().equals(Documento)).findFirst();
        Estudiante rtnEstudiante;
        try{
            rtnEstudiante = (Estudiante) theStudent.orElse(null);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            rtnEstudiante = null;
        }
        return rtnEstudiante;
    }

    //Actualiza los datos de un estudiante con la información ingresada por el admin
    public static void actualizarDatosEstudiante(Estudiante estudiante, Scanner scanner) {
        System.out.println("Actualice los datos del estudiante (deje en blanco si no desea actualizar):");

        System.out.print("Nombre: ");
        //[.trim()] se utiliza para eliminar los espacios al principio y final de la cadena
        String nombre = scanner.nextLine().trim();
        if (!nombre.isEmpty()) {
            estudiante.setNombre(nombre);
        }
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine().trim();
        if (!apellido.isEmpty()) 
            estudiante.setApellido(apellido);
        
        System.out.print("Documento: ");
        String documento = scanner.nextLine().trim();
        if (!documento.isEmpty()) 
            estudiante.setDocumento(documento);
        
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine().trim();
        if (!nombreUsuario.isEmpty()) {
            estudiante.setNombreUsuario(nombreUsuario);
        }
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine().trim();
        if (!contrasena.isEmpty()) {
            estudiante.setContrasena(contrasena);
        }
    }

    //Padre de familia
 
    public static PadreDeFamilia crearPadreFamilia() {
    System.out.println("Ingrese los datos del Padre de familia:");

    String nombre = Readers.readString("Nombre: ");
    String apellido = Readers.readString("Apellido: ");
    String documento = Readers.readString("Documento: ");
    String nombreUsuario = Readers.readString("Nombre de usuario: ");
    String contrasena = Readers.readString("Contraseña: ");

    return new PadreDeFamilia(nombre, apellido, documento, nombreUsuario, contrasena);
    }
    
    public PadreDeFamilia buscarPadreDeFamiliaPorCodigo(String Documento){
        Optional<Persona> theStudent = usuarios.stream().filter(std -> std.getDocumento().equals(Documento)).findFirst();
        PadreDeFamilia rtnPadre;
        try{
            rtnPadre = (PadreDeFamilia) theStudent.orElse(null);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            rtnPadre = null;
        }
        return rtnPadre;
    }

}

