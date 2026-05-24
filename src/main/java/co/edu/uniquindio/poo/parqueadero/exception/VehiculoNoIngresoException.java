package co.edu.uniquindio.poo.parqueadero.exception;

public class VehiculoNoIngresoException extends RuntimeException {
    public VehiculoNoIngresoException(String placa) {
        super("el vehiculo con placa" + placa + "no ingreso o ya salio");
    }
}
