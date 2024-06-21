package br.edu.ifsp.hotelsync.application.controller.updateControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UpdateProductController {
    @FXML
    private Button addProductBtnUpdate;

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
    private Button cancelProductBtnUpdate;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TextField descriptionFieldUpdate;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TextField priceFieldUpdate;

    @FXML
    private TableView<?> productsTable;

    private final NavigationHandler navHandler =
            new NavigationHandler();

    private final ExitHandler exitHandler =
            new ExitHandler();

    @FXML
    public void initialize() {
    }

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleAddProduct(ActionEvent event) throws IOException {
        navHandler.navigateToProductPage();
    }

    @FXML
    void handleCancelProduct(ActionEvent event) throws IOException  {
        navHandler.navigateToProductPage();
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException {
        navHandler.navigateToGuestPage();
    }

    @FXML
    void handleProductPage(ActionEvent event) throws IOException {
        navHandler.navigateToProductPage();
    }

    @FXML
    void handleReportPage(ActionEvent event) throws IOException {
        navHandler.navigateToReportPage();
    }

    @FXML
    void handleReservationPage(ActionEvent event) throws IOException {
        navHandler.navigateToReservationPage();
    }

    @FXML
    void handleRoomPage(ActionEvent event) throws IOException {
        navHandler.navigateToRoomPage();
    }
}


