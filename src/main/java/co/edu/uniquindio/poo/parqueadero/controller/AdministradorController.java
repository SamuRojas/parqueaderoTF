package co.edu.uniquindio.poo.parqueadero.controller;

import co.edu.uniquindio.poo.parqueadero.ModelFactory;
import co.edu.uniquindio.poo.parqueadero.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorController {

    @FXML private TextField txtCodigoEspacio;
    @FXML private ComboBox<TipoEspacio> cmbTipoEspacio;
    @FXML private ComboBox<EstadoEspacio> cmbEstadoEspacio;
    @FXML private ListView<Espacio> listaEspacios;
    @FXML private ComboBox<TipoVehiculo> cmbTipoTarifa;
    @FXML private TextField txtValorHora;
    @FXML private TextField txtDescuento;
    @FXML private ListView<Tarifa> listaTarifas;
    @FXML private TextField txtNombreUsuario;
    @FXML private TextField txtIdUsuario;
    @FXML private TextField txtTelefonoUsuario;
    @FXML private TextField txtCorreoUsuario;
    @FXML private ComboBox<TipoUsuarioParqueadero> cmbTipoUsuario;
    @FXML private ListView<UsuarioParqueadero> listaUsuarios;
    @FXML private Label lblMensajeAdmin;

    private final Parqueadero parqueadero = ModelFactory.getInstancia().getParqueadero();

    @FXML
    public void initialize() {
        cmbTipoEspacio.setItems(FXCollections.observableArrayList(TipoEspacio.values()));
        cmbEstadoEspacio.setItems(FXCollections.observableArrayList(EstadoEspacio.values()));
        cmbTipoTarifa.setItems(FXCollections.observableArrayList(TipoVehiculo.values()));
        cmbTipoUsuario.setItems(FXCollections.observableArrayList(TipoUsuarioParqueadero.values()));
        cmbTipoEspacio.getSelectionModel().selectFirst();
        cmbEstadoEspacio.getSelectionModel().selectFirst();
        cmbTipoTarifa.getSelectionModel().selectFirst();
        cmbTipoUsuario.getSelectionModel().selectFirst();
        refrescarListas();
    }

    @FXML
    protected void onAgregarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText().trim();
        if (codigo.isEmpty()) {
            mensaje("Ingrese el código del espacio", false);
            return;
        }
        mensaje(parqueadero.agregarEspacio(codigo, cmbTipoEspacio.getValue(), cmbEstadoEspacio.getValue()), true);
        refrescarListas();
    }

    @FXML
    protected void onCargarEspacioSeleccionado(ActionEvent event) {
        Espacio esp = listaEspacios.getSelectionModel().getSelectedItem();
        if (esp == null) {
            mensaje("Seleccione un espacio de la lista", false);
        } else {
            txtCodigoEspacio.setText(esp.getCodigo());
            cmbTipoEspacio.getSelectionModel().select(esp.getTipoEspacio());
            cmbEstadoEspacio.getSelectionModel().select(esp.getEstadoEspacio());
            mensaje("Espacio " + esp.getCodigo() + " cargado en el formulario", true);
        }
    }

    @FXML
    protected void onModificarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText().trim();
        if (codigo.isEmpty()) {
            mensaje("Seleccione o escriba el código del espacio", false);
            return;
        }
        mensaje(parqueadero.modificarInformacionEspacio(codigo, cmbTipoEspacio.getValue(), cmbEstadoEspacio.getValue()), true);
        refrescarListas();
    }

    @FXML
    protected void onDeshabilitarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText().trim();
        if (codigo.isEmpty()) {
            mensaje("Ingrese el código del espacio", false);
            return;
        }
        mensaje(parqueadero.modificarEstadoEspacio(codigo, EstadoEspacio.FUERADESERVICIO), true);
        refrescarListas();
    }

    @FXML
    protected void onAgregarTarifa(ActionEvent event) {
        try {
            double valor = Double.parseDouble(txtValorHora.getText().trim());
            double desc = Double.parseDouble(txtDescuento.getText().trim());
            mensaje(parqueadero.agregarTarifa(cmbTipoTarifa.getValue(), valor, desc), true);
            refrescarListas();
        } catch (NumberFormatException e) {
            mensaje("Valor por hora y descuento deben ser números", false);
        }
    }

    @FXML
    protected void onActualizarTarifa(ActionEvent event) {
        try {
            double valor = Double.parseDouble(txtValorHora.getText().trim());
            double desc = Double.parseDouble(txtDescuento.getText().trim());
            mensaje(parqueadero.actualizarTarifa(cmbTipoTarifa.getValue(), valor, desc), true);
            refrescarListas();
        } catch (NumberFormatException e) {
            mensaje("Valor por hora y descuento deben ser números", false);
        }
    }

    @FXML
    protected void onAgregarUsuario(ActionEvent event) {
        String nombre = txtNombreUsuario.getText().trim();
        String id = txtIdUsuario.getText().trim();
        if (nombre.isEmpty() || id.isEmpty()) {
            mensaje("Complete nombre e identificación", false);
            return;
        }
        mensaje(parqueadero.registrarNuevoUsuario(nombre, id,
                txtTelefonoUsuario.getText().trim(),
                txtCorreoUsuario.getText().trim(),
                cmbTipoUsuario.getValue()), true);
        refrescarListas();
    }

    @FXML
    protected void onActualizarUsuario(ActionEvent event) {
        String id = txtIdUsuario.getText().trim();
        if (id.isEmpty()) {
            mensaje("Ingrese la identificación del usuario", false);
            return;
        }
        mensaje(parqueadero.actualizarInfoUsuarioParqueadero(
                txtNombreUsuario.getText().trim(), id,
                txtTelefonoUsuario.getText().trim(),
                txtCorreoUsuario.getText().trim(),
                cmbTipoUsuario.getValue()), true);
        refrescarListas();
    }

    @FXML
    protected void onEliminarUsuario(ActionEvent event) {
        String id = txtIdUsuario.getText().trim();
        if (id.isEmpty()) {
            mensaje("Ingrese la identificación a eliminar", false);
            return;
        }
        mensaje(parqueadero.eliminarUsuario(id), true);
        refrescarListas();
    }

    @FXML
    protected void onCerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co.edu.uniquindio.poo.parqueadero/view/Inicio.fxml"));
        Scene scene = new Scene(loader.load(), 760, 520);
        VistaUtil.aplicarEstilos(scene);
        Stage stage = (Stage) listaEspacios.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("PARKUQ");
        stage.centerOnScreen();
    }

    private void refrescarListas() {
        listaEspacios.setItems(FXCollections.observableArrayList(parqueadero.getListEspacios()));
        listaTarifas.setItems(FXCollections.observableArrayList(parqueadero.getListTarifas()));
        listaUsuarios.setItems(FXCollections.observableArrayList(parqueadero.getListUsuariosParqueaderos()));
    }

    private void mensaje(String texto, boolean ok) {
        if (lblMensajeAdmin != null) {
            lblMensajeAdmin.setText(texto);
            lblMensajeAdmin.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
            if (ok) {
                lblMensajeAdmin.getStyleClass().add("mensaje-ok");
            } else {
                lblMensajeAdmin.getStyleClass().add("mensaje-error");
            }
        }
    }
}
