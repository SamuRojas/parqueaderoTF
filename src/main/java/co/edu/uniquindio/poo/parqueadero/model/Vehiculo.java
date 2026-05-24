package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    //Atributos
    private String placa, nombreConductor, identificacionConductor;
    private EstadoVehiculo estadoVehiculo;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private TipoVehiculo tipoVehiculo;

    //Relaciones

    private UsuarioParqueadero theUsuarioDelVehiculo;
    private Espacio theEspacioDelVehiculo;
    private List<Registro> listRegistroDelVehiculo;

    //Constructor

    public Vehiculo (String placa, String nombreConductor, String identificacionConductor, EstadoVehiculo estadoVehiculo, TipoVehiculo tipoVehiculo, LocalTime horaIngreso, LocalTime horaSalida, UsuarioParqueadero usuarioDelVehiculo, Espacio espacioDelVehiculo){
        this.placa = placa;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;

        this.estadoVehiculo = estadoVehiculo;
        this.tipoVehiculo = tipoVehiculo;

        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;

        this.theUsuarioDelVehiculo = usuarioDelVehiculo;
        this.theEspacioDelVehiculo = espacioDelVehiculo;
        this.listRegistroDelVehiculo = new ArrayList<>();

    }

    public String getPlaca() {
        return placa;
    }
    public String getNombreConductor() {
        return nombreConductor;
    }
    public String getIdentificacionConductor() {
        return identificacionConductor;
    }
    public EstadoVehiculo getEstadoVehiculo() {
        return estadoVehiculo;
    }
    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }
    public UsuarioParqueadero getTheUsuarioDelVehiculo() {
        return theUsuarioDelVehiculo;
    }
    public Espacio getTheEspacioDelVehiculo() {
        return theEspacioDelVehiculo;
    }
    public List<Registro> getListRegistroDelVehiculo() {
        return listRegistroDelVehiculo;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }
    public void setIdentificacionConductor(String identificacionConductor) {this.identificacionConductor = identificacionConductor;}
    public void setEstadoVehiculo(EstadoVehiculo estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }
    public void setHoraIngreso(LocalTime horaIngreso) {this.horaIngreso = horaIngreso;}
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    public void setTheUsuarioDelVehiculo(UsuarioParqueadero theUsuarioDelVehiculo) {this.theUsuarioDelVehiculo = theUsuarioDelVehiculo;}
    public void setTheEspacioDelVehiculo(Espacio theEspacioDelVehiculo) {this.theEspacioDelVehiculo = theEspacioDelVehiculo;}
    public void setListRegistroDelVehiculo(List<Registro> listRegistroDelVehiculo) {this.listRegistroDelVehiculo = listRegistroDelVehiculo;}

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", nombreConductor='" + nombreConductor + '\'' +
                ", identificacionConductor='" + identificacionConductor + '\'' +
                ", estadoVehiculo=" + estadoVehiculo +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", tipoVehiculo=" + tipoVehiculo +
                ", thePersonaDelVehiculo=" + theUsuarioDelVehiculo +
                ", theEspacioDelVehiculo=" + theEspacioDelVehiculo +
                ", listRegistroDelVehiculo=" + listRegistroDelVehiculo +
                '}';
    }
}
