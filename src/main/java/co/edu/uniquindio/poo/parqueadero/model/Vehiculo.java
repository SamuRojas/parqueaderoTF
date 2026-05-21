package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Vehiculo {
    //Atributos
    private String placa, nombreConductor, identificacionConductor;
    private EstadoVehiculo estadoVehiculo;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private TipoVehiculo tipoVehiculo;

    //Relaciones

    private Persona thePersonaDelVehiculo;
    private Espacio theEspacioDelVehiculo;
    private List<Registro> listRegistroDelVehiculo;

    //Constructor

    public Vehiculo (String placa, String nombreConductor, String identificacionConductor, EstadoVehiculo estadoVehiculo, TipoVehiculo tipoVehiculo, LocalTime horaIngreso, LocalTime horaSalida, Persona personaDelVehiculo, Espacio espacioDelVehiculo){
        this.placa = placa;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;

        this.estadoVehiculo = estadoVehiculo;
        this.tipoVehiculo = tipoVehiculo;

        this.horaIngreso = LocalTime.now();
        this.horaSalida = LocalTime.now();

        this.thePersonaDelVehiculo = personaDelVehiculo;
        this.theEspacioDelVehiculo = espacioDelVehiculo;

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

    public Persona getThePersonaDelVehiculo() {
        return thePersonaDelVehiculo;
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

    public void setThePersonaDelVehiculo(Persona thePersonaDelVehiculo) {this.thePersonaDelVehiculo = thePersonaDelVehiculo;}

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
                ", thePersonaDelVehiculo=" + thePersonaDelVehiculo +
                ", theEspacioDelVehiculo=" + theEspacioDelVehiculo +
                ", listRegistroDelVehiculo=" + listRegistroDelVehiculo +
                '}';
    }
}
