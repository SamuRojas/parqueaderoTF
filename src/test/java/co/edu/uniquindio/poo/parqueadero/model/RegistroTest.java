package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistroTest {

    @Test
    void crearRegistro() {
        Registro r = new Registro("ABC", LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT, 5000, 2);
        assertEquals(5000, r.getValorPagado());
    }
}
