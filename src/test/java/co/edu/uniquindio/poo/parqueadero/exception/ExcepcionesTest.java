package co.edu.uniquindio.poo.parqueadero.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExcepcionesTest {

    @Test
    public void placaDuplicadaTest() {

        PlacaDuplicadaException exception = new PlacaDuplicadaException("ABC123");
        assertTrue(exception.getMessage().contains("ABC123"));
    }

    @Test
    public void espacioNoDisponibleTest() {

        EspacioNoDisponibleException exception = new EspacioNoDisponibleException();
        assertTrue(exception.getMessage().contains("no hay espacios"));
    }

    @Test
    public void vehiculoNoEncontradoTest() {

        VehiculoNoEncontradoException exception = new VehiculoNoEncontradoException("XYZ789");
        assertTrue(exception.getMessage().contains("XYZ789"));
    }

    @Test
    public void vehiculoNoIngresoTest() {

        VehiculoNoIngresoException exception = new VehiculoNoIngresoException("AAA111");
        assertTrue(exception.getMessage().contains("AAA111"));
    }
}