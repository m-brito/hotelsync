package br.edu.ifsp.hotelsync.application.controller.entitiesControllers;

import br.edu.ifsp.hotelsync.application.view.Home;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProductController {
    @FXML
    private Button btnGuest;

    @FXML
    private Button btnProduct;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnRoom;

    @FXML
    private Button btnSignout;

    @FXML
    private Button createProductButton;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TextField searchProduct;

    @FXML
    private Button updateProductButton;


    @FXML
    public void initialize() {
    }

    @FXML
    void handleExit(ActionEvent event) {
        Alert alert = new Alert(Alert
                .AlertType
                .CONFIRMATION,
                "Do you really want to leave?",
                ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Departure Confirmation");
        alert.setHeaderText("Bye! \uD83D\uDC4B");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException {
        Home.setRoot("views/entitiesViews/guest");
    }

    @FXML
    void handleProductPage(ActionEvent actionEvent) throws IOException {
        Home.setRoot("views/entitiesViews/product");
    }

    @FXML
    void handleReportPage(ActionEvent event) throws IOException {
        Home.setRoot("views/useCasesViews/reportViews/generateReports");

    }

    @FXML
    void handleReservationPage(ActionEvent event) throws IOException {
        Home.setRoot("views/entitiesViews/reservation");

    }

    @FXML
    void handleRoomPage(ActionEvent event) throws IOException {
        Home.setRoot("views/entitiesViews/room");
    }

    @FXML
    void handleCreateProduct(ActionEvent event) throws IOException {
        Home.setRoot("views/useCasesViews/createViews/createProduct");
    }
}
