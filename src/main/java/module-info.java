module lk.ijse.sciencelab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;
    requires java.desktop;
    requires java.mail;

    opens lk.ijse.sciencelab to javafx.fxml;
    exports lk.ijse.sciencelab;

    exports lk.ijse.sciencelab.Controller;
    opens lk.ijse.sciencelab.Controller to javafx.fxml;

    opens lk.ijse.sciencelab.Dto;
    exports lk.ijse.sciencelab.Dto;

    // ✅ FIXED: open util to javafx.base for PropertyValueFactory to access fields reflectively
    opens lk.ijse.sciencelab.util to javafx.fxml, javafx.base;
}