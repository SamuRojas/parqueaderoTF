package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReporteDiarioTest {

    @Test
    void generarReporte() {
        ReporteDiario reporte = new ReporteDiario(LocalDate.now());
        Registro r = new Registro("X1", LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT, 4000, 3);
        reporte.agregarRegistro(r, 2);
        ArrayList<Registro> lista = new ArrayList<>();
        lista.add(r);
        reporte.calcularPromedio(lista);
        assertTrue(reporte.generarTexto().contains("Total vehículos"));
    }
}
