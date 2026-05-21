package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {

    //Atributos
    private String nombre;
    private int capacidadTotal;
    private TipoParqueadero tipoParqueadero;

    //Relaciones

    private List<Espacio> listEspacios;
    private List<Registro> listRegistros;
    private List<Tarifa> listTarifas;
    private List<Vehiculo> listVehiculos;
    private List <UsuarioParqueadero> listUsuariosParqueaderos;

    /**
     *contructor de la clase Parqueadero
     * @param nombre del administrador
     * @param capacidadTotal del parqueadero
     */

    public Parqueadero ( String nombre, int capacidadTotal, TipoParqueadero tipoParqueadero){
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
        this.tipoParqueadero = tipoParqueadero;

        this.listEspacios = new ArrayList<>();
        this.listRegistros = new ArrayList<>();
        this.listTarifas = new ArrayList<>();
        this.listVehiculos = new ArrayList<>();
        this.listUsuariosParqueaderos = new ArrayList<>();
    }

     public boolean buscarVehiculo (String placa) {
         boolean existe = false;
         for (Vehiculo vh : listVehiculos) {
             if (vh.getPlaca().equals(placa)) {
                 existe = true;
                 break;
             }
         }
         return existe;
     }

     public String RegistrarNuevoVehiculo(String placa, String nombreConductor, String identificacionConductor, EstadoVehiculo estadoVehiculo, LocalTime horaIngreso, LocalTime horaSalida, TipoVehiculo tipoVehiculo){
        String respuesta = "";
        if(buscarVehiculo(placa)) {
            respuesta = "el vehiculo ya existe";
        }else{
            Vehiculo vehiculoNuevo = new Vehiculo(placa, nombreConductor, identificacionConductor, tipoVehiculo, estadoVehiculo, horaIngreso, horaSalida);
            listVehiculos.add(vehiculoNuevo);

            respuesta = "el vehiculo con la placa: "+vehiculoNuevo.getPlaca() + "se ah registrado con exito y listo para asignarle un espacio";
        }
        return respuesta;
    }



     public String obtenerVehiculo (String placa){
        String infoVehiculo = "";

        if(buscarVehiculo(placa) && EstadoVehiculo == EstadoVehiculo.DENTRO){



        }
        return infoVehiculo;
      }


     public String registrarIngreso(String placa, TipoVehiculo tipoVehiculo, String nombreConductor, String id){
      return  "";
     }

     public String registrarSalida(String placa){
        return "";
     }

     public double consultarVehiculosDentro (){
       return 0 ;
     }

     public String consultarEspaciosDisponibles (){
        return "";
     }

     public String agregarEspacio (String codigo, TipoEspacio tipoEspacio){
         return "";
     }

     public String deshabilitarEspacio (String codigo){
         return "";
     }

     public String registrarPersona (String nombre, String identificacion, TipoUsuarioParqueadero tipoUsuarioParqueadero){
         return "";
     }

     public String buscarEspacioDisponible (TipoEspacio tipoEspacio){
         return "";
     }











    public String getNombre() {return nombre;}

    public int getCapacidadTotal() {return capacidadTotal;}

    public TipoParqueadero getTipoParqueadero() {return tipoParqueadero;}

    public List<Espacio> getListEspacios() {return listEspacios;}

    public List<Registro> getListRegistros() {return listRegistros;}

    public List<Tarifa> getListTarifas() {return listTarifas;}

    public List<Vehiculo> getListVehiculos() {return listVehiculos;}

    public List<UsuarioParqueadero> getListUsuariosParqueaderos() {
        return listUsuariosParqueaderos;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setCapacidadTotal(int capacidadTotal) {this.capacidadTotal = capacidadTotal;}

    public void setTipoParqueadero(TipoParqueadero tipoParqueadero) {this.tipoParqueadero = tipoParqueadero;}

    public void setListEspacios(List<Espacio> listEspacios) {this.listEspacios = listEspacios;}

    public void setListRegistros(List<Registro> listRegistros) {this.listRegistros = listRegistros;}

    public void setListTarifas(List<Tarifa> listTarifas) {this.listTarifas = listTarifas;}

    public void setListVehiculos(List<Vehiculo> listVehiculos) {this.listVehiculos = listVehiculos;}

    public void setListUsuariosParqueaderos(List<UsuarioParqueadero> listUsuariosParqueaderos) {
        this.listUsuariosParqueaderos = listUsuariosParqueaderos;
    }

    @Override
    public String toString() {
        return "Parqueadero{" +
                "nombre='" + nombre + '\'' +
                ", capacidadTotal='" + capacidadTotal + '\'' +
                ", tipoParqueadero=" + tipoParqueadero +
                ", listEspacios=" + listEspacios +
                ", listRegistros=" + listRegistros +
                ", listTarifas=" + listTarifas +
                ", listVehiculos=" + listVehiculos +
                ", listPersonas=" + listUsuariosParqueaderos +
                '}';
    }
}
