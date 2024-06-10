package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Long> idColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    private ObservableList<Product> products;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        products = FXCollections.observableArrayList();
        productsTable.setItems(products);
    }

    @FXML
    protected void onCreateProductClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/hotelsync/application/view/create_product.fxml"));
            Scene createProductScene = new Scene(fxmlLoader.load(), 800, 600); // Definindo o tamanho da cena para 800x600

            Stage stage = (Stage) welcomeText.getScene().getWindow();
            stage.setScene(createProductScene);
        } catch (IOException e) {
          //adicionar exceção
        }
    }

    @FXML
    protected void onFindProductClick() {
    }

    @FXML
    protected void onUpdateProductClick() {
    }


}