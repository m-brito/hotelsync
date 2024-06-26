package br.edu.ifsp.hotelsync.application.main;


import br.edu.ifsp.hotelsync.application.repository.sqlite.DatabaseBuilder;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteGuestDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteProductDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteReservationDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteRoomDao;
import br.edu.ifsp.hotelsync.application.util.DateFormatter;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.usecases.guest.create.CreateGuestUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.guest.create.CreateGuestUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.guest.find.*;
import br.edu.ifsp.hotelsync.domain.usecases.guest.update.UpdateGuestUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.guest.update.UpdateGuestUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindAllProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindAllProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindOneProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindOneProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.update.UpdateProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.update.UpdateProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.*;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.PdfExportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.PdfExportUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.*;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.*;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.*;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.room.find.*;
import br.edu.ifsp.hotelsync.domain.usecases.room.update.UpdateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.update.UpdateRoomUseCaseImpl;

import java.time.LocalDate;

public class Main {
    public static CreateGuestUseCase createGuestUseCase;
    public static FindAllGuestUseCase findAllGuestUseCase;
    public static FindAllOwnerUseCase findAllOwnerUseCase;
    public static FindOneGuestUseCase findOneGuestUseCase;
    public static UpdateGuestUseCase updateGuestUseCase;

    public static CreateRoomUseCase createRoomUseCase;
    public static FindAllRoomUseCase findAllRoomUseCase;
    public static FindAllAvailableRoomUseCase findAllAvailableRoomUseCase;
    public static FindOneRoomUseCase findOneRoomUseCase;
    public static UpdateRoomUseCase updateRoomUseCase;

    public static CreateProductUseCase createProductUseCase;
    public static FindAllProductUseCase findAllProductUseCase;
    public static FindOneProductUseCase findOneProductUseCase;
    public static UpdateProductUseCase updateProductUseCase;

    public static CreateReservationUseCase createReservationUseCase;
    public static FindAllReservationUseCase findAllReservationUseCase;
    public static FindAllReservationByOwnerUseCase findAllReservationByOwnerUseCase;
    public static FindOneReservationUseCase findOneReservationUseCase;
    public static AddConsumedProductUseCase addConsumedProductUseCase;
    public static AddGuestUseCase addGuestUseCase;
    public static CancelReservationUseCase cancelReservationUseCase;
    public static CheckInUseCase checkInUseCase;
    public static CheckOutUseCase checkOutUseCase;
    public static RemoveConsumedProductUseCase removeConsumedProductUseCase;

    public static CreateReportUseCase createCheckInReport;
    public static CreateReportUseCase createCheckOutReport;
    public static CreateReportUseCase createDailyOccupationReport;
    public static CreateReportUseCase createFinancialReport;

    public static PdfExportUseCase pdfExportUseCase;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        Home.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        GuestDao guestDao = new SqliteGuestDao();
        createGuestUseCase = new CreateGuestUseCaseImpl(guestDao);
        findAllGuestUseCase = new FindAllGuestUseCaseImpl(guestDao);
        findAllOwnerUseCase = new FindAllOwnerUseCaseImpl(guestDao);
        findOneGuestUseCase = new FindOneGuestUseCaseImpl(guestDao);
        updateGuestUseCase = new UpdateGuestUseCaseImpl(guestDao);

        RoomDao roomDao = new SqliteRoomDao() ;
        createRoomUseCase = new CreateRoomUseCaseImpl(roomDao);
        findAllRoomUseCase = new FindAllRoomUseCaseImpl(roomDao);
        findAllAvailableRoomUseCase = new FindAllAvailableRoomUseCaseImpl(roomDao);
        findOneRoomUseCase = new FindOneRoomUseCaseImpl(roomDao);
        updateRoomUseCase = new UpdateRoomUseCaseImpl(roomDao);

        ProductDao productDao = new SqliteProductDao();
        createProductUseCase = new CreateProductUseCaseImpl(productDao);
        findAllProductUseCase = new FindAllProductUseCaseImpl(productDao);
        findOneProductUseCase = new FindOneProductUseCaseImpl(productDao);
        updateProductUseCase = new UpdateProductUseCaseImpl(productDao);

        ReservationDao reservationDao = new SqliteReservationDao();
        createReservationUseCase = new CreateReservationUseCaseImpl(reservationDao);
        findAllReservationUseCase = new FindAllReservationUseCaseImpl(reservationDao);
        findAllReservationByOwnerUseCase = new FindAllReservationByOwnerUseCaseImpl(reservationDao);
        findOneReservationUseCase = new FindOneReservationUseCaseImpl(reservationDao);
        addConsumedProductUseCase = new AddConsumedProductUseCaseImpl(productDao, reservationDao);
        addGuestUseCase = new AddGuestUseCaseImpl(guestDao, reservationDao);
        cancelReservationUseCase = new CancelReservationUseCaseImpl(reservationDao);
        checkInUseCase = new CheckInUseCaseImpl(reservationDao, roomDao);
        checkOutUseCase = new CheckOutUseCaseImpl(reservationDao, roomDao);
        removeConsumedProductUseCase = new RemoveConsumedProductUseCaseImpl(productDao, reservationDao);

        createCheckInReport = new CreateCheckInReportUseCase(reservationDao);
        createCheckOutReport = new CreateCheckOutReportUseCase(reservationDao);
        createDailyOccupationReport = new CreateDailyOccupationReportUseCase(roomDao, reservationDao);
        createFinancialReport = new CreateFinancialReportUseCase(reservationDao);

        pdfExportUseCase = new PdfExportUseCaseImpl();
    }
}
