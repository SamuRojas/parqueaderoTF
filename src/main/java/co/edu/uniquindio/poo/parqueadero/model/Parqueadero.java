package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDate;
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

    public String modificarInformacionEspacio(String codigo, TipoEspacio nuevoTipoEspacio, EstadoEspacio nuevoEstadoEspacio){
        String respuesta = "";
        Espacio espacio = obtenerEspacio(codigo);
        if(espacio == null){
            respuesta = "Espacio no encontrado";
        } else {
            if(espacio.getEstadoEspacio() == EstadoEspacio.OCUPADO){
                respuesta = "No se puede modificar un espacio ocupado";
            } else {
                espacio.setTipoEspacio(nuevoTipoEspacio);
                espacio.setEstadoEspacio(nuevoEstadoEspacio);
                respuesta = "Espacio actualizado correctamente";
            }
        }
        return respuesta;
    }

    public List<String> listarCodigosEspaciosDisponibles(TipoEspacio tipoEspacio){

        List<String> codigos = new ArrayList<>();

        List<Espacio> espacios = consultarEspaciosDisponibles(tipoEspacio);

        for(Espacio espacio : espacios){
            codigos.add(espacio.getCodigo());
        }
        return codigos;
    }



    public String consultarDetalleEspacios(){
        String respuesta = "";

        for(Espacio espacio : listEspacios){

            respuesta += "CODIGO: " + espacio.getCodigo() + " | TIPO: " + espacio.getTipoEspacio() + " | ESTADO: " + espacio.getEstadoEspacio();

            if(espacio.getTheVehiculo() != null){
                respuesta += " | PLACA: " + espacio.getTheVehiculo().getPlaca();
            }
            respuesta += "\n";
        }
        return respuesta;
    }


    public String resumenEspacios(){
        int ocupados = 0;
        int disponibles = 0;
        int fueraServicio = 0;
        for(Espacio espacio : listEspacios){
            if(espacio.getEstadoEspacio() == EstadoEspacio.OCUPADO){
                ocupados++;
            } else if(espacio.getEstadoEspacio() == EstadoEspacio.DISPONIBLE){
                disponibles++;
            } else {
                fueraServicio++;
            }
        }
        return
                "Disponibles: " + disponibles + " | Ocupados: " + ocupados + " | Fuera de servicio: " + fueraServicio;
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


    public String registrarNuevoVehiculo(String placa, String nombreConductor, String identificacionConductor, EstadoVehiculo estadoVehiculo, LocalTime horaIngreso, TipoVehiculo tipoVehiculo) {
        String respuesta = "";
        if (buscarVehiculo(placa)) {
            respuesta = "el vehiculo ya existe";
        } else {
            Vehiculo vehiculoNuevo = new Vehiculo(
                    placa,
                    nombreConductor,
                    identificacionConductor,
                    estadoVehiculo,
                    tipoVehiculo,
                    horaIngreso,
                    null,
                    null,
                    null);
            listVehiculos.add(vehiculoNuevo);
            respuesta = "el vehiculo con la placa: " + vehiculoNuevo.getPlaca() + " se ha registrado con exito y listo para asignarle un espacio";
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

    public boolean vehiculoDentro(String placa){
        boolean estaDentro = false;
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo != null){
            if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.DENTRO){
                estaDentro = true;
            }
        }
        return estaDentro;
    }

    public TipoEspacio tipoEspacioDesdeVehiculo(TipoVehiculo tipoVehiculo){
        TipoEspacio tipoEspacio = null;
        if(tipoVehiculo == TipoVehiculo.CARRO){
            tipoEspacio = TipoEspacio.CARRO;}
        if(tipoVehiculo == TipoVehiculo.MOTO){
            tipoEspacio = TipoEspacio.MOTO;}
        if(tipoVehiculo == TipoVehiculo.BICICLETA){
            tipoEspacio = TipoEspacio.BICICLETA;}
        return tipoEspacio;
    }

    public Espacio buscarEspacioParaIngreso(TipoVehiculo tipoVehiculo){
        Espacio espacioEncontrado = null;
        TipoEspacio tipoEspacio = tipoEspacioDesdeVehiculo(tipoVehiculo);
        List<Espacio> espaciosDisponibles = consultarEspaciosDisponibles(tipoEspacio);

        if(espaciosDisponibles.size() > 0){
            espacioEncontrado = espaciosDisponibles.get(0);
        }
        return espacioEncontrado;
    }

    public void asignarEspacioAVehiculo(Vehiculo vehiculo, Espacio espacio){
        espacio.setEstadoEspacio(EstadoEspacio.OCUPADO);
        espacio.setTheVehiculo(vehiculo);
        vehiculo.setTheEspacioDelVehiculo(espacio);
    }

    public String asignarEspacioPorPlaca(String placa, String codigoEspacio){
        String respuesta = "";
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            respuesta = "Vehiculo no encontrado";
        } else {
            Espacio espacio = obtenerEspacio(codigoEspacio);
            if(espacio == null){
                respuesta = "Espacio no encontrado";
            } else {
                if(espacio.getEstadoEspacio() == EstadoEspacio.OCUPADO){
                    respuesta = "El espacio ya esta ocupado";
                } else {
                    asignarEspacioAVehiculo(vehiculo, espacio);
                    respuesta = "Espacio asignado correctamente";
                }
            }
        }
        return respuesta;
    }

    public void asignarUsuarioVehiculo(Vehiculo vehiculo, String identificacionConductor){
        UsuarioParqueadero usuario = obtenerUsuarioParqueadero(identificacionConductor);
        if(usuario != null){
            vehiculo.setTheUsuarioDelVehiculo(usuario);
            usuario.asignarVehiculo(vehiculo);
        }
    }

    public String registrarIngreso(String placa, String nombreConductor, String identificacionConductor, TipoVehiculo tipoVehiculo){
        String respuesta = "";
        if(vehiculoDentro(placa)){
            respuesta = "Ya existe un vehiculo dentro con esta placa";
        } else {
            Espacio espacio = buscarEspacioParaIngreso(tipoVehiculo);
            if(espacio == null){
                respuesta = "No hay espacios disponibles";
            } else {
                Vehiculo vehiculo = obtenerVehiculo(placa);
                LocalTime horaIngreso = LocalTime.now();
                if(vehiculo == null){
                    registrarNuevoVehiculo(
                            placa,
                            nombreConductor,
                            identificacionConductor,
                            EstadoVehiculo.DENTRO,
                            horaIngreso,
                            tipoVehiculo);
                    vehiculo = obtenerVehiculo(placa);
                } else {
                    vehiculo.setNombreConductor(nombreConductor);
                    vehiculo.setIdentificacionConductor(identificacionConductor);
                    vehiculo.setTipoVehiculo(tipoVehiculo);
                    vehiculo.setEstadoVehiculo(EstadoVehiculo.DENTRO);
                    vehiculo.setHoraIngreso(horaIngreso);
                    vehiculo.setHoraSalida(null);
                }

                asignarUsuarioVehiculo(vehiculo, identificacionConductor);
                asignarEspacioAVehiculo(vehiculo, espacio);

                Registro registro = new Registro(
                                placa,
                                LocalDate.now(),
                                horaIngreso,
                                null,
                                0,
                                0);
                listRegistros.add(registro);
                vehiculo.getListRegistroDelVehiculo().add(registro);
                respuesta = "Ingreso registrado correctamente. Espacio asignado: "+ espacio.getCodigo();
            }
        }
        return respuesta;
    }

    public double calcularHoras(LocalTime horaIngreso, LocalTime horaSalida){
        int minutosIngreso = (horaIngreso.getHour() * 60) + horaIngreso.getMinute();
        int minutosSalida = (horaSalida.getHour() * 60) + horaSalida.getMinute();
        int minutosTotales = minutosSalida - minutosIngreso;

        double horas = minutosTotales / 60.0;

        if(horas < 1){
            horas = 1;
        }
        return horas;
    }

    public String consultarInformacionVehiculo(String placa){
        String respuesta = "";
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            respuesta = "Vehiculo no encontrado";
        } else {
            respuesta = "PLACA: " + vehiculo.getPlaca()
                            + "\nCONDUCTOR: " + vehiculo.getNombreConductor()
                            + "\nIDENTIFICACION: " + vehiculo.getIdentificacionConductor()
                            + "\nTIPO VEHICULO: " + vehiculo.getTipoVehiculo()
                            + "\nESTADO: " + vehiculo.getEstadoVehiculo()
                            + "\nHORA INGRESO: " + vehiculo.getHoraIngreso();

            if(vehiculo.getTheEspacioDelVehiculo() != null){
                respuesta = respuesta
                                + "\nESPACIO: " + vehiculo.getTheEspacioDelVehiculo().getCodigo();
            }
        }
        return respuesta;
    }


    public List<Vehiculo> consultarVehiculosDentro(){
        List<Vehiculo> vehiculosDentro = new ArrayList<>();
        for(Vehiculo vehiculo : listVehiculos){
            if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.DENTRO){
                vehiculosDentro.add(vehiculo);
            }
        }
        return vehiculosDentro;
    }

    public double calcularValorPagar(Vehiculo vehiculo, double horas){
        double valorPagar = 0;
        Tarifa tarifa = obtenerTarifa(vehiculo.getTipoVehiculo());
        if(tarifa != null){
            valorPagar = horas * tarifa.getValorPorHora();
            if(vehiculo.getTheUsuarioDelVehiculo() != null){
                double descuento = valorPagar * tarifa.getDescuento() /100;
                valorPagar = valorPagar - descuento;
            }
        }
        return valorPagar;
    }



    public String simularSalida(String placa){
        String respuesta = "";
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            respuesta = "Vehiculo no encontrado";
        } else {
            if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.FUERA){
                respuesta = "El vehiculo no esta dentro";
            } else {
                LocalTime horaSalida = LocalTime.now();
                double horas = calcularHoras(vehiculo.getHoraIngreso(), horaSalida);
                double valorPagar = calcularValorPagar(vehiculo, horas);
                respuesta = "Tiempo total: " + horas + " horas, Valor estimado: $" + valorPagar;
            }
        }
        return respuesta;
    }

    public String textoVehiculoDentro(Vehiculo vehiculo){
        String respuesta = vehiculo.getPlaca() + " - " + vehiculo.getNombreConductor();
        if(vehiculo.getTheEspacioDelVehiculo() != null){
            respuesta = respuesta + " - " + vehiculo.getTheEspacioDelVehiculo().getCodigo();
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

    public boolean buscarTarifa(TipoVehiculo tipoVehiculo){
        boolean existe = false;
        Tarifa tarifa = obtenerTarifa(tipoVehiculo);
        if(tarifa != null){
            existe = true;
        }
        return existe;
    }

    public String agregarTarifa(TipoVehiculo tipoVehiculo, double valorPorHora, double descuento){
        String respuesta = "";
        if(buscarTarifa(tipoVehiculo)){
            respuesta = "Ya existe una tarifa";
        } else {
            Tarifa tarifaNueva = new Tarifa(
                            tipoVehiculo,
                            valorPorHora,
                            descuento);
            listTarifas.add(tarifaNueva);
            respuesta = "Tarifa registrada correctamente";
        }
        return respuesta;
    }


    public String actualizarTarifa(TipoVehiculo tipoVehiculo, double nuevoValorHora, double nuevoDescuento){
        String respuesta = "";
        Tarifa tarifa = obtenerTarifa(tipoVehiculo);
        if(tarifa == null){
            respuesta = "Tarifa no encontrada";
        } else {
            tarifa.setValorPorHora(nuevoValorHora);
            tarifa.setDescuento(nuevoDescuento);
            respuesta = "Tarifa actualizada correctamente";
        }
        return respuesta;
    }


    public ReporteDiario generarReporteDiario(){

        ReporteDiario reporte = new ReporteDiario(

        );
        for(Registro registro : listRegistros){
            reporte.agregarRegistro(registro
            );
        }
        reporte.calcularPromedio();
        return reporte;
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
