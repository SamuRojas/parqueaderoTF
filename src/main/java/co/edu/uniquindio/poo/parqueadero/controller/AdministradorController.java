package co.edu.uniquindio.poo.parqueadero.controller;

import co.edu.uniquindio.poo.parqueadero.ModelFactory;
import co.edu.uniquindio.poo.parqueadero.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorController {

    // ---- Tab Espacios ----
    @FXML private TextField txtCodigoEspacio;
    @FXML private ComboBox<TipoEspacio>   cmbTipoEspacio;
    @FXML private ComboBox<EstadoEspacio> cmbEstadoEspacio;
    @FXML private ListView<Espacio>       listaEspacios;

    // ---- Tab Tarifas ----
    @FXML private ComboBox<TipoVehiculo> cmbTipoTarifa;
    @FXML private TextField txtValorHora;
    @FXML private TextField txtDescuento;
    @FXML private ListView<Tarifa>       listaTarifas;

    // ---- Tab Usuarios ----
    @FXML private TextField txtNombreUsuario;
    @FXML private TextField txtIdUsuario;
    @FXML private TextField txtTelefonoUsuario;
    @FXML private TextField txtCorreoUsuario;
    @FXML private ComboBox<TipoUsuarioParqueadero> cmbTipoUsuario;
    @FXML private ListView<UsuarioParqueadero>     listaUsuarios;

    // ---- Barra inferior ----
    @FXML private Label lblMensajeAdmin;

    private final Parqueadero parqueadero = ModelFactory.getInstancia().getParqueadero();

    @FXML
    public void initialize() {
        // Llenar combos con los valores de los enums
        cmbTipoEspacio.setItems(FXCollections.observableArrayList(TipoEspacio.values()));
        cmbEstadoEspacio.setItems(FXCollections.observableArrayList(EstadoEspacio.values()));
        cmbTipoTarifa.setItems(FXCollections.observableArrayList(TipoVehiculo.values()));
        cmbTipoUsuario.setItems(FXCollections.observableArrayList(TipoUsuarioParqueadero.values()));

        // Seleccionar el primero por defecto para evitar valores nulos
        cmbTipoEspacio.getSelectionModel().selectFirst();
        cmbEstadoEspacio.getSelectionModel().selectFirst();
        cmbTipoTarifa.getSelectionModel().selectFirst();
        cmbTipoUsuario.getSelectionModel().selectFirst();

        // Cargar listas iniciales
        refrescarListas();
    }

    // ============================================================
    //  TAB ESPACIOS
    // ============================================================

    @FXML
    protected void onAgregarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText().trim();
        if (codigo.isEmpty()) {
            mensaje("Ingrese el codigo del espacio", false);
            return;
        }
        String respuesta = parqueadero.agregarEspacio(
                codigo,
                cmbTipoEspacio.getValue(),
                cmbEstadoEspacio.getValue());
        mensaje(respuesta, true);
        txtCodigoEspacio.clear();
        refrescarListas();
    }

    @FXML
    protected void onCargarEspacioSeleccionado(ActionEvent event) {
        Espacio esp = listaEspacios.getSelectionModel().getSelectedItem();
        if (esp == null) {
            mensaje("Seleccione un espacio de la lista", false);
            return;
        }
        txtCodigoEspacio.setText(esp.getCodigo());
        cmbTipoEspacio.getSelectionModel().select(esp.getTipoEspacio());
        cmbEstadoEspacio.getSelectionModel().select(esp.getEstadoEspacio());
        mensaje("Espacio " + esp.getCodigo() + " cargado en el formulario", true);
    }

    @FXML
    protected void onModificarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText().trim();
        if (codigo.isEmpty()) {
            mensaje("Escriba o seleccione el codigo del espacio", false);
            return;
        }
        String respuesta = parqueadero.modificarInformacionEspacio(
                codigo,
                cmbTipoEspacio.getValue(),
                cmbEstadoEspacio.getValue());
        mensaje(respuesta, true);
        refrescarListas();
    }

    @FXML
    protected void onDeshabilitarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText().trim();
        if (codigo.isEmpty()) {
            mensaje("Ingrese el codigo del espacio a deshabilitar", false);
            return;
        }
        String respuesta = parqueadero.modificarEstadoEspacio(codigo, EstadoEspacio.FUERADESERVICIO);
        mensaje(respuesta, true);
        refrescarListas();
    }

    // ============================================================
    //  TAB TARIFAS
    // ============================================================

    @FXML
    protected void onAgregarTarifa(ActionEvent event) {
        try {
            double valor     = Double.parseDouble(txtValorHora.getText().trim());
            double descuento = Double.parseDouble(txtDescuento.getText().trim());
            String respuesta = parqueadero.agregarTarifa(cmbTipoTarifa.getValue(), valor, descuento);
            mensaje(respuesta, true);
            txtValorHora.clear();
            txtDescuento.clear();
            refrescarListas();
        } catch (NumberFormatException e) {
            mensaje("Valor por hora y descuento deben ser numeros validos (ej: 3000 o 5.0)", false);
        }
    }

    @FXML
    protected void onActualizarTarifa(ActionEvent event) {
        try {
            double valor     = Double.parseDouble(txtValorHora.getText().trim());
            double descuento = Double.parseDouble(txtDescuento.getText().trim());
            String respuesta = parqueadero.actualizarTarifa(cmbTipoTarifa.getValue(), valor, descuento);
            mensaje(respuesta, true);
            refrescarListas();
        } catch (NumberFormatException e) {
            mensaje("Valor por hora y descuento deben ser numeros validos (ej: 3000 o 5.0)", false);
        }
    }

    // ============================================================
    //  TAB USUARIOS
    // ============================================================

    @FXML
    protected void onAgregarUsuario(ActionEvent event) {
        String nombre = txtNombreUsuario.getText().trim();
        String id     = txtIdUsuario.getText().trim();

        if (nombre.isEmpty() || id.isEmpty()) {
            mensaje("Nombre e identificacion son obligatorios", false);
            return;
        }

        String respuesta = parqueadero.registrarNuevoUsuario(
                nombre, id,
                txtTelefonoUsuario.getText().trim(),
                txtCorreoUsuario.getText().trim(),
                cmbTipoUsuario.getValue());

        mensaje(respuesta, true);
        limpiarFormularioUsuario();
        refrescarListas();
    }

    @FXML
    protected void onActualizarUsuario(ActionEvent event) {
        String id = txtIdUsuario.getText().trim();
        if (id.isEmpty()) {
            mensaje("Ingrese la identificacion del usuario a actualizar", false);
            return;
        }
        String respuesta = parqueadero.actualizarInfoUsuarioParqueadero(
                txtNombreUsuario.getText().trim(),
                id,
                txtTelefonoUsuario.getText().trim(),
                txtCorreoUsuario.getText().trim(),
                cmbTipoUsuario.getValue());

        mensaje(respuesta, true);
        refrescarListas();
    }

    @FXML
    protected void onEliminarUsuario(ActionEvent event) {
        String id = txtIdUsuario.getText().trim();
        if (id.isEmpty()) {
            mensaje("Ingrese la identificacion del usuario a eliminar", false);
            return;
        }
        String respuesta = parqueadero.eliminarUsuario(id);
        mensaje(respuesta, true);
        limpiarFormularioUsuario();
        refrescarListas();
    }

    @FXML
    protected void onCargarUsuarioSeleccionado(ActionEvent event) {
        UsuarioParqueadero u = listaUsuarios.getSelectionModel().getSelectedItem();
        if (u == null) {
            mensaje("Seleccione un usuario de la lista", false);
            return;
        }
        txtNombreUsuario.setText(u.getNombre());
        txtIdUsuario.setText(u.getIdentificacion());
        txtTelefonoUsuario.setText(u.getTelefono());
        txtCorreoUsuario.setText(u.getCorreo());
        cmbTipoUsuario.getSelectionModel().select(u.getTipoUsuarioParqueadero());
        mensaje("Usuario " + u.getNombre() + " cargado en el formulario", true);
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
        // Tomamos la ventana a partir de listaEspacios que siempre existe
        Stage stage = (Stage) listaEspacios.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("PARKUQ");
        stage.centerOnScreen();
    }

    // ============================================================
    //  METODOS PRIVADOS DE APOYO
    // ============================================================

    private void refrescarListas() {
        listaEspacios.setItems(FXCollections.observableArrayList(parqueadero.getListEspacios()));
        listaTarifas.setItems(FXCollections.observableArrayList(parqueadero.getListTarifas()));
        listaUsuarios.setItems(FXCollections.observableArrayList(parqueadero.getListUsuariosParqueaderos()));
    }

    private void limpiarFormularioUsuario() {
        txtNombreUsuario.clear();
        txtIdUsuario.clear();
        txtTelefonoUsuario.clear();
        txtCorreoUsuario.clear();
        cmbTipoUsuario.getSelectionModel().selectFirst();
    }

    private void mensaje(String texto, boolean esOk) {
        if (lblMensajeAdmin != null) {
            lblMensajeAdmin.setText(texto);
            lblMensajeAdmin.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
            if (esOk) {
                lblMensajeAdmin.getStyleClass().add("mensaje-ok");
            } else {
                lblMensajeAdmin.getStyleClass().add("mensaje-error");
            }
        }
    }
}
