package co.edu.uniquindio.poo.parqueadero.exception;

public class VehiculoNoEncontradoException extends ParqueaderoException {
    /**
     * Constructor de la clase VehiculoNoEncontradoException
     * Lanza un mensaje de error indicando la placa del vehiculo que no fue encontrado
     * @param placa del vehiculo que no se encontro en el sistema
     */
    public VehiculoNoEncontradoException(String placa) {
        super("No se encontro el vehiculo con placa" + placa);
    }
}
