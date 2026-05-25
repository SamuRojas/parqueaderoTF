package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EspacioTest {

    @Test
    public void crearEspacioTest(){

        Espacio espacio = new Espacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

        assertEquals("A1", espacio.getCodigo());
        assertEquals(TipoEspacio.CARRO, espacio.getTipoEspacio());
    }

    @Test
    public void modificarEstadoEspacioTest(){

        Espacio espacio = new Espacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

        espacio.setEstadoEspacio(EstadoEspacio.OCUPADO);

        assertEquals(EstadoEspacio.OCUPADO, espacio.getEstadoEspacio());
    }

    @Test
    public void asignarVehiculoEspacioTest(){

        Espacio espacio = new Espacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

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
        espacio.setTheVehiculo(vehiculo);
        assertEquals(vehiculo,
                espacio.getTheVehiculo());
    }
}