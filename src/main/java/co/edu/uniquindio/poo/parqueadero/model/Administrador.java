package co.edu.uniquindio.poo.parqueadero.model;

public class Administrador extends Persona implements IAutenticable {
    private final String usuario= "SamuelRojas";
    private final String contraseña = "123456";
    //Constructor
    public Administrador(String nombre, String identificacion, String telefono, String correo, String usuario, String contraseña) {
        super(nombre, identificacion, telefono, correo);
    }

    @Override
    public boolean logIn(String usuario, String Contraseña) {

        if ()
        return false;
    }

    @Override
    public void logOff() {

    }

    public String getContraseña() {return contraseña;}
    public String getUsuario() {return usuario;}
}
