package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EspacioTest {


    /**
     * Prueba que verifica que un espacio se crea correctamente
     * con el codigo y tipo de espacio que se le asignan
     */

    @Test
    public void crearEspacioTest(){

        Espacio espacio = new Espacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

        assertEquals("A1", espacio.getCodigo());
        assertEquals(TipoEspacio.CARRO, espacio.getTipoEspacio());
    }

    /**
     * Prueba que verifica que el estado de un espacio se puede cambiar correctamente
     * despues de haberlo creado con un estado inicial
     */

    @Test
    public void modificarEstadoEspacioTest(){

        Espacio espacio = new Espacio(
                "A1",
                TipoEspacio.CARRO,
                EstadoEspacio.DISPONIBLE);

        espacio.setEstadoEspacio(EstadoEspacio.OCUPADO);

        assertEquals(EstadoEspacio.OCUPADO, espacio.getEstadoEspacio());
    }

    /**
     * Prueba que verifica que se puede asignar un vehiculo a un espacio
     * y que la relacion queda correctamente establecida
     */

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