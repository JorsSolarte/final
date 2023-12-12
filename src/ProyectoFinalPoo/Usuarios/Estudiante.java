package ProyectoFinalPoo.Usuarios;

import ProyectoFinalPoo.Servicio.Carnet;
import ProyectoFinalPoo.Servicio.Materia;
import java.util.*;

public class Estudiante extends Persona implements ICalificar{
    
    private Carnet carnet; //Relacion de composicion
    private PadreDeFamilia padre; //Relación mcuhos a uno con PadreDeFamilia
    private Map<String, List<Double>> notasPorMateria; //Relación muchos a muchos con Materia
    private List<Materia> materias; //Relacion de agregacion

    public void addMateria(Materia materia){
        materias.add(materia);
    }

    //Constructor Parametrizado 
    public Estudiante(String nombre, String apellido, String documento, String nombreUsuario, String contrasena) {
        super(nombre, apellido, documento, nombreUsuario, contrasena);
        this.carnet = new Carnet(UUID.randomUUID().toString(), "Estudiante"); //Relacion de Composicion
        this.padre = padre;
        this.notasPorMateria = new HashMap<>();
        this.materias = new ArrayList<>();
    }



    //Getters
    public PadreDeFamilia getPadre() {
        return padre;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public List<Materia> getMaterias() {
        return materias;
    }
    
    public Map<String, List<Double>> getNotasPorMateria() {
        return notasPorMateria;
    }
    
    //Setters
    public void setPadre(PadreDeFamilia padre) {
        this.padre = padre;
        padre.agregarHijo(this);
    }
 
    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    public void setNotasPorMateria(Map<String, List<Double>> notasPorMateria) {
        this.notasPorMateria = notasPorMateria;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }  
    
    //Procedimiento para ver las notas y calcular el promedio
    //[.keySet()] se utiliza para acceder a los elementos del Map (Materias)
    public void verNotas() {
        for (String materia : notasPorMateria.keySet()) { 
            List<Double> notas = notasPorMateria.get(materia); //Obtener la lista de notas
            double promedio = calcularPromedio(notas);
            System.out.println("Notas en " + materia + ": " + notas);
            System.out.println("Promedio en " + materia + ": " + promedio);
        }
    }

    // Método para calcular el promedio de una lista de notas
    private double calcularPromedio(List<Double> notas) {
    // Verificar si la lista está vacía con .isEmpty (recorre la lsita)
    if (notas.isEmpty()) {
        return 0.0;
    }
    // Inicializar la variable de sum
    double sum = 0;

    // Recorrer cada nota en la lista y sumarla
    for (Double nota : notas) {
        sum += nota;
    }

    // Calcular el promedio dividiendo la suma por la cantidad de notas
    double promedio = sum / notas.size(); //[.size()] se tiliza para obtener el numero de elemetos en la lista "notas"

    // Devolver el resultado del promedio
    return promedio;
}

    //Procedimiento para alamacenar las notas que sean agregadas a los estduiantes
    public void agregarNota(String materia, double nota) {
           notasPorMateria.computeIfAbsent(materia, k   -> new ArrayList<>()).add(nota);
    }
    
    @Override
    public void calificar(Persona persona, String[] arg) {
        try{
            Docente docente = (Docente) persona;
            docente.setCalificacion(Double.parseDouble(arg[0]));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void menu() {
        System.out.println("\nMenú Estudiante");
        verNotas();
    }

}


   