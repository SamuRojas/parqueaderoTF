package co.edu.uniquindio.poo.parqueadero.model;

import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    private String nombre;
    private int capacidadTotal;
    private TipoParqueadero tipoParqueadero;


    private List<Espacio> listEspacios;
    private List<Registro> listRegistros;
    private List<Tarifa> listTarifas;
    private List<Vehiculo> listVehiculos;
    private List <Persona> listPersonas;

    public Parqueadero ( String nombre, int capacidadTotal, TipoParqueadero tipoParqueadero){
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
        this.tipoParqueadero = tipoParqueadero;

        this.listEspacios = new ArrayList<>();
        this.listRegistros = new ArrayList<>();
        this.listTarifas = new ArrayList<>();
        this.listVehiculos = new ArrayList<>();
        this.listPersonas = new ArrayList<>();
    }

//----------------------------------CRUD PARQUEADERO -----------------------

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

     public String buscarVehiculo ( String placa ){
         return "";
     }









    public String getNombre() {return nombre;}

    public int getCapacidadTotal() {return capacidadTotal;}

    public TipoParqueadero getTipoParqueadero() {return tipoParqueadero;}

    public List<Espacio> getListEspacios() {return listEspacios;}

    public List<Registro> getListRegistros() {return listRegistros;}

    public List<Tarifa> getListTarifas() {return listTarifas;}

    public List<Vehiculo> getListVehiculos() {return listVehiculos;}

    public List<Persona> getListPersonas() {return listPersonas;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setCapacidadTotal(int capacidadTotal) {this.capacidadTotal = capacidadTotal;}

    public void setTipoParqueadero(TipoParqueadero tipoParqueadero) {this.tipoParqueadero = tipoParqueadero;}

    public void setListEspacios(List<Espacio> listEspacios) {this.listEspacios = listEspacios;}

    public void setListRegistros(List<Registro> listRegistros) {this.listRegistros = listRegistros;}

    public void setListTarifas(List<Tarifa> listTarifas) {this.listTarifas = listTarifas;}

    public void setListVehiculos(List<Vehiculo> listVehiculos) {this.listVehiculos = listVehiculos;}

    public void setListPersonas(List<Persona> listPersonas) {this.listPersonas = listPersonas;}

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
                ", listPersonas=" + listPersonas +
                '}';
    }
}
