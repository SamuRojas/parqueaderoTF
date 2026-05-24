package co.edu.uniquindio.poo.parqueadero.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioParqueadero extends Persona {

    private TipoUsuarioParqueadero tipoUsuarioParqueadero;


    private List<Vehiculo> listVehiculosDeUsuario;

    //Constructor

    public UsuarioParqueadero(String nombre, String identificacion, String telefono, String correo, TipoUsuarioParqueadero tipoUsuarioParqueadero) {
        super(nombre, identificacion, telefono, correo);
        this.tipoUsuarioParqueadero = tipoUsuarioParqueadero;

        this.listVehiculosDeUsuario = new ArrayList<>();
    }

   public void asignarVehiculo(Vehiculo vehiculo){

        listVehiculosDeUsuario.add(vehiculo);
        vehiculo.setTheUsuarioDelVehiculo(this);
   }



    public TipoUsuarioParqueadero getTipoUsuarioParqueadero() {
        return tipoUsuarioParqueadero;
    }
    public void setTipoUsuarioParqueadero(TipoUsuarioParqueadero tipoUsuarioParqueadero) {this.tipoUsuarioParqueadero = tipoUsuarioParqueadero;}

    @Override
    public String toString() {
        return "UsuarioParqueadero{" +
                "tipoUsuarioParqueadero=" + tipoUsuarioParqueadero +
                ", listVehiculosDeUsuario=" + listVehiculosDeUsuario +
                '}';
    }
}
