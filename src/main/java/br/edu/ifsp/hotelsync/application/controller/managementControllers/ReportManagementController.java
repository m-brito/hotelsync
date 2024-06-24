package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.util.AlertHelper;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.Type;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateReportUseCase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

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
    private TableColumn<Map.Entry<LocalDate, ?>, String> dateColumn;

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
    private TableColumn<Map.Entry<LocalDate, ?>, String> valueColumn;

    @FXML
    private TableView<Map.Entry<LocalDate, ?>> tableFinancial;

    private ObservableList<Map.Entry<LocalDate, ?>> tableData;

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
        dateColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKey().toString()));
        valueColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getValue().toString()));
    }

    public void handleCheckInReport(ActionEvent actionEvent) {
        try{
            if(startDatePicker.getValue() == null || endDatePicker.getValue() == null) throw new IllegalArgumentException("Please select a start and end date");
            Exportable<LocalDate, Integer> report = createCheckInReport.createReport(new CreateReportUseCase.RequestModel(
                    startDatePicker.getValue(),
                    endDatePicker.getValue()));
            tableData.clear();
            tableData.addAll(report.getReport().entrySet());
        } catch (Exception e){
            AlertHelper.showErrorAlert("Error", "Error", e.getMessage());
        }
    }

    public void handleCheckOutReport(ActionEvent actionEvent) {
        try{
            if(startDatePicker.getValue() == null || endDatePicker.getValue() == null) throw new IllegalArgumentException("Please select a start and end date");
            Exportable<LocalDate, Integer> report = createCheckOutReport.createReport(new CreateReportUseCase.RequestModel(
                    startDatePicker.getValue(),
                    endDatePicker.getValue()));
            tableData.clear();
            tableData.addAll(report.getReport().entrySet());
        } catch (Exception e){
            AlertHelper.showErrorAlert("Error", "Error", e.getMessage());
        }
    }

    public void handleFinancialReport(ActionEvent actionEvent) {
        try{
            if(startDatePicker.getValue() == null || endDatePicker.getValue() == null) throw new IllegalArgumentException("Please select a start and end date");
            Exportable<LocalDate, Double> report = createFinancialReport.createReport(new CreateReportUseCase.RequestModel(
                    startDatePicker.getValue(),
                    endDatePicker.getValue()));
            tableData.clear();
            tableData.addAll(report.getReport().entrySet());
        } catch (Exception e){
            AlertHelper.showErrorAlert("Error", "Error", e.getMessage());
        }
    }

    public void handleDailyOcuppationReport(ActionEvent actionEvent) {
        try{
            if(startDatePicker.getValue() == null || endDatePicker.getValue() == null) throw new IllegalArgumentException("Please select a start and end date");
            Exportable<LocalDate, Double> report = createDailyOccupationReport.createReport(new CreateReportUseCase.RequestModel(
                    startDatePicker.getValue(),
                    endDatePicker.getValue()));
            tableData.clear();
            tableData.addAll(report.getReport().entrySet());
        } catch (Exception e){
            AlertHelper.showErrorAlert("Error", "Error", e.getMessage());
        }
    }
}
