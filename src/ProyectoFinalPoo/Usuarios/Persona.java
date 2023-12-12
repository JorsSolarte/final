package ProyectoFinalPoo.Usuarios;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    
    //Atributos 
    protected String nombre;
    protected String apellido;
    protected String documento;
    protected String nombreUsuario;
    protected String contrasena;
    
    //Constructor Parametrizado
    public Persona(String nombre, String apellido, String documento, String nombreUsuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }
    
    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public abstract void menu();
}

  

