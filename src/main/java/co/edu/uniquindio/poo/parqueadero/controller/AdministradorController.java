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


    /**
     * Metodo que se ejecuta automaticamente al cargar la pantalla del administrador,
     * llena los combos con los valores de los enums, selecciona el primero por defecto
     * y carga las listas iniciales de espacios, tarifas y usuarios
     */

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
    /**
     * Metodo que permite agregar un nuevo espacio desde la interfaz del administrador,
     * validando que el codigo no este vacio antes de llamar al modelo
     * @param event evento del boton agregar espacio
     */
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
    /**
     * Metodo que permite cargar los datos de un espacio seleccionado de la lista
     * en los campos del formulario para poder editarlo
     * @param event evento del boton cargar espacio seleccionado
     */
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
    /**
     * Metodo que permite modificar la informacion de un espacio existente desde la interfaz,
     * validando que se haya escrito o seleccionado un codigo antes de proceder
     * @param event evento del boton modificar espacio
     */
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

    /**
     * Metodo que permite deshabilitar un espacio del parqueadero poniendolo fuera de servicio,
     * validando que se haya ingresado el codigo del espacio
     * @param event evento del boton deshabilitar espacio
     */

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

    /**
     * Metodo que permite agregar una nueva tarifa desde la interfaz del administrador,
     * validando que el valor por hora y el descuento sean numeros validos
     * @param event evento del boton agregar tarifa
     */

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


    /**
     * Metodo que permite actualizar una tarifa existente desde la interfaz del administrador,
     * validando que el valor por hora y el descuento sean numeros validos
     * @param event evento del boton actualizar tarifa
     */

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

    /**
     * Metodo que permite registrar un nuevo usuario del parqueadero desde la interfaz,
     * validando que el nombre y la identificacion no esten vacios
     * @param event evento del boton agregar usuario
     */

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

    /**
     * Metodo que permite actualizar la informacion de un usuario existente desde la interfaz,
     * validando que la identificacion no este vacia antes de proceder
     * @param event evento del boton actualizar usuario
     */

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

    /**
     * Metodo que permite eliminar un usuario del parqueadero desde la interfaz,
     * validando que la identificacion no este vacia antes de proceder
     * @param event evento del boton eliminar usuario
     */

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

    /**
     * Metodo que permite cargar los datos de un usuario seleccionado de la lista
     * en los campos del formulario para poder editarlo
     * @param event evento del boton cargar usuario seleccionado
     */

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

    /**
     * Metodo que permite cerrar la sesion del administrador y volver a la pantalla de inicio
     * @param event evento del boton cerrar sesion
     */

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

    /**
     * Metodo privado que actualiza las listas de espacios, tarifas y usuarios
     * en la interfaz con los datos mas recientes del modelo
     */

    private void refrescarListas() {
        listaEspacios.setItems(FXCollections.observableArrayList(parqueadero.getListEspacios()));
        listaTarifas.setItems(FXCollections.observableArrayList(parqueadero.getListTarifas()));
        listaUsuarios.setItems(FXCollections.observableArrayList(parqueadero.getListUsuariosParqueaderos()));
    }

    /**
     * Metodo privado que borra el contenido de todos los campos del formulario de usuarios
     * y deja seleccionado el primer tipo de usuario del combo
     */

    private void limpiarFormularioUsuario() {
        txtNombreUsuario.clear();
        txtIdUsuario.clear();
        txtTelefonoUsuario.clear();
        txtCorreoUsuario.clear();
        cmbTipoUsuario.getSelectionModel().selectFirst();
    }


    /**
     * Metodo privado que muestra un mensaje en la barra inferior de la pantalla del administrador,
     * aplicando estilo verde si es un mensaje de exito o rojo si es un mensaje de error
     * @param texto mensaje a mostrar
     * @param esOk true si es un mensaje de exito, false si es un mensaje de error
     */

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
