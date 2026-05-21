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


    //-----------------------------CRUD ESPACIO------------------------------------------------------------------------------------------------------------------
    public boolean buscarEspacio(String codigo){
        boolean existe = false;
        for(Espacio espacio : listEspacios){
            if(espacio.getCodigo().equals(codigo)){
                existe = true;
                break;
            }
        }
        return existe;
    }


    public String agregarEspacio(String codigo, TipoEspacio tipoEspacio, EstadoEspacio estadoEspacio){
        String respuesta = "";
        if(buscarEspacio(codigo)){
            respuesta = " un espacio con este codigo ya existe ";
        }else{
            Espacio espacioNuevo = new Espacio(codigo, tipoEspacio, estadoEspacio);
            listEspacios.add(espacioNuevo);
            respuesta = "el espacio con el codigo " + espacioNuevo.getCodigo()+"se ah registrado correctamente;";
        }
        return respuesta;
    }


    public List<Espacio> consultarEspaciosDisponibles(TipoEspacio tipoEspacio){
        List<Espacio> espaciosDisponibles = new ArrayList<>();
        for(Espacio ep : listEspacios){
            if(ep.getEstadoEspacio()== EstadoEspacio.DISPONIBLE && ep.getTipoEspacio() == tipoEspacio){
                espaciosDisponibles.add(ep);
            }
        }
            return espaciosDisponibles;
    }


    public Espacio obtenerEspacio(String codigo){
        Espacio encontrado = null;
        for(Espacio espacio : listEspacios){
            if(espacio.getCodigo().equals(codigo)){
                encontrado =  espacio;
                break;
            }
        }
        return encontrado;
    }


    public String modificarEstadoEspacio(String codigo, EstadoEspacio nuevoEstado){
        String respuesta;
         Espacio espacio = obtenerEspacio(codigo);
               if(espacio == null){
                   respuesta = "Espacio no encontrado ";
               } else {
                   EstadoEspacio estadoActual = espacio.getEstadoEspacio();
                   if (estadoActual == EstadoEspacio.OCUPADO && nuevoEstado == EstadoEspacio.FUERADESERVICIO) {
                       respuesta = " no se puede deshabilitar un espacio ocupado";
                   } else {
                       espacio.setEstadoEspacio(nuevoEstado);
                       respuesta = " estado cambiado con exito";

                       }
               }
        return respuesta;
    }

    //----------------------------------CRUD VEHICULO -------------------------------------------------------------------------------------------------------------------------------

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


     public String registrarNuevoVehiculo(String placa, String nombreConductor, String identificacionConductor, EstadoVehiculo estadoVehiculo, LocalTime horaIngreso, TipoVehiculo tipoVehiculo){
        String respuesta = "";
        if(buscarVehiculo(placa)) {
            respuesta = "el vehiculo ya existe";
        }else{
            Vehiculo vehiculoNuevo = new Vehiculo(placa, nombreConductor, identificacionConductor, tipoVehiculo, estadoVehiculo, horaIngreso, null);
            listVehiculos.add(vehiculoNuevo);

            respuesta = "el vehiculo con la placa: "+vehiculoNuevo.getPlaca() + "se ah registrado con exito y listo para asignarle un espacio";
        }
        return respuesta;
    }

    public Vehiculo obtenerVehiculo(String placa){
        Vehiculo encontrado = null;
        for(Vehiculo vehiculo : listVehiculos){
            if(vehiculo.getPlaca().equals(placa)){
                encontrado = vehiculo;
                break;
            }
        }
        return encontrado;
    }


    public String registrarSalida(String placa){
        String respuesta;
           Vehiculo vehiculo = obtenerVehiculo(placa);

        if(vehiculo == null){
            respuesta = " el vehiculo no existe";

            }else if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.FUERA){
                 respuesta = " el vehiculo ya salio del parqueadero";

                 }else{
                       vehiculo.setEstadoVehiculo(EstadoVehiculo.FUERA);
                            vehiculo.setHoraSalida(LocalTime.now());
                                 respuesta = "salida registrada con exito";
                      }
        return respuesta;
    }




