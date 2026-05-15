package co.edu.uniquindio.poo.parqueadero.model;

public interface IAutenticable {

    boolean logIn(String usuario, String Contraseña);

    void logOff();

}
