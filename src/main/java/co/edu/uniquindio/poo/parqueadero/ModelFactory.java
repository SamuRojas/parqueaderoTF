package co.edu.uniquindio.poo.parqueadero;

import co.edu.uniquindio.poo.parqueadero.model.*;

/**
 * Clase que crea una sola instancia del parqueadero y los usuarios del sistema.
 */
public class ModelFactory {

    private static ModelFactory instancia;
    private final Parqueadero parqueadero;
    private final Administrador administrador;
    private final Operador operador;

    private ModelFactory() {
        parqueadero = new Parqueadero("Parqueadero Central UQ", 20, TipoParqueadero.CARRO);
        administrador = new Administrador("Admin Sistema", "1001", "3000000000",
                "admin@uq.edu.co", "SamuelRojas", "123456");
        operador = new Operador("Operador Turno", "2001", "3000000001", "operador@uq.edu.co");
        cargarDatosIniciales();
    }

    public static ModelFactory getInstancia() {
        if (instancia == null) {
            instancia = new ModelFactory();
        }
        return instancia;
    }

    private void cargarDatosIniciales() {
        parqueadero.agregarEspacio("C01", TipoEspacio.CARRO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("C02", TipoEspacio.CARRO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("C03", TipoEspacio.CARRO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("M01", TipoEspacio.MOTO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("M02", TipoEspacio.MOTO, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("B01", TipoEspacio.BICICLETA, EstadoEspacio.DISPONIBLE);
        parqueadero.agregarEspacio("B02", TipoEspacio.BICICLETA, EstadoEspacio.DISPONIBLE);

        parqueadero.agregarTarifa(TipoVehiculo.CARRO, 3000, 0);
        parqueadero.agregarTarifa(TipoVehiculo.MOTO, 2000, 0);
        parqueadero.agregarTarifa(TipoVehiculo.BICICLETA, 1000, 5);

        parqueadero.registrarNuevoUsuario("Ana Estudiante", "1101", "3100000000",
                "ana@uq.edu.co", TipoUsuarioParqueadero.ESTUDIANTE);
        parqueadero.registrarNuevoUsuario("Profesor Luis", "1102", "3100000001",
                "luis@uq.edu.co", TipoUsuarioParqueadero.DOCENTE);
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public Operador getOperador() {
        return operador;
    }
}
