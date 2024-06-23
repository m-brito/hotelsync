package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ReportManagementController {
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
    private Button checkInButton;

    @FXML
    private Button checkOutButton;

    @FXML
    private Button dailyOcuppationButton;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private DatePicker endDaePicker;

    @FXML
    private Button exportBtn;

    @FXML
    private ComboBox<Type> exportTypeCombo;

    @FXML
    private Button financialButton;

    @FXML
    private Pane pnlOverview;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<?, ?> valueColumn;

    @FXML
    private TableView<?> tableFinancial;

    @FXML
    public void initialize() {
        exportTypeCombo.getItems().addAll(Type.values());
    }

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    void handleExit(ActionEvent event) { new ExitHandler().handleExit(event); }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException{
        navHandler.navigateToGuestManagementPage();
    }

    @FXML
    void handleProductPage(ActionEvent actionEvent) throws IOException {
        navHandler.navigateToProductManagementPage();
    }

    @FXML
    void handleReportPage(ActionEvent event) throws IOException {
        navHandler.navigateToReportPage();
    }

    @FXML
    void handleReservationPage(ActionEvent event) throws IOException {
        navHandler.navigateToReservationManagementPage();
    }

    @FXML
    void handleRoomPage(ActionEvent event) throws IOException {
        navHandler.navigateToRoomManagementPage();
    }
}
