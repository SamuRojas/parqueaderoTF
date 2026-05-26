package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDateTime;

public class Tarifa {
  //Atributos

    private TipoVehiculo tipoVehiculo;
    private double valorPorHora;
    private double descuento;


    /**
     * Constructor de la clase Tarifa
     * @param tipoVehiculo de Tarifa
     * @param valorPorHora de tarifa
     * @param descuento de tarifa
     */

    public Tarifa (TipoVehiculo tipoVehiculo, double valorPorHora, double descuento){
        this.tipoVehiculo = tipoVehiculo;
        this.descuento = descuento;
        this.valorPorHora = valorPorHora;

    }

    /**
     * Metodo que permite calcular el valorTotal del vehiculo a pagar segun su tarifa, horas quedadas y si cuenta o no con descuento
     * @return valorTotal ah pagar por el vehiculo
     */

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
