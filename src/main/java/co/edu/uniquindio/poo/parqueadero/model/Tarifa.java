package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDateTime;

public class Tarifa {
  //Atributos

    private TipoVehiculo tipoVehiculo;
    private double valorPorHora;
    private double descuento;


    public Tarifa (TipoVehiculo tipoVehiculo, double valorPorHora, double descuento){
        this.tipoVehiculo = tipoVehiculo;
        this.descuento = descuento;
        this.valorPorHora = valorPorHora;

    }

    public double calcularValor (TipoVehiculo tipoVehiculo, double descuento)


    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }
}
