package co.edu.uniquindio.poo.parqueadero.exception;

public class ParqueaderoException extends RuntimeException {
/**
 * Constructor de la clase ParqueaderoException
 * @param message mensaje que describe el error ocurrido en el parqueadero
 */

    public ParqueaderoException(String message) {
        super(message);
    }
}
