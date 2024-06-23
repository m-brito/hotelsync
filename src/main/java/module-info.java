module br.edu.ifsp.hotelsync {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires sqlite.jdbc;

    opens br.edu.ifsp.hotelsync.application.view to javafx.fxml;

    exports br.edu.ifsp.hotelsync.application.view;
    exports br.edu.ifsp.hotelsync.application.controller;
    exports br.edu.ifsp.hotelsync.application.controller.managementControllers;

    opens br.edu.ifsp.hotelsync.application.controller.managementControllers to javafx.fxml;
    opens br.edu.ifsp.hotelsync.application.controller to javafx.fxml;

    opens br.edu.ifsp.hotelsync.domain.entities.product to javafx.base;
    opens br.edu.ifsp.hotelsync.domain.entities.guest to javafx.base;
    opens br.edu.ifsp.hotelsync.domain.entities.room to javafx.base;
    opens br.edu.ifsp.hotelsync.domain.entities.reservation to javafx.base;
}
