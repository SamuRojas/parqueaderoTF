package co.edu.uniquindio.poo.parqueadero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteDiarioTest {

    /**
     * Prueba que verifica que al agregar un registro al reporte diario,
     * el total de ingresos aumenta en uno correctamente
     */
    @Test
    public void agregarRegistroTest(){

        ReporteDiario reporte = new ReporteDiario(LocalDate.now());

        Registro registro = new Registro(
                "ABC123",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now(),
                10000,
                6);

        reporte.agregarRegistro(registro);
        assertEquals(1, reporte.getTotalIngresos());
    }

    /**
     * Prueba que verifica que el calculo del promedio de horas estacionadas
     * da el resultado correcto al tener dos registros con distintas horas
     */

    @Test
    public void calcularPromedioTest(){

        ReporteDiario reporte = new ReporteDiario(LocalDate.now());

        reporte.agregarRegistro(new Registro(
                "ABC123",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now(),
                10000,
                2));

        reporte.agregarRegistro(new Registro(
                "XYZ123",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now(),
                10000,
                4));

        reporte.calcularPromedio();
        assertEquals(3, reporte.getTiempoPromedioHoras());
    }
}