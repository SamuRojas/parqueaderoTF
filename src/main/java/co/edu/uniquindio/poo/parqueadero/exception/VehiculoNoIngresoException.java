package co.edu.uniquindio.poo.parqueadero.exception;

public class VehiculoNoIngresoException extends RuntimeException {

    /**
     * Constructor de la clase VehiculoNoIngresoException
     * Lanza un mensaje de error indicando que el vehiculo no ingreso al parqueadero
     * o que ya salio del mismo
     * @param placa del vehiculo que no se encuentra dentro del parqueadero
     */

    public VehiculoNoIngresoException(String placa) {
        super("el vehiculo con placa" + placa + "no ingreso o ya salio");
    }
}
