package co.edu.uniquindio.poo.parqueadero.exception;

public class EspacioNoDisponibleException extends ParqueaderoException {
    public EspacioNoDisponibleException() {

        super("no hay espacios disponibles para este tipo de vehiculo");
    }
}
