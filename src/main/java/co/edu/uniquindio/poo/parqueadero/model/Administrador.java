package co.edu.uniquindio.poo.parqueadero.model;

public class Administrador extends Persona implements IAutenticable {
    //Constructor
    public Administrador(String nombre, String identificacion, String telefono, String correo) {
        super(nombre, identificacion, telefono, correo);
    }

    @Override
    public String logIn() {
        return "";
    }

    @Override
    public void logOff() {

    }


}
