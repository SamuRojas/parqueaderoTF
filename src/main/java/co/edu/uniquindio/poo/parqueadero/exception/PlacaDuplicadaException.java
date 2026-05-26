package co.edu.uniquindio.poo.parqueadero.exception;

public class PlacaDuplicadaException extends ParqueaderoException {

    /**
     * Constructor de la clase PlacaDuplicadaException
     * Lanza un mensaje de error indicando que la placa ya se encuentra dentro del parqueadero
     * @param placa del vehiculo que ya esta registrado como dentro del parqueadero
     */

    public PlacaDuplicadaException(String placa) {
        super("la placa" + placa + " ya esta dentro del parqueadero");
    }
}
