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

    /**
     * Metodo que se ejecuta automaticamente al cargar la pantalla del operador,
     * llena el combo de tipo de vehiculo, configura los TextAreas como solo lectura
     * y carga los datos iniciales en pantalla
     */

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

    /**
     * Metodo que permite registrar el ingreso de un vehiculo al parqueadero desde la interfaz,
     * validando que todos los campos esten completos y manejando las excepciones del modelo
     * @param event evento del boton registrar ingreso
     */

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

    /**
     * Metodo privado que borra el contenido de todos los campos del formulario de ingreso
     * y deja seleccionado el primer tipo de vehiculo del combo
     */

    private void limpiarFormularioIngreso() {
        txtPlacaIngreso.clear();
        txtNombreConductor.clear();
        txtIdConductor.clear();
        cmbTipoVehiculo.getSelectionModel().selectFirst();
    }

    // ============================================================
    //  TAB SALIDA Y COBRO
    // ============================================================

    /**
     * Metodo que permite simular el cobro de salida de un vehiculo sin registrarla oficialmente,
     * validando que se haya ingresado la placa y manejando las excepciones del modelo
     * @param event evento del boton simular salida
     */

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

    /**
     * Metodo que permite registrar la salida definitiva de un vehiculo del parqueadero,
     * validando que se haya ingresado la placa y manejando las excepciones del modelo
     * @param event evento del boton registrar salida
     */

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

    /**
     * Metodo que permite buscar y mostrar la informacion de un vehiculo
     * ingresando su placa en la interfaz del operador
     * @param event evento del boton buscar vehiculo
     */

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

    /**
     * Metodo que permite ver el detalle de todos los espacios del parqueadero
     * y actualizar las estadisticas en la pantalla del operador
     * @param event evento del boton ver espacios
     */

    @FXML
    protected void onVerEspacios(ActionEvent event) {
        txtDetalleEspacios.setText(parqueadero.consultarDetalleEspacios());
        actualizarEstadisticas();
        mostrarMensaje("Espacios actualizados", true);
    }

    // ============================================================
    //  TAB DENTRO DEL PARQUEADERO
    // ============================================================

    /**
     * Metodo que permite actualizar todas las listas y estadisticas mostradas
     * en la pantalla del operador con los datos mas recientes
     * @param event evento del boton actualizar lista
     */

    @FXML
    protected void onActualizarLista(ActionEvent event) {
        actualizarTodo();
        mostrarMensaje("Listas actualizadas", true);
    }

    // ============================================================
    //  TAB REPORTES
    // ============================================================

    /**
     * Metodo que permite generar el reporte diario del parqueadero
     * y mostrarlo en el area de texto de reportes de la interfaz
     * @param event evento del boton generar reporte
     */

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

    /**
     * Metodo que permite cerrar la sesion del operador y volver a la pantalla de inicio
     * @param event evento del boton cerrar sesion
     */

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

    /**
     * Metodo privado que actualiza la lista de vehiculos dentro, el detalle de espacios
     * y las estadisticas en la pantalla del operador con los datos mas recientes
     */

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

    /**
     * Metodo privado que recorre todos los espacios del parqueadero y actualiza
     * los contadores de espacios totales, ocupados y disponibles en la interfaz
     */

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


    /**
     * Metodo privado que muestra un mensaje en la barra inferior de la pantalla del operador,
     * aplicando estilo verde si es un mensaje de exito o rojo si es un mensaje de error
     * @param texto mensaje a mostrar
     * @param esOk true si es un mensaje de exito, false si es un mensaje de error
     */

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

    /**
     * Metodo privado que muestra una ventana emergente de tipo advertencia
     * con el mensaje de error recibido desde el modelo
     * @param mensaje texto del error a mostrar en la alerta
     */

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("PARKUQ - Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
