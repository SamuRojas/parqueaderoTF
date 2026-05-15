package co.edu.uniquindio.poo.parqueadero.model;

public class UsuarioParqueadero extends Persona {

    private TipoUsuarioParqueadero tipoUsuarioParqueadero;

    //Constructor

    public UsuarioParqueadero(String nombre, String identificacion, String telefono, String correo) {
        super(nombre, identificacion, telefono, correo);
        this.tipoUsuarioParqueadero = tipoUsuarioParqueadero;
    }





    public TipoUsuarioParqueadero getTipoUsuarioParqueadero() {
        return tipoUsuarioParqueadero;
    }
    public void setTipoUsuarioParqueadero(TipoUsuarioParqueadero tipoUsuarioParqueadero) {
        this.tipoUsuarioParqueadero = tipoUsuarioParqueadero;
    }
}
