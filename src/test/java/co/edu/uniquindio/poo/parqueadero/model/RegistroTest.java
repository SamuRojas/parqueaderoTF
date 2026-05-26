package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroTest {

    /**
     * Prueba que verifica que un registro se crea correctamente
     * con la placa, el valor pagado y las horas estacionadas indicadas
     */

    @Test
    public void crearRegistroTest(){

        Registro registro = new Registro(
                "ABC123",
                LocalDate.now(),
                LocalTime.of(8,0),
                LocalTime.of(10,0),
                10000,
                2);

        assertEquals("ABC123", registro.getPlaca());
        assertEquals(10000, registro.getValorPagado());
        assertEquals(2,
                registro.getHorasEstacionadas());
    }

    /**
     * Prueba que verifica que el valor pagado de un registro
     * se puede modificar correctamente despues de haberlo creado
     */

    @Test
    public void modificarValorPagadoTest(){

        Registro registro = new Registro(
                "ABC123",
                LocalDate.now(),
                LocalTime.now(),
                null,
                0,
                0);

        registro.setValorPagado(15000);
        assertEquals(15000, registro.getValorPagado());
    }
}