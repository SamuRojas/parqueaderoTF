package co.edu.uniquindio.poo.parqueadero.model;

public class Operador extends Persona implements IAutenticable {
    //Constructor
    public Operador(String nombre, String identificacion, String telefono, String correo) {
        super(nombre, identificacion, telefono, correo);
    }

    @Override
    public boolean logIn(String usuario, String Contraseña) {
        return false;
    }

    @Override
    public void logOff() {

    }


}