//-----------------------------CRUD USUARIOPARQUEADERO-----------------------------------------------------------------
    public boolean buscarUsuarioParqueadero (String identificacion) {
        boolean existe = false;
        for (UsuarioParqueadero up : listUsuariosParqueaderos) {
            if (up.getIdentificacion().equals(identificacion)) {
               existe = true;
               break;
            }
        }
        return existe;
    }


    public String registrarNuevoUsuario (String nombre, String identificacion, String telefono, String correo, TipoUsuarioParqueadero tipoUsuarioParqueadero){
        String respuesta = " ";
        if(buscarUsuarioParqueadero(identificacion)){
            respuesta = "El usuario ya fue registrado en el sistema";
        }else{
            UsuarioParqueadero nuevoUsuarioParqueadero = new UsuarioParqueadero(nombre, identificacion, telefono, correo, tipoUsuarioParqueadero);
            listUsuariosParqueaderos.add(nuevoUsuarioParqueadero);

            respuesta = "El usuario" + nuevoUsuarioParqueadero.getNombre() + " se registro correctamente";
        }
        return respuesta;
    }

    public UsuarioParqueadero obtenerUsuarioParqueadero(String identificacion){
        UsuarioParqueadero encontrado = null;
        for(UsuarioParqueadero usuario: listUsuariosParqueaderos){
            if(usuario.getIdentificacion().equals(identificacion)){
                encontrado = usuario;
                break;
            }
        }
        return encontrado;
    }


    public String actualizarInfoUsuarioParqueadero(String nuevoNombre, String identificacion, String nuevoTelefono, String nuevoCorreo, TipoUsuarioParqueadero nuevoTipoUsuarioParqueadero){
        String respuesta;
        UsuarioParqueadero up = obtenerUsuarioParqueadero(identificacion);
        if( up == null){
            respuesta = "Usuario no encontrado";
        }else{
            up.setNombre(nuevoNombre);
            up.setTelefono(nuevoTelefono);
            up.setCorreo(nuevoCorreo);
            up.setTipoUsuarioParqueadero(nuevoTipoUsuarioParqueadero);
            respuesta = "Usuario actualizado correctamente";
        }
        return respuesta;
    }

    public String eliminarUsuario(String identificacion){
        String respuesta;
        UsuarioParqueadero up = obtenerUsuarioParqueadero(identificacion);
        if(up == null){
            respuesta = "Cliente no encontrado";
        }else{
            listUsuariosParqueaderos.remove(up);
            respuesta = "Usuario eliminado correctamente";
        }
        return respuesta;
    }

    //---------------------------------CRUD TARIFA----------------------------------------------------------------------------------
    public Tarifa obtenerTarifa(TipoVehiculo tipoVehiculo){
        Tarifa encontrado = null;
        for(Tarifa tarifa: listTarifas){
            if(tarifa.getTipoVehiculo()==tipoVehiculo){
                encontrado = tarifa;
                break;
            }
        }
        return encontrado;
    }






     public String registrarIngreso(String placa, TipoEspacio tipoEspacio, String nombreConductor, String identificacionConductor, TipoVehiculo tipoVehiculo ){
        String respuesta;

        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            registrarNuevoVehiculo(placa, nombreConductor, identificacionConductor, EstadoVehiculo.FUERA, LocalTime.now(), tipoVehiculo );
            vehiculo = obtenerVehiculo(placa);
        } else if(consultarEspaciosDisponibles(tipoEspacio)){
            theEspacioDelVehiculo.add(espacio);
            obtenerEspacio().setTheVehiculo(this);

            vehiculo.setEstadoVehiculo(EstadoVehiculo.DENTRO);
            vehiculo.setHoraIngreso(LocalTime.now());

            respuesta = "se le ah asignado un espacio correctamente";

        }else {
            respuesta = "no hay espacios disponibles";
        }
      return  respuesta ;
     }


     public double consultarVehiculosDentro (){
       return 0 ;
     }



















    public String getNombre() {return nombre;}

    public int getCapacidadTotal() {return capacidadTotal;}

    public TipoParqueadero getTipoParqueadero() {return tipoParqueadero;}

    public List<Espacio> getListEspacios() {return listEspacios;}

    public List<Registro> getListRegistros() {return listRegistros;}

    public List<Tarifa> getListTarifas() {return listTarifas;}

    public List<Vehiculo> getListVehiculos() {return listVehiculos;}

    public List<UsuarioParqueadero> getListUsuariosParqueaderos() {return listUsuariosParqueaderos;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setCapacidadTotal(int capacidadTotal) {this.capacidadTotal = capacidadTotal;}

    public void setTipoParqueadero(TipoParqueadero tipoParqueadero) {this.tipoParqueadero = tipoParqueadero;}

    public void setListEspacios(List<Espacio> listEspacios) {this.listEspacios = listEspacios;}

    public void setListRegistros(List<Registro> listRegistros) {this.listRegistros = listRegistros;}

    public void setListTarifas(List<Tarifa> listTarifas) {this.listTarifas = listTarifas;}

    public void setListVehiculos(List<Vehiculo> listVehiculos) {this.listVehiculos = listVehiculos;}

    public void setListUsuariosParqueaderos(List<UsuarioParqueadero> listUsuariosParqueaderos) {this.listUsuariosParqueaderos = listUsuariosParqueaderos;}

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
