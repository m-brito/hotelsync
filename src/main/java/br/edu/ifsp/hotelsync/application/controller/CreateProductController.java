package br.edu.ifsp.hotelsync.application.controller;


import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateProductController {
    @FXML
    public Button btnOverview;
    @FXML
    public Button btnPackages;
    @FXML
    public Button btnCustomers;
    @FXML
    public Button btnMenus;
    @FXML
    public Button btnOrders;
    @FXML
    public Button btnSettings;
    @FXML
    public Button btnSignout;
    @FXML
    public Pane pnlOverview;

    @FXML
    private TextField descriptionField;
    @FXML
    private TextField priceField;


    private CreateProductUseCase createProductUseCase;
    public CreateProductController() {
    }

    @FXML
    protected void onCreateProductClick() {
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());

        CreateProductUseCase.RequestModel request = new CreateProductUseCase.RequestModel(description, price);
        Long productId = createProductUseCase.createProduct(request);
    }

    @FXML
    protected void onCancelProductClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Creation");
        alert.setHeaderText("Are you sure you want to cancel product creation?");
        alert.setContentText("If you cancel, all unsaved changes will be lost.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/hotelsync/application/view/home.fxml"));
                    Scene homeScene = new Scene(fxmlLoader.load(), 800, 600);

                    Stage stage = (Stage) descriptionField.getScene().getWindow();
                    stage.setScene(homeScene);
                } catch (IOException e) {
                   //criar excecao
                }
            }
        });
    }

    public void handleClicks(ActionEvent actionEvent) {
    }
}