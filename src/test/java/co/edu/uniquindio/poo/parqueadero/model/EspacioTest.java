package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EspacioTest {

    @Test
    void crearEspacio() {
        Espacio e = new Espacio("M01", TipoEspacio.MOTO, EstadoEspacio.DISPONIBLE);
        assertEquals("M01", e.getCodigo());
        assertEquals(EstadoEspacio.DISPONIBLE, e.getEstadoEspacio());
    }
}
