package br.edu.ifsp.hotelsync.application.controller.createControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CreateProductController {

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
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TextField descriptionField;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<?> productsTable;

    @FXML
    public void initialize() {
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

}
