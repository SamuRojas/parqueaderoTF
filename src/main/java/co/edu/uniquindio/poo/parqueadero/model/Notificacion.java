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

    public String getDestinatario() {return destinatario;}
    public void setDestinatario(String destinatario) {this.destinatario = destinatario;}
    public String getMensaje() {return mensaje;}
    public void setMensaje(String mensaje) {this.mensaje = mensaje;}
    public LocalDateTime getFechaInicio() {return fechaInicio;}
    public void setFechaInicio(LocalDateTime fechaInicio) {this.fechaInicio = fechaInicio;}


    @Override
    public String toString() {
        return "Notificacion{" +
                "destinatario='" + destinatario + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaInicio=" + fechaInicio +
                '}';
    }
}
