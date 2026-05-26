package co.edu.uniquindio.poo.parqueadero.model;

public class Operador extends Persona implements IAutenticable {

    private final String usuario = "operador";
    private final String contrasena = "123456";

    //Constructor
    /**
     * Constructor de la clase Operador
     * @param nombre del operador
     * @param identificacion del operador
     * @param telefono del operador
     * @param correo del operador
     */

    public Operador(String nombre, String identificacion, String telefono, String correo) {
        super(nombre, identificacion, telefono, correo);
    }

    /**
     *Metodo que permite hacer login al programa al operador validando usuario y contraseña
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
     *Metodo que permite hacer log Off al programa al administrador
     */

    @Override
    public void logOff() {

    }

    public String getUsuario() {return usuario;}
    public String getContrasena() {
        return contrasena;
    }


    @Override
    public String toString() {
        return "Operador{" +
                "usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}

