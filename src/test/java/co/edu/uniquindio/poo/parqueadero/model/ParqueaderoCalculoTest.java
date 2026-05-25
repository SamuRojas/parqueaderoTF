package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParqueaderoCalculoTest {

    private Parqueadero parqueadero;

    @BeforeEach
    void setUp() {
        parqueadero = new Parqueadero("Test", 5, TipoParqueadero.CARRO);
        parqueadero.agregarTarifa(TipoVehiculo.CARRO, 1000, 0);
    }

    @Test
    void calcularHorasBasico() {
        LocalTime ingreso = LocalTime.of(10, 0);
        LocalTime salida = LocalTime.of(12, 30);
        double horas = parqueadero.calcularHoras(ingreso, salida);
        assertTrue(horas >= 2);
    }

    @Test
    void calcularValorConDescuentoUsuario() {
        Vehiculo v = new Vehiculo("A1", "Juan", "1", EstadoVehiculo.DENTRO,
                TipoVehiculo.CARRO, LocalTime.NOON, null, null, null);
        UsuarioParqueadero u = new UsuarioParqueadero("Ana", "1101", "300", "a@uq.edu.co",
                TipoUsuarioParqueadero.ESTUDIANTE);
        u.asignarVehiculo(v);
        double valor = parqueadero.calcularValorPagar(v, 2);
        assertTrue(valor > 0);
    }
}
