module co.edu.uniquindio.poo.parqueadero {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.parqueadero to javafx.fxml;
    exports co.edu.uniquindio.poo.parqueadero;
}