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

    /**
     * Metodo que permite abrir la pantalla de login con el rol de Administrador
     * al presionar el boton correspondiente en la pantalla de inicio
     * @param event evento del boton administrador
     */

    @FXML
    protected void onAdministrador(ActionEvent event) throws IOException {
        abrirLogin("Administrador");
    }

    /**
     * Metodo que permite abrir la pantalla de login con el rol de Operador
     * al presionar el boton correspondiente en la pantalla de inicio
     * @param event evento del boton operador
     */

    @FXML
    protected void onOperador(ActionEvent event) throws IOException {
        abrirLogin("Operador");
    }

    /**
     * Metodo privado que carga la pantalla de login, le asigna el rol recibido
     * y la muestra en la misma ventana con el titulo actualizado
     * @param rol rol que se le pasara al LoginController, puede ser Administrador u Operador
     */

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
