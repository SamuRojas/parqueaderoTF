package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    /**
     * Prueba que verifica que el login del administrador es exitoso
     * cuando se ingresan el usuario y la contrasena correctos
     */

    @Test
    public void loginCorrectoTest() {
        Administrador admin = new Administrador(
                "Samuel",
                "123",
                "321",
                "samuel@gmail.com",
                "SamuelRojas",
                "123456");
        boolean resultado = admin.logIn("SamuelRojas", "123456");
        assertTrue(resultado);
    }

    /**
     * Prueba que verifica que el login del administrador falla
     * cuando se ingresan usuario y contrasena incorrectos
     */

    @Test
    public void loginIncorrectoTest() {
        Administrador admin = new Administrador(
                "Samuel",
                "123",
                "321",
                "samuel@gmail.com",
                "SamuelRojas",
                "123456");
        boolean resultado = admin.logIn("malo", "malo");
        assertFalse(resultado);
    }
}