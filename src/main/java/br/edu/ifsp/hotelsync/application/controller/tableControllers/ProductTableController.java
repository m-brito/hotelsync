package br.edu.ifsp.hotelsync.application.controller.tableControllers;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProductTableController {

    private final TableView<Product> productsTable;
    private final TableColumn<Product, String> descriptionColumn;
    private final TableColumn<Product, Double> priceColumn;

    public ProductTableController(TableView<Product> productsTable, TableColumn<Product, String> descriptionColumn, TableColumn<Product, Double> priceColumn) {
        this.productsTable = productsTable;
        this.descriptionColumn = descriptionColumn;
        this.priceColumn = priceColumn;
    }

    public void initialize() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void updateTable(List<Product> products) {
        ObservableList<Product> observableProducts = FXCollections.observableArrayList(products);
        productsTable.setItems(observableProducts);
    }
}