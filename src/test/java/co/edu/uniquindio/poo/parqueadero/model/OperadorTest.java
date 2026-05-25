package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperadorTest {

    @Test
    public void loginCorrectoTest(){

        Operador operador = new Operador(
                "Juan",
                "123",
                "321",
                "juan@gmail.com"
        );

        boolean resultado = operador.logIn("operador", "123456");
        assertTrue(resultado);
    }

    @Test
    public void loginIncorrectoTest(){

        Operador operador = new Operador(
                "Juan",
                "123",
                "321",
                "juan@gmail.com"
        );

        boolean resultado = operador.logIn("mal", "mal");
        assertFalse(resultado);
    }
}