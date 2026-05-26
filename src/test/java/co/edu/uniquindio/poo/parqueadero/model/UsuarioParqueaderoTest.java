package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioParqueaderoTest {

    /**
     * Prueba que verifica que al asignar un vehiculo a un usuario del parqueadero,
     * la relacion queda correctamente establecida en ambos lados
     */

    @Test
    public void asignarVehiculoTest(){

        UsuarioParqueadero usuario = new UsuarioParqueadero(
                "Samuel",
                "123",
                "321",
                "correo@gmail.com",
                TipoUsuarioParqueadero.ESTUDIANTE);

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

        usuario.asignarVehiculo(vehiculo);
        assertEquals(usuario, vehiculo.getTheUsuarioDelVehiculo());
    }
}