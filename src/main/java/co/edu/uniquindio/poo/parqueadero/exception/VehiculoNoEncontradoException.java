package co.edu.uniquindio.poo.parqueadero.exception;

public class VehiculoNoEncontradoException extends ParqueaderoException {
    public VehiculoNoEncontradoException(String placa) {
        super("No se encontro el vehiculo con placa" + placa);
    }
}
