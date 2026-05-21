package co.edu.uniquindio.poo.parqueadero.model;

public class Administrador extends Persona implements IAutenticable {

    //atributos

    private final String usuario= "SamuelRojas";
    private final String contraseña = "123456";

    /**
     *contructor de la clase Administrador
     * @param nombre del administrador
     * @param identificacion del administrador
     * @param telefono del administrador
     * @param correo del administrador
     */

    public Administrador(String nombre, String identificacion, String telefono, String correo, String usuario, String contraseña) {
        super(nombre, identificacion, telefono, correo);
    }

    /**
     *Metodo que permite hacer login al programa al administrador validando usuario y contraseña
     * @return si lo validado es falso o verdadero
     */

    @Override
    public boolean logIn(String usuario, String Contraseña) {
        boolean login = false;
          if (usuarioIngresado.equals(usuario) && contraseñaIngresada.equals(contraseña)){
              login = true;
          }

        return login;
    }

    /**
     *Metodoque permite hacer log Off al programa al administrador
     */

    @Override
    public void logOff() {

    }







    public String getContraseña() {return contraseña;}
    public String getUsuario() {return usuario;}


}
