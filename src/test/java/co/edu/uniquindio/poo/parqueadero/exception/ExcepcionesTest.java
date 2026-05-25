package co.edu.uniquindio.poo.parqueadero.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcepcionesTest {

    @Test
    void placaDuplicada() {
        PlacaDuplicadaException e = new PlacaDuplicadaException("ABC");
        assertTrue(e.getMessage().contains("ABC"));
    }

    @Test
    void espacioNoDisponible() {
        EspacioNoDisponibleException e = new EspacioNoDisponibleException();
        assertTrue(e.getMessage().contains("espacios"));
    }

    @Test
    void vehiculoNoEncontrado() {
        VehiculoNoEncontradoException e = new VehiculoNoEncontradoException("X1");
        assertTrue(e.getMessage().contains("X1"));
    }
}

