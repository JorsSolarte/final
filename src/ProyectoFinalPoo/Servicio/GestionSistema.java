package ProyectoFinalPoo.Servicio;

import java.io.*;

public final class GestionSistema {
    
    public void guardarEnArchivo(SistemaGestionEducativa sistema, String file){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(sistema);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Se guardaron correctamente los datos");
        }catch (Exception ex){
            System.out.println("Ocurrio un error" + ex.getMessage());
        }
    }
    
    public SistemaGestionEducativa cargarDesdeArchivo(String nombreArchivo){
        
        SistemaGestionEducativa sistema = new SistemaGestionEducativa();
        try {
            FileInputStream fileInputStream = new FileInputStream(nombreArchivo);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            sistema = (SistemaGestionEducativa) in.readObject();
            in.close();
            fileInputStream.close();
            System.out.println("Se cargaron correctamente los datos");
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sistema;
    }
}
