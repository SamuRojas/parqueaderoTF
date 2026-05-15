package co.edu.uniquindio.poo.parqueadero.model;

public interface IAutenticable {

    /**
     *Metodo Abstracto que permite hacer loggin
     */

    boolean logIn(String usuario, String Contraseña);
    void logOff();

}
