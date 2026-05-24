package co.edu.uniquindio.poo.parqueadero.exception;

public class PlacaDuplicadaException extends ParqueaderoException {
    public PlacaDuplicadaException(String placa) {
        super("la placa" + placa + " ya esta dentro del parqueadero");
    }
}
