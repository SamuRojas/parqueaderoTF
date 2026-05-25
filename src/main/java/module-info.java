module co.edu.uniquindio.poo.parqueadero {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.parqueadero to javafx.fxml;
    opens co.edu.uniquindio.poo.parqueadero.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.parqueadero;
    exports co.edu.uniquindio.poo.parqueadero.controller;
    exports co.edu.uniquindio.poo.parqueadero.model;
    exports co.edu.uniquindio.poo.parqueadero.exception;
}
