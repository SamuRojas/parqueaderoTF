package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {

    private Parqueadero parqueadero;

    /**
     * Metodo que se ejecuta antes de cada prueba para crear un parqueadero
     * nuevo con datos base, asegurando que cada test parte de un estado limpio
     */

    @BeforeEach
    public void setUp(){

        parqueadero = new Parqueadero(
                "UQ",
                100,
                TipoParqueadero.CARRO);
    }

    /**
     * Prueba que verifica que un espacio se agrega correctamente al parqueadero
     * y que puede ser encontrado luego por su codigo
     */

    @Test
    public void agregarEspacioTest(){

        parqueadero.agregarEspacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);
        assertTrue(parqueadero.buscarEspacio("A1"));
    }

    /**
     * Prueba que verifica que un vehiculo se registra correctamente en el parqueadero
     * y que puede ser encontrado luego por su placa
     */

    @Test
    public void registrarVehiculoTest(){

        parqueadero.registrarNuevoVehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                LocalTime.now(),
                TipoVehiculo.CARRO);
        assertTrue(parqueadero.buscarVehiculo("ABC123"));
    }

    /**
     * Prueba que verifica que un usuario del parqueadero se registra correctamente
     * y que puede ser encontrado luego por su identificacion
     */

    @Test
    public void registrarUsuarioTest(){

        parqueadero.registrarNuevoUsuario(
                "Samuel",
                "123",
                "321",
                "correo@gmail.com",
                TipoUsuarioParqueadero.ESTUDIANTE);
        assertTrue(parqueadero.buscarUsuarioParqueadero("123"));
    }

    /**
     * Prueba que verifica que una tarifa se agrega correctamente al parqueadero
     * y que puede ser encontrada luego por el tipo de vehiculo
     */

    @Test
    public void agregarTarifaTest(){

        parqueadero.agregarTarifa(
                TipoVehiculo.CARRO,
                5000,
                10);
        assertTrue(parqueadero.buscarTarifa(TipoVehiculo.CARRO));
    }

    /**
     * Prueba que verifica que el calculo de horas entre hora de ingreso y hora de salida
     * retorna el valor correcto
     */

    @Test
    public void calcularHorasTest(){

        double horas = parqueadero.calcularHoras(
                LocalTime.of(8,0),
                LocalTime.of(10,0));
        assertEquals(2, horas);
    }


    /**
     * Prueba que verifica que se puede asignar un espacio a un vehiculo correctamente,
     * usando la placa del vehiculo y el codigo del espacio
     */

    @Test
    public void asignarEspacioVehiculoTest(){

        parqueadero.agregarEspacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

        parqueadero.registrarNuevoVehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                LocalTime.now(),
                TipoVehiculo.CARRO);

        String respuesta = parqueadero.asignarEspacioPorPlaca(
                "ABC123",
                "A1");
        assertEquals("Espacio asignado correctamente", respuesta);
    }
}