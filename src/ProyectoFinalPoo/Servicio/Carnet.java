package ProyectoFinalPoo.Servicio;

import java.io.Serializable;

public class Carnet implements Serializable {
    
    private String numCarnet;
    private String tipo;
    
    //Constructor Parametrizado
    public Carnet (String numCarnet, String tipo){
        this.numCarnet = numCarnet;
        this.tipo = tipo;
    }
    
    //Getters
    public String getNumCarnet() {
        return numCarnet;
    }

    public String getTipo() {
        return tipo;
    }
    
    //Setters
    public void setNumCarnet(String numCarnet) {
        this.numCarnet = numCarnet;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
