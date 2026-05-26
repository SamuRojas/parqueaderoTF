package co.edu.uniquindio.poo.parqueadero.controller;

import co.edu.uniquindio.poo.parqueadero.ModelFactory;
import co.edu.uniquindio.poo.parqueadero.model.IAutenticable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private Label lblTituloRol;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnIngresar;

    private String rol = "Operador";
    private final ModelFactory model = ModelFactory.getInstancia();

    /**
     * Metodo que permite establecer el rol del usuario que intenta iniciar sesion,
     * y actualiza el titulo de la pantalla con el rol seleccionado
     * @param rolNuevo rol que se asignara al login, puede ser Administrador u Operador
     */

    public void setRol(String rolNuevo) {
        this.rol = rolNuevo;
        if (lblTituloRol != null) {
            lblTituloRol.setText("Inicio de sesion - " + rolNuevo);
        }
    }

    /**
     * Metodo que permite volver a la pantalla de inicio al presionar el boton volver
     * @param event evento del boton volver
     */

    @FXML
    protected void onVolver(ActionEvent event) throws IOException {
        abrirPantalla("/co.edu.uniquindio.poo.parqueadero/view/Inicio.fxml", 900, 560, "PARKUQ");
    }

    /**
     * Metodo que permite validar el usuario y contrasena ingresados,
     * determinando si corresponden al administrador o al operador segun el rol seleccionado,
     * y abriendo la pantalla correspondiente si el login es correcto
     * @param event evento del boton ingresar
     */

    @FXML
    protected void onIngresar(ActionEvent event) {
        mostrarMensaje("");

        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("Complete usuario y contrasena");
            return;
        }

        IAutenticable cuenta = null;
        String vista = "";
        boolean esAdmin = false;

        if (rol != null && rol.equals("Administrador")) {
            cuenta = model.getAdministrador();
            vista = "/co.edu.uniquindio.poo.parqueadero/view/AdministradorView.fxml";
            esAdmin = true;
        } else {
            cuenta = model.getOperador();
            vista = "/co.edu.uniquindio.poo.parqueadero/view/OperadorView.fxml";
        }

        boolean loginOk = false;
        if (cuenta != null) {
            loginOk = cuenta.logIn(usuario, contrasena);
        }

        if (loginOk) {
            try {
                int ancho = 1000;
                int alto = 680;
                abrirPantalla(vista, ancho, alto, "PARKUQ - " + (esAdmin ? "Administrador" : "Operador"));
            } catch (Exception e) {
                mostrarMensaje("Error al abrir el panel: " + e.getMessage());
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error PARKUQ");
                alerta.setHeaderText("No se pudo cargar la pantalla");
                alerta.setContentText(e.getMessage());
                alerta.showAndWait();
            }
        } else {
            mostrarMensaje("Usuario o contrasena incorrectos");
        }
    }

    /**
     * Metodo privado que permite cargar y mostrar una pantalla nueva en la misma ventana,
     * aplicando los estilos y el titulo indicados
     * @param ruta ruta del archivo FXML a cargar
     * @param ancho ancho de la nueva escena
     * @param alto alto de la nueva escena
     * @param titulo titulo de la ventana
     */

    private void abrirPantalla(String ruta, int ancho, int alto, String titulo) throws IOException {
        URL url = getClass().getResource(ruta);
        if (url == null) {
            mostrarMensaje("No se encontro el archivo de la interfaz");
            return;
        }
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load(), ancho, alto);
        VistaUtil.aplicarEstilos(scene);
        Stage stage = (Stage) btnIngresar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.centerOnScreen();
    }

    /**
     * Metodo privado que muestra un mensaje de texto en el label de mensajes de la pantalla de login
     * @param texto mensaje a mostrar
     */

    private void mostrarMensaje(String texto) {
        if (lblMensaje != null) {
            lblMensaje.setText(texto);
        }
    }
}
