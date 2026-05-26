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
import java.util.ArrayList;
import java.util.List;

public class OperadorController {

    // ---- Tab Ingreso ----
    @FXML private TextField txtPlacaIngreso;
    @FXML private TextField txtNombreConductor;
    @FXML private TextField txtIdConductor;
    @FXML private ComboBox<TipoVehiculo> cmbTipoVehiculo;

    // ---- Tab Salida ----
    @FXML private TextField txtPlacaSalida;
    @FXML private TextArea txtSimulacionSalida;

    // ---- Tab Buscar vehiculo ----
    @FXML private TextField txtPlacaBuscar;
    @FXML private TextArea txtInfoVehiculo;

    // ---- Tab Espacios ----
    @FXML private TextArea txtDetalleEspacios;
    @FXML private Label lblTotalEspacios;
    @FXML private Label lblOcupados;
    @FXML private Label lblDisponibles;

    // ---- Tab Dentro del parqueadero ----
    @FXML private ListView<String> listaVehiculosDentro;

    // ---- Tab Reportes ----
    @FXML private TextArea txtReporte;

    // ---- Barra inferior ----
    @FXML private Label lblMensajeOperador;

    private final Parqueadero parqueadero = ModelFactory.getInstancia().getParqueadero();

    @FXML
    public void initialize() {
        // Llenar combo tipo vehiculo con todos los valores del enum
        cmbTipoVehiculo.setItems(FXCollections.observableArrayList(TipoVehiculo.values()));
        cmbTipoVehiculo.getSelectionModel().selectFirst();

        // Los TextAreas de solo lectura no se editan
        if (txtSimulacionSalida != null) txtSimulacionSalida.setEditable(false);
        if (txtInfoVehiculo != null)     txtInfoVehiculo.setEditable(false);
        if (txtDetalleEspacios != null)  txtDetalleEspacios.setEditable(false);
        if (txtReporte != null)          txtReporte.setEditable(false);

        // Cargar datos iniciales en pantalla
        actualizarTodo();
    }

    // ============================================================
    //  TAB INGRESO
    // ============================================================

    @FXML
    protected void onRegistrarIngreso(ActionEvent event) {
        String placa  = txtPlacaIngreso.getText().trim().toUpperCase();
        String nombre = txtNombreConductor.getText().trim();
        String id     = txtIdConductor.getText().trim();
        TipoVehiculo tipo = cmbTipoVehiculo.getValue();

        if (placa.isEmpty() || nombre.isEmpty() || id.isEmpty() || tipo == null) {
            mostrarMensaje("Complete todos los campos obligatorios (placa, conductor, ID y tipo)", false);
            return;
        }

        try {
            // registrarIngreso(placa, nombre, id, tipoVehiculo) — 4 params segun el modelo
            String respuesta = parqueadero.registrarIngreso(placa, nombre, id, tipo);
            mostrarMensaje(respuesta, true);
            limpiarFormularioIngreso();
            actualizarTodo();

        } catch (ParqueaderoException e) {
            mostrarAlerta(e.getMessage());
            mostrarMensaje(e.getMessage(), false);
        }
    }

    private void limpiarFormularioIngreso() {
        txtPlacaIngreso.clear();
        txtNombreConductor.clear();
        txtIdConductor.clear();
        cmbTipoVehiculo.getSelectionModel().selectFirst();
    }

    // ============================================================
    //  TAB SALIDA Y COBRO
    // ============================================================

    @FXML
    protected void onSimularSalida(ActionEvent event) {
        String placa = txtPlacaSalida.getText().trim().toUpperCase();
        if (placa.isEmpty()) {
            mostrarMensaje("Ingrese la placa del vehiculo", false);
            return;
        }
        try {
            String resultado = parqueadero.simularSalida(placa);
            txtSimulacionSalida.setText(resultado);
            mostrarMensaje("Simulacion calculada correctamente", true);
        } catch (ParqueaderoException e) {
            txtSimulacionSalida.clear();
            mostrarAlerta(e.getMessage());
            mostrarMensaje(e.getMessage(), false);
        }
    }

