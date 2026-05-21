package co.edu.uniquindio.poo.parqueadero.model;

public interface IAutenticable {

    /**
     *Metodo Abstracto que permite hacer loggin
     */

    boolean logIn(String usuario, String Contraseña);

    /**
     *Metodo Abstracto que permite hacer log off
     */

    void logOff();

}
