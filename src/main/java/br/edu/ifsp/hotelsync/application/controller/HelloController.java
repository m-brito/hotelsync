package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}