    @FXML
    protected void onRegistrarSalida(ActionEvent event) {
        String placa = txtPlacaSalida.getText().trim().toUpperCase();
        if (placa.isEmpty()) {
            mostrarMensaje("Ingrese la placa del vehiculo", false);
            return;
        }
        try {
            String resultado = parqueadero.registrarSalida(placa);
            txtSimulacionSalida.setText(resultado);
            mostrarMensaje("Salida registrada correctamente", true);
            txtPlacaSalida.clear();
            actualizarTodo();
        } catch (ParqueaderoException e) {
            mostrarAlerta(e.getMessage());
            mostrarMensaje(e.getMessage(), false);
        }
    }

    // ============================================================
    //  TAB BUSCAR VEHICULO
    // ============================================================

    @FXML
    protected void onBuscarVehiculo(ActionEvent event) {
        String placa = txtPlacaBuscar.getText().trim().toUpperCase();
        if (placa.isEmpty()) {
            mostrarMensaje("Ingrese una placa para buscar", false);
            return;
        }
        try {
            String info = parqueadero.consultarInformacionVehiculo(placa);
            txtInfoVehiculo.setText(info);
            mostrarMensaje("Informacion cargada", true);
        } catch (ParqueaderoException e) {
            txtInfoVehiculo.clear();
            mostrarAlerta(e.getMessage());
            mostrarMensaje(e.getMessage(), false);
        }
    }

    // ============================================================
    //  TAB ESPACIOS
    // ============================================================

    @FXML
    protected void onVerEspacios(ActionEvent event) {
        txtDetalleEspacios.setText(parqueadero.consultarDetalleEspacios());
        actualizarEstadisticas();
        mostrarMensaje("Espacios actualizados", true);
    }

    // ============================================================
    //  TAB DENTRO DEL PARQUEADERO
    // ============================================================

    @FXML
    protected void onActualizarLista(ActionEvent event) {
        actualizarTodo();
        mostrarMensaje("Listas actualizadas", true);
    }

    // ============================================================
    //  TAB REPORTES
    // ============================================================

    @FXML
    protected void onGenerarReporte(ActionEvent event) {
        // generarReporteDiario() no recibe parametros — asi esta en el modelo
        ReporteDiario reporte = parqueadero.generarReporteDiario();
        txtReporte.setText(reporte.generarTexto());
        mostrarMensaje("Reporte del dia generado correctamente", true);
    }

    // ============================================================
    //  CERRAR SESION
    // ============================================================

    @FXML
    protected void onCerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co.edu.uniquindio.poo.parqueadero/view/Inicio.fxml"));
        Scene scene = new Scene(loader.load(), 900, 560);
        VistaUtil.aplicarEstilos(scene);
        Stage stage = (Stage) listaVehiculosDentro.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("PARKUQ");
        stage.centerOnScreen();
    }

    // ============================================================
    //  METODOS PRIVADOS DE APOYO
    // ============================================================

    private void actualizarTodo() {
        // Lista de vehiculos dentro
        List<String> textos = new ArrayList<>();
        for (Vehiculo v : parqueadero.consultarVehiculosDentro()) {
            textos.add(parqueadero.textoVehiculoDentro(v));
        }
        listaVehiculosDentro.setItems(FXCollections.observableArrayList(textos));

        // Detalle de espacios
        if (txtDetalleEspacios != null) {
            txtDetalleEspacios.setText(parqueadero.consultarDetalleEspacios());
        }

        actualizarEstadisticas();
    }

    private void actualizarEstadisticas() {
        int total       = 0;
        int ocupados    = 0;
        int disponibles = 0;

        for (Espacio e : parqueadero.getListEspacios()) {
            total++;
            if (e.getEstadoEspacio() == EstadoEspacio.OCUPADO) {
                ocupados++;
            } else if (e.getEstadoEspacio() == EstadoEspacio.DISPONIBLE) {
                disponibles++;
            }
        }

        if (lblTotalEspacios  != null) lblTotalEspacios.setText(String.valueOf(total));
        if (lblOcupados       != null) lblOcupados.setText(String.valueOf(ocupados));
        if (lblDisponibles    != null) lblDisponibles.setText(String.valueOf(disponibles));
    }

    private void mostrarMensaje(String texto, boolean esOk) {
        if (lblMensajeOperador != null) {
            lblMensajeOperador.setText(texto);
            lblMensajeOperador.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
            if (esOk) {
                lblMensajeOperador.getStyleClass().add("mensaje-ok");
            } else {
                lblMensajeOperador.getStyleClass().add("mensaje-error");
            }
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("PARKUQ - Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
