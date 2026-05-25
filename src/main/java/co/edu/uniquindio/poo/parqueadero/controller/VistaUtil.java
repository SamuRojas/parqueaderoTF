package co.edu.uniquindio.poo.parqueadero.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class VistaUtil {

    private static final String CSS = "/co.edu.uniquindio.poo.parqueadero/css/parkuq.css";

    public static void aplicarEstilos(Parent root) {
        if (root != null && root.getScene() != null) {
            root.getScene().getStylesheets().clear();
            root.getScene().getStylesheets().add(
                    VistaUtil.class.getResource(CSS).toExternalForm());
        }
    }

    public static void aplicarEstilos(Scene scene) {
        if (scene != null) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(
                    VistaUtil.class.getResource(CSS).toExternalForm());
        }
    }
}
