package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteDiarioTest {

    @Test
    public void agregarRegistroTest(){

        ReporteDiario reporte = new ReporteDiario(LocalDate.now());

        Registro registro = new Registro(
                "ABC123",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now(),
                10000,
                6
        );

        reporte.agregarRegistro(registro);

        assertEquals(1, reporte.getTotalIngresos());
    }

    @Test
    public void calcularPromedioTest(){

        ReporteDiario reporte = new ReporteDiario(LocalDate.now());

        reporte.agregarRegistro(new Registro(
                "ABC123",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now(),
                10000,
                2
        ));

        reporte.agregarRegistro(new Registro(
                "XYZ123",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now(),
                10000,
                4
        ));

        reporte.calcularPromedio();

        assertEquals(3, reporte.getTiempoPromedioHoras());
    }
}