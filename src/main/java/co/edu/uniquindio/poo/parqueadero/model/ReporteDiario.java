package co.edu.uniquindio.poo.parqueadero.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteDiario implements IReporte {

    private LocalDate fecha;
    private int totalIngresos;
    private double ingresosGenerados;
    private double tiempoPromedioHoras;
    private List<String> vehiculosTiempoLargo;
    private List<Registro> listRegistrosDelDia;

    public ReporteDiario(LocalDate fecha) {
        this.fecha = fecha;
        this.totalIngresos = 0;
        this.ingresosGenerados = 0;
        this.tiempoPromedioHoras = 0;
        this.vehiculosTiempoLargo = new ArrayList<>();
        this.listRegistrosDelDia = new ArrayList<>();
    }




    public void agregarRegistro(Registro registro){
        listRegistrosDelDia.add(registro);
        totalIngresos = totalIngresos + 1;
        ingresosGenerados = ingresosGenerados + registro.getValorPagado();
        if(registro.getHorasEstacionadas() > 5){
            vehiculosTiempoLargo.add(registro.getPlaca());
        }
    }

    public void calcularPromedio(){
        double suma = 0;
        for(Registro registro : listRegistrosDelDia){
            suma += registro.getHorasEstacionadas();
        }
        if(totalIngresos > 0){
            tiempoPromedioHoras = suma / totalIngresos;
        }
    }

    public String generarTexto(){
        String texto = "";
        texto = "REPORTE DIARIO\n"
                        + "Fecha: " + fecha + "\n"
                        + "Total ingresos: " + totalIngresos + "\n"
                        + "Ingresos generados: $" + ingresosGenerados + "\n"
                        + "Tiempo promedio: " + tiempoPromedioHoras + " horas\n"
                        + "Vehiculos mas de 5 horas: " + vehiculosTiempoLargo;
        return texto;
    }

    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public int getTotalIngresos() {return totalIngresos;}
    public void setTotalIngresos(int totalIngresos) {this.totalIngresos = totalIngresos;}
    public double getIngresosGenerados() {return ingresosGenerados;}
    public void setIngresosGenerados(double ingresosGenerados) {this.ingresosGenerados = ingresosGenerados;}
    public double getTiempoPromedioHoras() {return tiempoPromedioHoras;}
    public void setTiempoPromedioHoras(double tiempoPromedioHoras) {this.tiempoPromedioHoras = tiempoPromedioHoras;}
    public List<String> getVehiculosTiempoLargo() {return vehiculosTiempoLargo;}
    public void setVehiculosTiempoLargo(List<String> vehiculosTiempoLargo) {this.vehiculosTiempoLargo = vehiculosTiempoLargo;}
    public List<Registro> getListRegistrosDelDia() {return listRegistrosDelDia;}
    public void setListRegistrosDelDia(List<Registro> listRegistrosDelDia) {this.listRegistrosDelDia = listRegistrosDelDia;}
}





