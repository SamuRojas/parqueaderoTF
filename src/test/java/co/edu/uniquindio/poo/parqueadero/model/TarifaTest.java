package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifaTest {

    @Test
    void calcularValorConDescuento() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.CARRO, 1000, 10);
        double total = tarifa.calcularValor(2);
        assertEquals(1800, total, 0.01);
    }
}
