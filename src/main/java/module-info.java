module br.edu.ifsp.hotelsync {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;

    opens br.edu.ifsp.hotelsync.application.view to javafx.fxml;

    exports br.edu.ifsp.hotelsync.application.view;
    exports br.edu.ifsp.hotelsync.application.controller.addControllers;
    opens br.edu.ifsp.hotelsync.application.controller.addControllers to javafx.fxml;
    exports br.edu.ifsp.hotelsync.application.controller.entitiesControllers;
    opens br.edu.ifsp.hotelsync.application.controller.entitiesControllers to javafx.fxml;
    exports br.edu.ifsp.hotelsync.application.controller;
    opens br.edu.ifsp.hotelsync.application.controller to javafx.fxml;
    exports br.edu.ifsp.hotelsync.application.view.entitiesViews;
    opens br.edu.ifsp.hotelsync.application.view.entitiesViews to javafx.fxml;
}