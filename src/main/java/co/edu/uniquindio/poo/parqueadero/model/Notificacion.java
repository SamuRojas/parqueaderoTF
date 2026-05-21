package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDateTime;

public class Notificacion implements IReporte {
    //Atributos
    private String destinatario, mensaje;
    private LocalDateTime fechaInicio;

    /**
     *contructor de la clase Notificacion
     * @param destinatario de Notificacion
     * @param mensaje de Notificacion

     */

    public Notificacion (String destinatario, String mensaje, LocalDateTime fechaInicio){
        this.destinatario = destinatario;
        this. mensaje = mensaje;
    }




}
