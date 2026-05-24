package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Registro {
    private String placa;
    private LocalDate fecha;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private double valorPagado;
    private double horasEstacionadas;

    public Registro(String placa, LocalDate fecha, LocalTime horaIngreso, LocalTime horaSalida, double valorPagado, double horasEstacionadas) {
        this.placa = placa;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.valorPagado = valorPagado;
        this.horasEstacionadas = horasEstacionadas;
    }




    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }
    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
    public double getValorPagado() {
        return valorPagado;
    }
    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }
    public double getHorasEstacionadas() {
        return horasEstacionadas;
    }
    public void setHorasEstacionadas(double horasEstacionadas) {
        this.horasEstacionadas = horasEstacionadas;
    }


    @Override
    public String toString() {
        return "Registro{" +
                "placa='" + placa + '\'' +
                ", fecha=" + fecha +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", valorPagado=" + valorPagado +
                ", horasEstacionadas=" + horasEstacionadas +
                '}';
    }
}
