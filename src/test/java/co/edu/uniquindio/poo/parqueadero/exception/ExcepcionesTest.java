package co.edu.uniquindio.poo.parqueadero.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExcepcionesTest {

    /**
     * Prueba que verifica que la excepcion de placa duplicada
     * incluye la placa del vehiculo en su mensaje de error
     */

    @Test
    public void placaDuplicadaTest() {

        PlacaDuplicadaException exception = new PlacaDuplicadaException("ABC123");
        assertTrue(exception.getMessage().contains("ABC123"));
    }

    /**
     * Prueba que verifica que la excepcion de espacio no disponible
     * incluye el texto esperado en su mensaje de error
     */

    @Test
    public void espacioNoDisponibleTest() {

        EspacioNoDisponibleException exception = new EspacioNoDisponibleException();
        assertTrue(exception.getMessage().contains("no hay espacios"));
    }

    /**
     * Prueba que verifica que la excepcion de vehiculo no encontrado
     * incluye la placa del vehiculo en su mensaje de error
     */

    @Test
    public void vehiculoNoEncontradoTest() {

        VehiculoNoEncontradoException exception = new VehiculoNoEncontradoException("XYZ789");
        assertTrue(exception.getMessage().contains("XYZ789"));
    }

    /**
     * Prueba que verifica que la excepcion de vehiculo no ingresado
     * incluye la placa del vehiculo en su mensaje de error
     */

    @Test
    public void vehiculoNoIngresoTest() {

        VehiculoNoIngresoException exception = new VehiculoNoIngresoException("AAA111");
        assertTrue(exception.getMessage().contains("AAA111"));
    }
}