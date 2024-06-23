package br.edu.ifsp.hotelsync.application.util;

import br.edu.ifsp.hotelsync.application.view.Home;

import java.io.IOException;

public class NavigationHandler {

    public void navigateToGuestManagementPage() throws IOException {
        Home.setRoot("views/managementViews/guestManagement");
    }

    public void navigateToProductManagementPage() throws IOException {
        Home.setRoot("views/managementViews/productManagement");
    }

    public void navigateToRoomManagementPage() throws IOException {
        Home.setRoot("views/managementViews/roomManagement");
    }

    public void navigateToReservationManagementPage() throws IOException {
        Home.setRoot("views/managementViews/reservationManagement");
    }

    public void navigateToGuestPage() throws IOException {
        Home.setRoot("views/useCasesViews/guest");
    }

    public  void navigateToProductPage() throws IOException {
        Home.setRoot("views/useCasesViews/product");
    }

    public void navigateToRoomPage() throws IOException {
        Home.setRoot("views/useCasesViews/room");
    }

    public void navigateToReservationPage() throws IOException {
        Home.setRoot("views/useCasesViews/reservation");
    }

    public void navigateToHomePage() throws IOException{
        Home.setRoot("home");
    }

    public void navigateToReportPage() throws IOException {
        Home.setRoot("views/useCasesViews/reportViews/generateReports");
    }
}
