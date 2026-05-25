package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OperadorTest {

    @Test
    void loginOperador() {
        Operador op = new Operador("Op", "2", "300", "o@uq.edu.co");
        assertTrue(op.logIn("operador", "123456"));
    }
}
