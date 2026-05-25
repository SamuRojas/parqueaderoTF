package co.edu.uniquindio.poo.parqueadero;


import co.edu.uniquindio.poo.parqueadero.controller.VistaUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/co.edu.uniquindio.poo.parqueadero/view/Inicio.fxml"
                        )
                );

        Scene scene = new Scene(loader.load(), 900, 560);
        VistaUtil.aplicarEstilos(scene);

        stage.setScene(scene);
        stage.setTitle("PARKUQ - Parqueadero UQ");
        stage.centerOnScreen();
        stage.setMinWidth(600);
        stage.setMinHeight(450);
        stage.show();
    }
}