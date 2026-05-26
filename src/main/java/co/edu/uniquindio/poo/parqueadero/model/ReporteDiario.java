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
    private List<String> listVehiculosTiempoLargo;
    private List<Registro> listRegistrosDelDia;

    /**
     * Constructor de la clase ReporteDiario
     * @param fecha del reporte a generar
     */

    public ReporteDiario(LocalDate fecha) {
        this.fecha = fecha;
        this.totalIngresos = 0;
        this.ingresosGenerados = 0;
        this.tiempoPromedioHoras = 0;
        this.listVehiculosTiempoLargo = new ArrayList<>();
        this.listRegistrosDelDia = new ArrayList<>();
    }

    /**
     * Metodo que permite agregar un registro al reporte del dia,
     * sumando el ingreso al total, acumulando el valor pagado y
     * guardando la placa del vehiculo si estuvo mas de 5 horas estacionado
     * @param registro a agregar al reporte diario
     */


    public void agregarRegistro(Registro registro){
        listRegistrosDelDia.add(registro);
        totalIngresos = totalIngresos + 1;
        ingresosGenerados = ingresosGenerados + registro.getValorPagado();
        if(registro.getHorasEstacionadas() > 5){
            listVehiculosTiempoLargo.add(registro.getPlaca());
        }
    }

    /**
     * Metodo que permite calcular el tiempo promedio en horas
     * que los vehiculos estuvieron estacionados durante el dia,
     * sumando las horas de todos los registros y dividiendo entre el total de ingresos
     */

    public void calcularPromedio(){
        double suma = 0;
        for(Registro registro : listRegistrosDelDia){
            suma += registro.getHorasEstacionadas();
        }
        if(totalIngresos > 0){
            tiempoPromedioHoras = suma / totalIngresos;
        }
    }

    /**
     * Metodo que permite generar y retornar el texto del reporte diario
     * con la fecha, total de ingresos, dinero generado, tiempo promedio
     * y la lista de vehiculos que estuvieron mas de 5 horas
     * @return texto con el resumen del reporte diario
     */

    public String generarTexto(){
        String texto = "";
        texto = "REPORTE DIARIO\n"
                        + "Fecha: " + fecha + "\n"
                        + "Total ingresos: " + totalIngresos + "\n"
                        + "Ingresos generados: $" + ingresosGenerados + "\n"
                        + "Tiempo promedio: " + tiempoPromedioHoras + " horas\n"
                        + "Vehiculos mas de 5 horas: " + listVehiculosTiempoLargo;
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
    public List<String> getVehiculosTiempoLargo() {return listVehiculosTiempoLargo;}
    public void setVehiculosTiempoLargo(List<String> vehiculosTiempoLargo) {this.listVehiculosTiempoLargo = vehiculosTiempoLargo;}
    public List<Registro> getListRegistrosDelDia() {return listRegistrosDelDia;}
    public void setListRegistrosDelDia(List<Registro> listRegistrosDelDia) {this.listRegistrosDelDia = listRegistrosDelDia;}

    @Override
    public String toString() {
        return "ReporteDiario{" +
                "fecha=" + fecha +
                ", totalIngresos=" + totalIngresos +
                ", ingresosGenerados=" + ingresosGenerados +
                ", tiempoPromedioHoras=" + tiempoPromedioHoras +
                ", listVehiculosTiempoLargo=" + listVehiculosTiempoLargo +
                ", listRegistrosDelDia=" + listRegistrosDelDia +
                '}';
    }
}





