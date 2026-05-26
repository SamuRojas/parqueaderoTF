package co.edu.uniquindio.poo.parqueadero.exception;

public class EspacioNoDisponibleException extends ParqueaderoException {

    /**
     * Constructor de la clase EspacioNoDisponibleException
     * Lanza un mensaje de error indicando que no hay espacios disponibles
     * para el tipo de vehiculo que intenta ingresar
     */


    public EspacioNoDisponibleException() {

        super("no hay espacios disponibles para este tipo de vehiculo");
    }
}
