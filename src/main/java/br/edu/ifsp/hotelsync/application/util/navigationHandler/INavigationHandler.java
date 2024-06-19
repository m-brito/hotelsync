package br.edu.ifsp.hotelsync.application.util.navigationHandler;

import java.io.IOException;

public interface INavigationHandler {
    void navigateToGuestPage() throws IOException;
    void navigateToProductPage() throws IOException;
    void navigateToReportPage() throws IOException;
    void navigateToReservationPage() throws IOException;
    void navigateToRoomPage() throws IOException;
}