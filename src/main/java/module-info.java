//module br.edu.ifsp.hotelsync {
//    requires javafx.controls;
//    requires javafx.fxml;
//
//
//    opens br.edu.ifsp.hotelsync to javafx.fxml;
//    exports br.edu.ifsp.hotelsync;
//    exports br.edu.ifsp.hotelsync.application.view;
//    opens br.edu.ifsp.hotelsync.application.view to javafx.fxml;
//}
module br.edu.ifsp.hotelsync {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsp.hotelsync.application.view to javafx.fxml;
    opens br.edu.ifsp.hotelsync.application.controller to javafx.fxml;

    exports br.edu.ifsp.hotelsync.application.view;
    exports br.edu.ifsp.hotelsync.application.controller;
}