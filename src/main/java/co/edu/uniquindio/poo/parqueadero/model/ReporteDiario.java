package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReporteDiario {
    private String placa;
    private LocalDate fecha;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private double valorPagado;
    private double horasEstacionadas;

    public Registro(String placa, LocalDate fecha, LocalTime horaIngreso, LocalTime horaSalida, double valorPagado, double horasEstacionadas) {
        this.placa = placa;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.valorPagado = valorPagado;
        this.horasEstacionadas = horasEstacionadas;
    }

    public void agregarRegistro(Registro registro){
        totalIngresos = totalIngresos + 1;
        ingresosGenerados = ingresosGenerados + registro.getValorPagado();

        if(registro.getHorasEstacionadas()
                > 5){

            vehiculosTiempoLargo.add(
                    registro.getPlaca()
            );
        }
    }

    public void calcularPromedio(){

        double suma = 0;

        for(Registro registro :
                listRegistrosDelDia){

            suma =
                    suma
                            + registro
                            .getHorasEstacionadas();
        }

        if(totalIngresos > 0){

            tiempoPromedioHoras =
                    suma /
                            totalIngresos;
        }
    }


    public String getPlaca() {
        return placa;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public double getValorPagado() {
        return valorPagado;
    }
    public double getHorasEstacionadas() {
        return horasEstacionadas;
    }

    @Override
    public String toString() {
        return placa + " | " + fecha + " | ingreso: " + horaIngreso + " | salida: " + horaSalida
                + " | horas: " + horasEstacionadas + " | pagó: $" + valorPagado;
    }
}



