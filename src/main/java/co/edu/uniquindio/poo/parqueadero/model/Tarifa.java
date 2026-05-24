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

    public double calcularValor(double horas, boolean tieneDescuento){
        double valorTotal = horas * valorPorHora;
        if(tieneDescuento){
            double valorDescuento = valorTotal * descuento /100;
            valorTotal = valorTotal - valorDescuento;
        }
        return valorTotal;
    }


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

    @Override
    public String toString() {
        return "Tarifa{" +
                "tipoVehiculo=" + tipoVehiculo +
                ", valorPorHora=" + valorPorHora +
                ", descuento=" + descuento +
                '}';
    }
}
