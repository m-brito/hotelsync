package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.update.UpdateProductUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

public class ProductController {

    @FXML
    private Button addProductBtn;

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
    private Button cancelProductBtn;

    @FXML
    private TextField descriptionField;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField priceField;

    @FXML
    private Label viewTitle;

    private Product product;

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void initialize() {

    }

    public void setEntity(Product product, UIMode mode) {
        if (product == null)
            throw new IllegalArgumentException("Product can not be null.");

        this.product = product;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    private void setEntityIntoView() {
        if (product != null) {
            viewTitle.setText("Update Product");
            descriptionField.setText(product.getDescription());
            priceField.setText(String.valueOf(product.getPrice()));
        }
    }

    private void configureViewMode() {
        descriptionField.setDisable(true);
        priceField.setDisable(true);
    }

    private void getEntityToView() {
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        if(product == null) {
            product = Product.createProduct(description, price);
        } else {
            product.setDescription(description);
            product.setPrice(price);
        }
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private void saveOrUpdate() {
        try {
            getEntityToView();
            if (product == null) return;
            if (product.getId() == null) {
                createProductUseCase.createProduct(new CreateProductUseCase.RequestModel(product.getDescription(), product.getPrice()));
            } else {
                updateProductUseCase.updateProduct(new UpdateProductUseCase.RequestModel(product.getId(), product.getDescription(), product.getPrice()));
            }

            navHandler.navigateToProductManagementPage();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    @FXML
    void handleExit(ActionEvent event) {
        new ExitHandler().handleExit(event);
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException {
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
    public void onSaveProduct(ActionEvent actionEvent) {
        saveOrUpdate();
    }

    public void handleCancelProduct(ActionEvent actionEvent) throws IOException {
        navHandler.navigateToProductManagementPage();
    }
}
