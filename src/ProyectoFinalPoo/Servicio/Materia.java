package ProyectoFinalPoo.Servicio;

import ProyectoFinalPoo.Usuarios.Docente;
import ProyectoFinalPoo.Usuarios.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Materia implements Serializable {
    private Materia materiaPrevia = null; // Autorelación
    private String nombre;
    private String codigo;
    private List<Estudiante> estudiantesInscritos; // Relación muchos a muchos con Estudiante
    private Docente docente; //Relacion de asociacion con Docente (1 a 1)
    
    //Constructor Parametrizado
    public Materia(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.estudiantesInscritos = new ArrayList<>();
    }
    
    // Getter
    public List<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
    

    public String getMateriaPreviaToString() {
        return "La materia " + this.nombre + (materiaPrevia != null ? (" tiene como materia previa: " + materiaPrevia.nombre) : " no tiene materia previa");
    }

    public Docente getDocente() {
        return docente;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEstudiantesInscritos(List<Estudiante> estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }  
    
    public void setMateriaPrevia(Materia materiaPrevia) {
    this.materiaPrevia = materiaPrevia;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }


    //Procedimiento para inscribir un estudiante a la materia
    public void inscribirEstudiante(Estudiante estudiante) {
        estudiantesInscritos.add(estudiante);
    }
    

    public void asignarDocente(Docente docente) {
    this.docente = docente;
    }

}
    

