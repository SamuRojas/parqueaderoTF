package co.edu.uniquindio.poo.parqueadero;


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
                                "/co.edu.uniquindio.poo.parqueadero/"
                        )
                );

        Scene scene =
                new Scene(loader.load());

        stage.setScene(scene);

        stage.show();
    }
}