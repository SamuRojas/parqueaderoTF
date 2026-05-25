package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {

    private Parqueadero parqueadero;

    @BeforeEach
    public void setUp(){

        parqueadero = new Parqueadero(
                "UQ",
                100,
                TipoParqueadero.CARRO
        );
    }

    @Test
    public void agregarEspacioTest(){

        parqueadero.agregarEspacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE
        );

        assertTrue(parqueadero.buscarEspacio("A1"));
    }

    @Test
    public void registrarVehiculoTest(){

        parqueadero.registrarNuevoVehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                LocalTime.now(),
                TipoVehiculo.CARRO
        );

        assertTrue(parqueadero.buscarVehiculo("ABC123"));
    }

    @Test
    public void registrarUsuarioTest(){

        parqueadero.registrarNuevoUsuario(
                "Samuel",
                "123",
                "321",
                "correo@gmail.com",
                TipoUsuarioParqueadero.ESTUDIANTE
        );

        assertTrue(parqueadero.buscarUsuarioParqueadero("123"));
    }

    @Test
    public void agregarTarifaTest(){

        parqueadero.agregarTarifa(
                TipoVehiculo.CARRO,
                5000,
                10
        );

        assertTrue(parqueadero.buscarTarifa(TipoVehiculo.CARRO));
    }

    @Test
    public void calcularHorasTest(){

        double horas = parqueadero.calcularHoras(
                LocalTime.of(8,0),
                LocalTime.of(10,0)
        );

        assertEquals(2, horas);
    }

    @Test
    public void asignarEspacioVehiculoTest(){

        parqueadero.agregarEspacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE
        );

        parqueadero.registrarNuevoVehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                LocalTime.now(),
                TipoVehiculo.CARRO
        );

        String respuesta = parqueadero.asignarEspacioPorPlaca(
                "ABC123",
                "A1"
        );

        assertEquals("Espacio asignado correctamente", respuesta);
    }
}