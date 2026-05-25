package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioParqueaderoTest {

    @Test
    void registrarUsuario() {
        UsuarioParqueadero u = new UsuarioParqueadero("Ana", "1101", "300", "a@uq.edu.co",
                TipoUsuarioParqueadero.ESTUDIANTE);
        assertEquals(TipoUsuarioParqueadero.ESTUDIANTE, u.getTipoUsuarioParqueadero());
    }
}
