package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TarifaTest {

    /**
     * Prueba que verifica que el calculo del valor a pagar es correcto
     * cuando el vehiculo no tiene descuento aplicado
     */

    @Test
    public void calcularValorSinDescuentoTest(){

        Tarifa tarifa = new Tarifa(
                TipoVehiculo.CARRO,
                5000,
                10);
        double resultado = tarifa.calcularValor(2, false);
        assertEquals(10000, resultado);
    }


    /**
     * Prueba que verifica que el calculo del valor a pagar es correcto
     * cuando el vehiculo si tiene descuento aplicado
     */

    @Test
    public void calcularValorConDescuentoTest(){

        Tarifa tarifa = new Tarifa(
                TipoVehiculo.CARRO,
                5000,
                10);

        double resultado = tarifa.calcularValor(2, true);
        assertEquals(9000, resultado);
    }
}