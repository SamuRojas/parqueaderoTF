package co.edu.uniquindio.poo.parqueadero.model;

public class Operador extends Persona implements IAutenticable {


    //Constructor


    public Operador(String nombre, String identificacion, String telefono, String correo) {
        super(nombre, identificacion, telefono, correo);
    }

    /**
     *Metodo que permite hacer login al programa al operador validando usuario y contraseña
     * @return si lo validado es falso o verdadero
     */

    @Override
    public boolean logIn(String usuario, String Contraseña) {
        return false;
    }

    /**
     *Metodo que permite hacer log Off al programa al administrador
     */

    @Override
    public void logOff() {

    }


}

