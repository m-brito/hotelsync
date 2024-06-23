package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteReservationDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteRoomDao;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.Type;
import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.formatter.SimpleTextFormatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckInReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateDailyOccupationReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.PdfExportUseCaseImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static br.edu.ifsp.hotelsync.application.main.Main.createCheckInReport;

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
    private TableColumn<Exportable, String> dateColumn;

    @FXML
    private DatePicker endDatePicker;

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
    private TableColumn<Exportable, String> valueColumn;

    @FXML
    private TableView<Exportable> tableFinancial;

    private ObservableList<Exportable> tableData;

    @FXML
    public void initialize() {
        exportTypeCombo.getItems().addAll(Type.values());
        bindTableViewToItemsList();
        bindColumnsToValuesSources();
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

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableFinancial.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        dateColumn.setCellValueFactory(cell -> {
            Object cellValue = cell.getValue();
            if (cellValue instanceof Exportable) {
                Exportable exportable = (Exportable) cellValue;
                String key = exportable.getReport().keySet().isEmpty() ? "" : exportable.getReport().keySet().iterator().next().toString();
                return new SimpleStringProperty(key);
            } else {
                // Handle the case when cellValue is not an Exportable
                return new SimpleStringProperty(cellValue.toString());
            }
        });
    }

    public void handleCheckInReport(ActionEvent actionEvent) {
        Exportable report = createCheckInReport.createReport(new CreateReportUseCase.RequestModel(
                startDatePicker.getValue(),
                endDatePicker.getValue()));
        tableData.clear();
        tableData.addAll(report.getReport().values());
    }
}
