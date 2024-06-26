package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.controller.ProductController;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindAllProductByNameUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.FindAllReservationByOwnerUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

public class ProductManagementController {
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
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TextField searchProduct;

    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private Button updateProductButton;

    private final NavigationHandler navHandler =
            new NavigationHandler();

    private ObservableList<Product> tableData;

    @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValuesSources();
        populateTable();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableProduct.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void showProductInMode(UIMode mode) throws IOException {
        Product selectedItem = tableProduct.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            navHandler.navigateToProductPage();
            ProductController controller = (ProductController) Home.getController();
            controller.setEntity(selectedItem, mode);
        }
    }

    public void populateTable() {
        Map<Long, Product> products = findAllProductUseCase.findAll();
        tableData.clear();
        tableData.addAll(products.values());
    }

    @FXML
    void handleExit(ActionEvent event) {
        new ExitHandler().handleExit(event);
    }

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

    @FXML
    void handleCreateProduct(ActionEvent event) throws IOException {
        navHandler.navigateToProductPage();
    }

    @FXML
    public void handleUpdateProduct(ActionEvent actionEvent) throws IOException {
        showProductInMode(UIMode.UPDATE);
    }

    @FXML
    public void handleImageClick(MouseEvent mouseEvent) {
        Map<Long, Product> products = findAllProductByNameUseCase.findAllByName(
                new FindAllProductByNameUseCase.RequestModel(searchProduct.getText()));
        searchProduct.setText("");
        System.out.println(products.size());
        tableData.clear();
        tableData.addAll(products.values());
    }
}
