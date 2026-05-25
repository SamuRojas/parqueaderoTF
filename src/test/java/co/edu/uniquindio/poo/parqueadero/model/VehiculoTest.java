package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehiculoTest {

    @Test
    void crearVehiculo() {
        Vehiculo v = new Vehiculo("ABC", "Pedro", "99", EstadoVehiculo.FUERA,
                TipoVehiculo.MOTO, LocalTime.NOON, null, null, null);
        assertEquals("ABC", v.getPlaca());
        assertEquals(EstadoVehiculo.FUERA, v.getEstadoVehiculo());
    }
}
