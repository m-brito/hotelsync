package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Cursor;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HomeController {

    @FXML
    private AnchorPane bakcground;

    @FXML
    private ImageView githubIcon;

    @FXML
    private ImageView javaIcon;

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
    private TextField inputSearch;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

   @FXML
    public void initialize() {
       githubIcon.setCursor(Cursor.HAND);
    }

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    private final NavigationHandler navigationHandler = new NavigationHandler();


    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
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
    void handleGithubIconClick() throws IOException {
        navigationHandler.navigateToGithubPage();
    }
}
