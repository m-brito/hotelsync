package br.edu.ifsp.hotelsync.application.util;

import br.edu.ifsp.hotelsync.application.view.Home;

import java.io.IOException;

public class NavigationHandler {

    public void navigateToGuestPage() throws IOException {
        Home.setRoot("views/entitiesViews/guest");
    }

    public void navigateToProductPage() throws IOException {
        Home.setRoot("views/entitiesViews/product");
    }

    public void navigateToReportPage() throws IOException {
        Home.setRoot("views/useCasesViews/reportViews/generateReports");
    }

    public void navigateToReservationPage() throws IOException {
        Home.setRoot("views/entitiesViews/reservation");
    }

    public void navigateToRoomPage() throws IOException {
        Home.setRoot("views/entitiesViews/room");
    }

    public void handleCreateReservation() throws IOException {
        Home.setRoot("views/useCasesViews/createViews/createReservation");
    }

    public  void handleCreateProduct() throws IOException {
        Home.setRoot("views/useCasesViews/createViews/createProduct");
    }

    public void handleCreateGuest() throws IOException {
        Home.setRoot("views/useCasesViews/createViews/createGuest");
    }

    public void navigateToCreateRoomPage() throws IOException {
        Home.setRoot("views/useCasesViews/createViews/createRoom");
    }
}
