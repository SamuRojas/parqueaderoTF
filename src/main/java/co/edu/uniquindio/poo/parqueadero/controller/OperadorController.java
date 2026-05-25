package co.edu.uniquindio.poo.parqueadero.controller;

import co.edu.uniquindio.poo.parqueadero.ModelFactory;
import co.edu.uniquindio.poo.parqueadero.exception.ParqueaderoException;
import co.edu.uniquindio.poo.parqueadero.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class OperadorController {

    @FXML private TextField txtPlacaIngreso;
    @FXML private TextField txtNombreConductor;
    @FXML private TextField txtIdConductor;
    @FXML private ComboBox<TipoVehiculo> cmbTipoVehiculo;
    @FXML private ComboBox<String> cmbEspacioIngreso;
    @FXML private TextField txtPlacaSalida;
    @FXML private TextArea txtSimulacionSalida;
    @FXML private TextField txtPlacaBuscar;
    @FXML private TextArea txtInfoVehiculo;
    @FXML private TextArea txtDetalleEspacios;
    @FXML private ListView<String> listaVehiculosDentro;
    @FXML private Label lblTotalEspacios;
    @FXML private Label lblOcupados;
    @FXML private Label lblDisponibles;
    @FXML private Label lblMensajeOperador;
    @FXML private TextField txtHorasReporte;
    @FXML private TextArea txtReporte;

    private final Parqueadero parqueadero = ModelFactory.getInstancia().getParqueadero();

    @FXML
    public void initialize() {
        cmbTipoVehiculo.setItems(FXCollections.observableArrayList(TipoVehiculo.values()));
        cmbTipoVehiculo.getSelectionModel().selectFirst();
        cargarEspaciosDisponibles();
        actualizarTodo();
        if (txtSimulacionSalida != null) {
            txtSimulacionSalida.setEditable(false);
        }
        if (txtInfoVehiculo != null) {
            txtInfoVehiculo.setEditable(false);
        }
        if (txtDetalleEspacios != null) {
            txtDetalleEspacios.setEditable(false);
        }
        if (txtReporte != null) {
            txtReporte.setEditable(false);
        }
    }

    @FXML
    protected void onTipoVehiculoCambio(ActionEvent event) {
        cargarEspaciosDisponibles();
    }

    @FXML
    protected void onRegistrarIngreso(ActionEvent event) {
        try {
            String placa = txtPlacaIngreso.getText().trim().toUpperCase();
            String nombre = txtNombreConductor.getText().trim();
            String id = txtIdConductor.getText().trim();
            TipoVehiculo tipo = cmbTipoVehiculo.getValue();

            if (placa.isEmpty() || nombre.isEmpty() || id.isEmpty()) {
                mostrarMensaje("Complete placa, conductor e identificación", false);
                return;
            }

            String codigoEspacio = cmbEspacioIngreso.getValue();
            String respuesta = parqueadero.registrarIngreso(placa, nombre, id, tipo, codigoEspacio);
            mostrarMensaje(respuesta, true);
            limpiarIngreso();
            actualizarTodo();
        } catch (ParqueaderoException e) {
            mostrarMensaje(e.getMessage(), false);
        }
    }

    @FXML
    protected void onSimularSalida(ActionEvent event) {
        try {
            String placa = txtPlacaSalida.getText().trim().toUpperCase();
            if (placa.isEmpty()) {
                mostrarMensaje("Ingrese la placa", false);
                return;
            }
            txtSimulacionSalida.setText(parqueadero.simularSalida(placa));
            mostrarMensaje("Simulación calculada", true);
        } catch (ParqueaderoException e) {
            txtSimulacionSalida.clear();
            mostrarMensaje(e.getMessage(), false);
        }
    }

    @FXML
    protected void onRegistrarSalida(ActionEvent event) {
        try {
            String placa = txtPlacaSalida.getText().trim().toUpperCase();
            if (placa.isEmpty()) {
                mostrarMensaje("Ingrese la placa", false);
                return;
            }
            String respuesta = parqueadero.registrarSalida(placa);
            txtSimulacionSalida.setText(respuesta);
            mostrarMensaje(respuesta, true);
            actualizarTodo();
        } catch (ParqueaderoException e) {
            mostrarMensaje(e.getMessage(), false);
        }
    }

    @FXML
    protected void onBuscarVehiculo(ActionEvent event) {
        try {
            String placa = txtPlacaBuscar.getText().trim().toUpperCase();
            if (placa.isEmpty()) {
                mostrarMensaje("Ingrese la placa a buscar", false);
                return;
            }
            txtInfoVehiculo.setText(parqueadero.consultarInformacionVehiculo(placa));
            mostrarMensaje("Información cargada", true);
        } catch (ParqueaderoException e) {
            txtInfoVehiculo.clear();
            mostrarMensaje(e.getMessage(), false);
        }
    }

    @FXML
    protected void onVerEspacios(ActionEvent event) {
        txtDetalleEspacios.setText(parqueadero.consultarDetalleEspacios());
        actualizarEstadisticas();
        mostrarMensaje("Espacios actualizados", true);
    }

    @FXML
    protected void onActualizarLista(ActionEvent event) {
        actualizarTodo();
        mostrarMensaje("Listas actualizadas", true);
    }

    @FXML
    protected void onGenerarReporte(ActionEvent event) {
        double horasMin = 2;
        try {
            if (txtHorasReporte != null && !txtHorasReporte.getText().trim().isEmpty()) {
                horasMin = Double.parseDouble(txtHorasReporte.getText().trim());
            }
        } catch (NumberFormatException e) {
            horasMin = 2;
        }
        ReporteDiario reporte = parqueadero.generarReporteDiario(horasMin);
        txtReporte.setText(reporte.generarTexto());
        mostrarMensaje("Reporte generado", true);
    }

    @FXML
    protected void onCerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co.edu.uniquindio.poo.parqueadero/view/Inicio.fxml"));
        Scene scene = new Scene(loader.load(), 760, 520);
        VistaUtil.aplicarEstilos(scene);
        Stage stage = (Stage) listaVehiculosDentro.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("PARKUQ");
        stage.centerOnScreen();
    }

    private void cargarEspaciosDisponibles() {
        TipoVehiculo tipo = cmbTipoVehiculo.getValue();
        if (tipo == null) {
            return;
        }
        java.util.List<String> codigos = parqueadero.listarCodigosEspaciosDisponibles(tipo);
        cmbEspacioIngreso.setItems(FXCollections.observableArrayList(codigos));
        if (!codigos.isEmpty()) {
            cmbEspacioIngreso.getSelectionModel().selectFirst();
        }
    }

    private void actualizarTodo() {
        java.util.List<String> textos = new java.util.ArrayList<>();
        for (Vehiculo v : parqueadero.consultarVehiculosDentro()) {
            textos.add(parqueadero.textoVehiculoDentro(v));
        }
        listaVehiculosDentro.setItems(FXCollections.observableArrayList(textos));
        txtDetalleEspacios.setText(parqueadero.consultarDetalleEspacios());
        actualizarEstadisticas();
        cargarEspaciosDisponibles();
    }

    private void actualizarEstadisticas() {
        int total = 0;
        int ocupados = 0;
        int disponibles = 0;
        for (Espacio e : parqueadero.getListEspacios()) {
            total = total + 1;
            if (e.getEstadoEspacio() == EstadoEspacio.OCUPADO) {
                ocupados = ocupados + 1;
            } else if (e.getEstadoEspacio() == EstadoEspacio.DISPONIBLE) {
                disponibles = disponibles + 1;
            }
        }
        lblTotalEspacios.setText(String.valueOf(total));
        lblOcupados.setText(String.valueOf(ocupados));
        lblDisponibles.setText(String.valueOf(disponibles));
    }

    private void limpiarIngreso() {
        txtPlacaIngreso.clear();
        txtNombreConductor.clear();
        txtIdConductor.clear();
    }

    private void mostrarMensaje(String texto, boolean ok) {
        lblMensajeOperador.setText(texto);
        lblMensajeOperador.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
        if (ok) {
            lblMensajeOperador.getStyleClass().add("mensaje-ok");
        } else {
            lblMensajeOperador.getStyleClass().add("mensaje-error");
        }
    }
}
