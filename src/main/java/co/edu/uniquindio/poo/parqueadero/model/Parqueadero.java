package co.edu.uniquindio.poo.parqueadero.model;

import co.edu.uniquindio.poo.parqueadero.exception.EspacioNoDisponibleException;
import co.edu.uniquindio.poo.parqueadero.exception.PlacaDuplicadaException;
import co.edu.uniquindio.poo.parqueadero.exception.VehiculoNoEncontradoException;
import co.edu.uniquindio.poo.parqueadero.exception.VehiculoNoIngresoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class    Parqueadero {

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
     * @param tipoParqueadero tipo del parqueadero
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
    /**
     * Metodo que permite buscar si un espacio ya existe en el parqueadero
     * segun su codigo, recorriendo la lista de espacios
     * @param codigo del espacio a buscar
     * @return true si el espacio existe, false si no existe
     */

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

    /**
     * Metodo que permite agregar un nuevo espacio al parqueadero,
     * siempre y cuando no exista otro con el mismo codigo
     * @param codigo del nuevo espacio
     * @param tipoEspacio del nuevo espacio
     * @param estadoEspacio del nuevo espacio
     * @return mensaje indicando si el espacio fue registrado o si ya existia
     */

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

    /**
     * Metodo que permite consultar todos los espacios disponibles
     * que coincidan con el tipo de espacio indicado
     * @param tipoEspacio tipo de espacio a buscar
     * @return lista de espacios disponibles del tipo indicado
     */

    public List<Espacio> consultarEspaciosDisponibles(TipoEspacio tipoEspacio){
        List<Espacio> espaciosDisponibles = new ArrayList<>();
        for(Espacio ep : listEspacios){
            if(ep.getEstadoEspacio()== EstadoEspacio.DISPONIBLE && ep.getTipoEspacio() == tipoEspacio){
                espaciosDisponibles.add(ep);
            }
        }
            return espaciosDisponibles;
    }

    /**
     * Metodo que permite obtener un objeto Espacio buscandolo por su codigo
     * @param codigo del espacio a obtener
     * @return el espacio encontrado, o null si no existe
     */

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

    /**
     * Metodo que permite modificar solo el estado de un espacio,
     * validando que no se pueda deshabilitar un espacio que esta ocupado
     * @param codigo del espacio a modificar
     * @param nuevoEstado estado nuevo que se quiere asignar al espacio
     * @return mensaje indicando el resultado de la operacion
     */

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


    /**
     * Metodo que permite modificar tanto el tipo como el estado de un espacio,
     * validando que el espacio no este ocupado antes de hacer cambios
     * @param codigo del espacio a modificar
     * @param nuevoTipoEspacio nuevo tipo a asignar al espacio
     * @param nuevoEstadoEspacio nuevo estado a asignar al espacio
     * @return mensaje indicando el resultado de la operacion
     */

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

    /**
     * Metodo que permite obtener una lista con solo los codigos
     * de los espacios disponibles para un tipo de espacio especifico
     * @param tipoEspacio tipo de espacio a consultar
     * @return lista de codigos de los espacios disponibles
     */

    public List<String> listarCodigosEspaciosDisponibles(TipoEspacio tipoEspacio){

        List<String> codigos = new ArrayList<>();

        List<Espacio> espacios = consultarEspaciosDisponibles(tipoEspacio);

        for(Espacio espacio : espacios){
            codigos.add(espacio.getCodigo());
        }
        return codigos;
    }


    /**
     * Metodo que permite generar un texto con el detalle de todos los espacios del parqueadero,
     * mostrando codigo, tipo, estado y la placa del vehiculo si el espacio esta ocupado
     * @return texto con la informacion de todos los espacios
     */

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

    /**
     * Metodo que permite generar un resumen con la cantidad de espacios
     * disponibles, ocupados y fuera de servicio en el parqueadero
     * @return texto con el resumen de los estados de los espacios
     */

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

    /**
     * Metodo que permite buscar si un vehiculo ya existe en el parqueadero
     * segun su placa, recorriendo la lista de vehiculos
     * @param placa del vehiculo a buscar
     * @return true si el vehiculo existe, false si no existe
     */

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

    /**
     * Metodo que permite registrar un vehiculo nuevo en el sistema del parqueadero,
     * siempre y cuando no exista otro con la misma placa
     * @param placa del vehiculo a registrar
     * @param nombreConductor nombre de quien conduce el vehiculo
     * @param identificacionConductor identificacion de quien conduce el vehiculo
     * @param estadoVehiculo estado inicial del vehiculo
     * @param horaIngreso hora en que ingresa el vehiculo
     * @param tipoVehiculo tipo del vehiculo a registrar
     * @return mensaje indicando si el vehiculo fue registrado o si ya existia
     */

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

    /**
     * Metodo que permite obtener un objeto Vehiculo buscandolo por su placa
     * @param placa del vehiculo a obtener
     * @return el vehiculo encontrado, o null si no existe
     */

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

    /**
     * Metodo que permite registrar la salida de un vehiculo del parqueadero,
     * cambiando su estado a FUERA y asignandole la hora de salida actual.
     * Lanza excepcion si el vehiculo no existe o si ya salio
     * @param placa del vehiculo que va a salir
     * @return mensaje confirmando que la salida fue registrada
     */

    public String registrarSalida(String placa){
        String respuesta;
           Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            throw new VehiculoNoEncontradoException(placa);
            }else if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.FUERA){
                 throw new VehiculoNoIngresoException(placa);
                 }else{
                       vehiculo.setEstadoVehiculo(EstadoVehiculo.FUERA);
                            vehiculo.setHoraSalida(LocalTime.now());
                                 respuesta = "salida registrada con exito";
                      }
        return respuesta;
    }

    /**
     * Metodo que permite verificar si un vehiculo se encuentra actualmente dentro del parqueadero
     * @param placa del vehiculo a verificar
     * @return true si el vehiculo esta dentro, false si no esta o no existe
     */

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

    /**
     * Metodo que permite convertir el tipo de vehiculo en el tipo de espacio correspondiente,
     * para saber que clase de espacio se necesita al ingresar un vehiculo
     * @param tipoVehiculo tipo del vehiculo que ingresa
     * @return el tipo de espacio que le corresponde al vehiculo
     */

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

    /**
     * Metodo que permite buscar el primer espacio disponible que sea compatible
     * con el tipo del vehiculo que quiere ingresar al parqueadero
     * @param tipoVehiculo tipo del vehiculo para determinar que espacio necesita
     * @return el primer espacio disponible encontrado, o null si no hay ninguno
     */

    public Espacio buscarEspacioParaIngreso(TipoVehiculo tipoVehiculo){
        Espacio espacioEncontrado = null;
        TipoEspacio tipoEspacio = tipoEspacioDesdeVehiculo(tipoVehiculo);
        List<Espacio> espaciosDisponibles = consultarEspaciosDisponibles(tipoEspacio);

        if(espaciosDisponibles.size() > 0){
            espacioEncontrado = espaciosDisponibles.get(0);
        }
        return espacioEncontrado;
    }

    /**
     * Metodo que permite asignar un espacio a un vehiculo,
     * marcando el espacio como ocupado y vinculando ambos objetos entre si
     * @param vehiculo al que se le va a asignar el espacio
     * @param espacio que se le va a asignar al vehiculo
     */

    public void asignarEspacioAVehiculo(Vehiculo vehiculo, Espacio espacio){
        espacio.setEstadoEspacio(EstadoEspacio.OCUPADO);
        espacio.setTheVehiculo(vehiculo);
        vehiculo.setTheEspacioDelVehiculo(espacio);
    }

    /**
     * Metodo que permite asignar un espacio a un vehiculo buscandolos
     * por su placa y codigo respectivamente, validando que ambos existan
     * y que el espacio no este ya ocupado
     * @param placa del vehiculo al que se le asignara el espacio
     * @param codigoEspacio codigo del espacio a asignar
     * @return mensaje indicando el resultado de la operacion
     */

    public String asignarEspacioPorPlaca(String placa, String codigoEspacio){
        String respuesta = "";
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null) {
         throw new VehiculoNoEncontradoException(placa);
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

    /**
     * Metodo que permite vincular un usuario registrado del parqueadero con un vehiculo,
     * buscando al usuario por su identificacion y asignandolo si existe
     * @param vehiculo al que se le asignara el usuario
     * @param identificacionConductor identificacion del usuario a buscar y vincular
     */

    public void asignarUsuarioVehiculo(Vehiculo vehiculo, String identificacionConductor){
        UsuarioParqueadero usuario = obtenerUsuarioParqueadero(identificacionConductor);
        if(usuario != null){
            vehiculo.setTheUsuarioDelVehiculo(usuario);
            usuario.asignarVehiculo(vehiculo);
        }
    }

    /**
     * Metodo que permite registrar el ingreso de un vehiculo al parqueadero,
     * buscando un espacio disponible segun el tipo de vehiculo,
     * creando o actualizando el vehiculo en el sistema,
     * vinculando al usuario si existe, asignando el espacio
     * y creando el registro del ingreso.
     * Lanza excepcion si el vehiculo ya esta dentro o si no hay espacios disponibles
     * @param placa del vehiculo que ingresa
     * @param nombreConductor nombre de quien conduce
     * @param identificacionConductor identificacion de quien conduce
     * @param tipoVehiculo tipo del vehiculo que ingresa
     * @return mensaje confirmando el ingreso y el espacio asignado
     */

    public String registrarIngreso(String placa, String nombreConductor, String identificacionConductor, TipoVehiculo tipoVehiculo){
        String respuesta = "";
        if(vehiculoDentro(placa)){
            throw new PlacaDuplicadaException(placa);
        } else {
            Espacio espacio = buscarEspacioParaIngreso(tipoVehiculo);
            if(espacio == null){
               throw new EspacioNoDisponibleException();
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

    /**
     * Metodo que permite calcular la cantidad de horas que estuvo un vehiculo estacionado,
     * convirtiendo las horas y minutos a minutos totales y dividiendo entre 60.
     * Si el resultado es menor a 1 hora, se cobra minimo 1 hora
     * @param horaIngreso hora en que el vehiculo ingreso al parqueadero
     * @param horaSalida hora en que el vehiculo salio del parqueadero
     * @return cantidad de horas estacionadas, minimo 1
     */

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


    /**
     * Metodo que permite consultar y retornar la informacion de un vehiculo
     * buscandolo por su placa, mostrando sus datos y el espacio asignado si tiene uno.
     * Lanza excepcion si el vehiculo no existe
     * @param placa del vehiculo a consultar
     * @return texto con la informacion del vehiculo
     */

    public String consultarInformacionVehiculo(String placa){
        String respuesta = "";
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            throw new VehiculoNoEncontradoException(placa);
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


    /**
     * Metodo que permite obtener la lista de todos los vehiculos
     * que se encuentran actualmente dentro del parqueadero
     * @return lista de vehiculos con estado DENTRO
     */

    public List<Vehiculo> consultarVehiculosDentro(){
        List<Vehiculo> vehiculosDentro = new ArrayList<>();
        for(Vehiculo vehiculo : listVehiculos){
            if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.DENTRO){
                vehiculosDentro.add(vehiculo);
            }
        }
        return vehiculosDentro;
    }


    /**
     * Metodo que permite calcular cuanto debe pagar un vehiculo al salir,
     * buscando su tarifa segun el tipo de vehiculo y aplicando descuento
     * si el vehiculo pertenece a un usuario registrado del parqueadero
     * @param vehiculo del que se quiere calcular el valor a pagar
     * @param horas cantidad de horas que estuvo estacionado
     * @return valor total a pagar por el tiempo estacionado
     */

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

    /**
     * Metodo que permite simular cuanto le costaria la salida a un vehiculo
     * sin registrar la salida oficialmente, calculando las horas y el valor estimado.
     * Lanza excepcion si el vehiculo no existe o si ya salio
     * @param placa del vehiculo a simular
     * @return texto con el tiempo total y el valor estimado a pagar
     */

    public String simularSalida(String placa){
        String respuesta = "";
        Vehiculo vehiculo = obtenerVehiculo(placa);
        if(vehiculo == null){
            throw new VehiculoNoEncontradoException(placa);
        } else {
            if(vehiculo.getEstadoVehiculo() == EstadoVehiculo.FUERA){
               throw new VehiculoNoIngresoException(placa);
            } else {
                LocalTime horaSalida = LocalTime.now();
                double horas = calcularHoras(vehiculo.getHoraIngreso(), horaSalida);
                double valorPagar = calcularValorPagar(vehiculo, horas);
                respuesta = "Tiempo total: " + horas + " horas, Valor estimado: $" + valorPagar;
            }
        }
        return respuesta;
    }

    /**
     * Metodo que permite generar un texto con la informacion basica de un vehiculo dentro del parqueadero,
     * mostrando la placa, el nombre del conductor y el codigo del espacio si tiene uno asignado
     * @param vehiculo del que se quiere generar el texto
     * @return texto con placa, conductor y espacio del vehiculo
     */

    public String textoVehiculoDentro(Vehiculo vehiculo){
        String respuesta = vehiculo.getPlaca() + " - " + vehiculo.getNombreConductor();
        if(vehiculo.getTheEspacioDelVehiculo() != null){
            respuesta = respuesta + " - " + vehiculo.getTheEspacioDelVehiculo().getCodigo();
        }
        return respuesta;
    }


//-----------------------------CRUD USUARIOPARQUEADERO-----------------------------------------------------------------

    /**
     * Metodo que permite buscar si un usuario del parqueadero ya esta registrado en el sistema
     * segun su identificacion, recorriendo la lista de usuarios
     * @param identificacion del usuario a buscar
     * @return true si el usuario existe, false si no existe
     */

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

    /**
     * Metodo que permite registrar un nuevo usuario del parqueadero en el sistema,
     * siempre y cuando no exista otro con la misma identificacion
     * @param nombre del usuario a registrar
     * @param identificacion del usuario a registrar
     * @param telefono del usuario a registrar
     * @param correo del usuario a registrar
     * @param tipoUsuarioParqueadero tipo de usuario a registrar
     * @return mensaje indicando si el usuario fue registrado o si ya existia
     */

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

    /**
     * Metodo que permite obtener un objeto UsuarioParqueadero buscandolo por su identificacion
     * @param identificacion del usuario a obtener
     * @return el usuario encontrado, o null si no existe
     */

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


    /**
     * Metodo que permite actualizar los datos de un usuario del parqueadero ya registrado,
     * buscandolo por su identificacion y cambiando su nombre, telefono, correo y tipo
     * @param nuevoNombre nuevo nombre del usuario
     * @param identificacion identificacion del usuario a actualizar
     * @param nuevoTelefono nuevo telefono del usuario
     * @param nuevoCorreo nuevo correo del usuario
     * @param nuevoTipoUsuarioParqueadero nuevo tipo de usuario
     * @return mensaje indicando si fue actualizado o si no fue encontrado
     */

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

    /**
     * Metodo que permite eliminar un usuario del parqueadero del sistema,
     * buscandolo por su identificacion y removiendolo de la lista
     * @param identificacion del usuario a eliminar
     * @return mensaje indicando si fue eliminado o si no fue encontrado
     */

    public String eliminarUsuario(String identificacion){
        String respuesta;
        UsuarioParqueadero up = obtenerUsuarioParqueadero(identificacion);
        if(up == null){
            respuesta = "Usuario no encontrado";
        }else{
            listUsuariosParqueaderos.remove(up);
            respuesta = "Usuario eliminado correctamente";
        }
        return respuesta;
    }

    //---------------------------------CRUD TARIFA----------------------------------------------------------------------------------

    /**
     * Metodo que permite obtener una tarifa buscandola por el tipo de vehiculo
     * @param tipoVehiculo tipo del vehiculo del que se quiere obtener la tarifa
     * @return la tarifa encontrada, o null si no existe
     */

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

    /**
     * Metodo que permite verificar si ya existe una tarifa para un tipo de vehiculo especifico
     * @param tipoVehiculo tipo del vehiculo a verificar
     * @return true si ya existe una tarifa, false si no existe
     */

    public boolean buscarTarifa(TipoVehiculo tipoVehiculo){
        boolean existe = false;
        Tarifa tarifa = obtenerTarifa(tipoVehiculo);
        if(tarifa != null){
            existe = true;
        }
        return existe;
    }

    /**
     * Metodo que permite agregar una nueva tarifa al parqueadero para un tipo de vehiculo,
     * siempre y cuando no exista ya una tarifa para ese tipo
     * @param tipoVehiculo tipo de vehiculo al que se le asignara la tarifa
     * @param valorPorHora valor a cobrar por hora de estacionamiento
     * @param descuento porcentaje de descuento para usuarios registrados
     * @return mensaje indicando si la tarifa fue registrada o si ya existia
     */


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

    /**
     * Metodo que permite actualizar el valor por hora y el descuento
     * de una tarifa ya existente para un tipo de vehiculo
     * @param tipoVehiculo tipo de vehiculo cuya tarifa se quiere actualizar
     * @param nuevoValorHora nuevo valor por hora a asignar
     * @param nuevoDescuento nuevo porcentaje de descuento a asignar
     * @return mensaje indicando si fue actualizada o si no fue encontrada
     */

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

    /**
     * Metodo que permite generar el reporte diario del parqueadero,
     * recorriendo todos los registros del dia, agregandolos al reporte
     * y calculando el promedio de tiempo de estacionamiento
     * @return objeto ReporteDiario con toda la informacion del dia actual
     */

    public ReporteDiario generarReporteDiario(){

        ReporteDiario reporte = new ReporteDiario(LocalDate.now());
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
