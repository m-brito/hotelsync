module br.edu.ifsp.hotelsync {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.edu.ifsp.hotelsync.application.view to javafx.fxml;
    opens br.edu.ifsp.hotelsync.application.controller to javafx.fxml;

    exports br.edu.ifsp.hotelsync.application.view;
    exports br.edu.ifsp.hotelsync.application.controller;
}