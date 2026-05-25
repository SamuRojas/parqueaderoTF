package co.edu.uniquindio.poo.parqueadero.model;

import co.edu.uniquindio.poo.parqueadero.exception.EspacioNoDisponibleException;
import co.edu.uniquindio.poo.parqueadero.exception.PlacaDuplicadaException;
import co.edu.uniquindio.poo.parqueadero.exception.VehiculoNoIngresoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParqueaderoTest {

    private Parqueadero parqueadero;

    @BeforeEach
    void setUp() {
        parqueadero = new Parqueadero("Test", 10, TipoParqueadero.CARRO);
        parqueadero.agregarEspacio("C01", TipoEspacio.CARRO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("M01", TipoEspacio.MOTO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarTarifa(TipoVehiculo.CARRO, 3000, 0);
        parqueadero.agregarTarifa(TipoVehiculo.MOTO, 2000, 0);
    }

    @Test
    void agregarEspacioDuplicado() {
        String r = parqueadero.agregarEspacio("C01", TipoEspacio.CARRO, EstadoEspacio.DISPONIBLE);
        assertTrue(r.contains("ya existe"));
    }

    @Test
    void registrarIngresoYPlacaDuplicada() throws Exception {
        parqueadero.registrarIngreso("ABC123", "Juan", "1", TipoVehiculo.CARRO);
        assertThrows(PlacaDuplicadaException.class, () ->
                parqueadero.registrarIngreso("ABC123", "Juan", "1", TipoVehiculo.CARRO));
    }

    @Test
    void sinEspaciosDisponibles() throws Exception {
        parqueadero.registrarIngreso("A1", "Ana", "2", TipoVehiculo.CARRO);
        assertThrows(EspacioNoDisponibleException.class, () ->
                parqueadero.registrarIngreso("B2", "Luis", "3", TipoVehiculo.CARRO));
    }

    @Test
    void salidaSinIngreso() {
        parqueadero.registrarNuevoVehiculo("ZZZ999", "Luis", "9", EstadoVehiculo.FUERA,
                java.time.LocalTime.now(), TipoVehiculo.CARRO);
        assertThrows(VehiculoNoIngresoException.class, () ->
                parqueadero.registrarSalida("ZZZ999"));
    }

    @Test
    void consultarInformacionVehiculo() throws Exception {
        parqueadero.registrarIngreso("INFO1", "Pedro", "1101", TipoVehiculo.CARRO, "C01");
        String info = parqueadero.consultarInformacionVehiculo("INFO1");
        assertTrue(info.contains("C01"));
        assertTrue(info.contains("Pedro"));
    }

    @Test
    void ingresoConEspacioEspecifico() throws Exception {
        String r = parqueadero.registrarIngreso("ESP2", "Ana", "99", TipoVehiculo.MOTO, "M01");
        assertTrue(r.contains("M01"));
    }

    @Test
    void registrarSalidaConValor() throws Exception {
        parqueadero.registrarIngreso("XYZ10", "Maria", "5", TipoVehiculo.CARRO);
        String salida = parqueadero.registrarSalida("XYZ10");
        assertTrue(salida.contains("Total a pagar"));
        assertEquals(0, parqueadero.consultarVehiculosDentro().size());
    }
}
