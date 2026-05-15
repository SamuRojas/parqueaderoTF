package co.edu.uniquindio.poo.parqueadero.model;
import java.util.ArrayList;
import java.util.List;

public abstract class persona {

    //atributos
    private String nombre, identificacion, telefono,correo;

    //Relaciones

    private List<vehiculo> listVehiculosDePersona;
    private List<administrador> listAdministradores;
    private List<operador> listOperadores;

   /**
   *contructor de la clase persona
   * @param nombre de la persona
   * @param identificacion de la persona
   * @param telefono de la persona
   * @param correo de la persona
   */

   public persona ( String nombre, String identificacion, String telefono, String correo){
      this.nombre = nombre;
      this.identificacion = identificacion;
      this.telefono = telefono;
      this.correo = correo;

      this.listVehiculosDePersona = new ArrayList<>();
      this.listAdministradores = new ArrayList<>();
      this.listOperadores = new ArrayList<>();
   }


   public String getNombre(){ return nombre;}
    public void setNombre ( String nombre) { this.nombre = nombre;}

    public String getIdentificacion (){ return identificacion;}
    public void setIdentificacion (String identificacion){this.identificacion = identificacion;}

    public String getTelefono (){ return telefono;}
    public void setTelefono(String telefono){this.telefono = telefono;}

    public String getCorreo (){return correo;}
    public void setCorreo(String correo){this.correo = correo;}


    @Override
    public String toString() {
        return "persona{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", listVehiculosDePersona=" + listVehiculosDePersona +
                ", listAdministradores=" + listAdministradores +
                ", listOperadores=" + listOperadores +
                '}';
    }
}
