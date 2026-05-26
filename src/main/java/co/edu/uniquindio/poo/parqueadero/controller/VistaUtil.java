package co.edu.uniquindio.poo.parqueadero.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class VistaUtil {

    private static final String CSS = "/co.edu.uniquindio.poo.parqueadero/css/parkuq.css";

    /**
     * Metodo que permite aplicar la hoja de estilos CSS del proyecto
     * a un nodo raiz de tipo Parent, limpiando primero cualquier estilo previo
     * @param root nodo raiz de la pantalla al que se le aplicaran los estilos
     */

    public static void aplicarEstilos(Parent root) {
        if (root != null && root.getScene() != null) {
            root.getScene().getStylesheets().clear();
            root.getScene().getStylesheets().add(
                    VistaUtil.class.getResource(CSS).toExternalForm());
        }
    }

    /**
     * Metodo que permite aplicar la hoja de estilos CSS del proyecto
     * directamente a una escena, limpiando primero cualquier estilo previo
     * @param scene escena a la que se le aplicaran los estilos
     */

    public static void aplicarEstilos(Scene scene) {
        if (scene != null) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(
                    VistaUtil.class.getResource(CSS).toExternalForm());
        }
    }
}
