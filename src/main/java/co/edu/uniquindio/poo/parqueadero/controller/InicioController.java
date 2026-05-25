package co.edu.uniquindio.poo.parqueadero.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {

    @FXML
    private Button btnAdministrador;
    @FXML
    private Button btnOperador;

    @FXML
    protected void onAdministrador(ActionEvent event) throws IOException {
        abrirLogin("Administrador");
    }

    @FXML
    protected void onOperador(ActionEvent event) throws IOException {
        abrirLogin("Operador");
    }

    private void abrirLogin(String rol) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co.edu.uniquindio.poo.parqueadero/view/Login.fxml"));
        Scene scene = new Scene(loader.load(), 900, 560);
        VistaUtil.aplicarEstilos(scene);
        LoginController controller = loader.getController();
        controller.setRol(rol);
        Stage stage = (Stage) btnAdministrador.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("PARKUQ - Login " + rol);
        stage.centerOnScreen();
    }
}
