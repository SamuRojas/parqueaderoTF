package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculoTest {

    /**
     * Prueba que verifica que un vehiculo se crea correctamente
     * con la placa, el nombre del conductor y el tipo de vehiculo indicados
     */

    @Test
    public void crearVehiculoTest(){

        Vehiculo vehiculo = new Vehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                TipoVehiculo.CARRO,
                LocalTime.now(),
                null,
                null,
                null);

        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals("Samuel", vehiculo.getNombreConductor());
        assertEquals(TipoVehiculo.CARRO, vehiculo.getTipoVehiculo());
    }

    /**
     * Prueba que verifica que se puede asignar un espacio a un vehiculo
     * y que la relacion queda correctamente establecida
     */

    @Test
    public void asignarEspacioVehiculoTest(){

        Vehiculo vehiculo = new Vehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                TipoVehiculo.CARRO,
                LocalTime.now(),
                null,
                null,
                null);

        Espacio espacio = new Espacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

        vehiculo.setTheEspacioDelVehiculo(espacio);

        assertEquals(espacio, vehiculo.getTheEspacioDelVehiculo());
    }

    /**
     * Prueba que verifica que el estado de un vehiculo se puede cambiar correctamente
     * despues de haberlo creado con un estado inicial
     */

    @Test
    public void cambiarEstadoVehiculoTest(){

        Vehiculo vehiculo = new Vehiculo(
                "ABC123",
                "Samuel",
                "123",
                EstadoVehiculo.DENTRO,
                TipoVehiculo.CARRO,
                LocalTime.now(),
                null,
                null,
                null);

        vehiculo.setEstadoVehiculo(EstadoVehiculo.FUERA);
        assertEquals(EstadoVehiculo.FUERA, vehiculo.getEstadoVehiculo());
    }
}