package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdministradorTest {

    @Test
    void loginCorrecto() {
        Administrador admin = new Administrador("Admin", "1", "300", "a@uq.edu.co", "u", "c");
        assertTrue(admin.logIn("SamuelRojas", "123456"));
    }

    @Test
    void loginIncorrecto() {
        Administrador admin = new Administrador("Admin", "1", "300", "a@uq.edu.co", "u", "c");
        assertFalse(admin.logIn("otro", "mal"));
    }
}
