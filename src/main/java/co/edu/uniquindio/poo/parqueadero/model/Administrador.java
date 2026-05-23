package co.edu.uniquindio.poo.parqueadero.model;

public class Administrador extends Persona implements IAutenticable {

    //atributos

    private final String usuario = "SamuelRojas";
    private final String contrasena = "123456";

    /**
     * contructor de la clase Administrador
     *
     * @param nombre         del administrador
     * @param identificacion del administrador
     * @param telefono       del administrador
     * @param correo         del administrador
     */

    public Administrador(String nombre, String identificacion, String telefono, String correo, String usuario, String contrasena) {
        super(nombre, identificacion, telefono, correo);
    }

    /**
     * Metodo que permite hacer login al programa al administrador validando usuario y contraseña
     *
     * @return si lo validado es falso o verdadero
     */

    @Override
    public boolean logIn(String usuarioIngresado, String contrasenaIngresada) {
        boolean login = false;
        if (usuarioIngresado != null && contrasenaIngresada != null) {
            if (usuarioIngresado.trim().equals(usuario) && contrasenaIngresada.trim().equals(contrasena)) {
                login = true;
            }
        }
        return login;
    }


    /**
     * Metodoque permite hacer log Off al programa al administrador
     */

    @Override
    public void logOff() {

    }


    public String getContrasena() {
        return contrasena;
    }

    public String getUsuario() {
        return usuario;
    }


}
