package br.edu.ifsp.hotelsync;

import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryGuestDao;
import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryProductDao;
import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryReservationDao;
import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryRoomDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteGuestDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteProductDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteReservationDao;
import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteRoomDao;
import br.edu.ifsp.hotelsync.domain.entities.guest.*;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.TerminalExporter;
import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.formatter.SimpleTextFormatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.usecases.guest.create.CreateGuestUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.guest.create.CreateGuestUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateDailyOccupationReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateFinancialReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.ExportReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.PdfExportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.PdfExportUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reports.export.TerminalExportReportUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.FindAllReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.FindAllReservationUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.AddConsumedProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.AddGuestUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.CheckInUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.CheckOutUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.AddConsumedProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.AddGuestUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckInUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckOutUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCaseImpl;

import java.time.LocalDate;

public class MainTests {
    public static void main(String[] args) {
        GuestDao guestDao = new SqliteGuestDao();
        ProductDao productDao = new SqliteProductDao();
        ReservationDao reservationDao = new SqliteReservationDao();
        RoomDao roomDao = new SqliteRoomDao();

        CreateGuestUseCase createGuestUseCase = new CreateGuestUseCaseImpl(guestDao);
        CreateProductUseCase createProductUseCase = new CreateProductUseCaseImpl(productDao);
        CreateReservationUseCase createReservationUseCase = new CreateReservationUseCaseImpl(reservationDao);
        CreateRoomUseCase createRoomUseCase = new CreateRoomUseCaseImpl(roomDao);
        CreateDailyOccupationReportUseCase createDailyOccupationReportUseCase = new CreateDailyOccupationReportUseCase(roomDao, reservationDao);
        CheckInUseCaseImpl checkInUseCase = new CheckInUseCaseImpl(reservationDao, roomDao);
        CheckOutUseCaseImpl checkOutUseCase = new CheckOutUseCaseImpl(reservationDao, roomDao);
        CreateFinancialReportUseCase createFinancialReportUseCase = new CreateFinancialReportUseCase(reservationDao);
        AddConsumedProductUseCase addConsumedProductUseCase = new AddConsumedProductUseCaseImpl(productDao, reservationDao);
        AddGuestUseCase addGuestUseCase = new AddGuestUseCaseImpl(guestDao, reservationDao);
        PdfExportUseCaseImpl pdfExport = new PdfExportUseCaseImpl();
        TerminalExporter terminalExporter = new TerminalExporter();
        FindAllReservationUseCase findAllReservationUseCase = new FindAllReservationUseCaseImpl(reservationDao);

        Formatter<LocalDate, Double, DailyOccupationReport> simpleFormatter = new SimpleTextFormatter<>();


        Room room1 = Room.createRoom(1, 2, "King", RoomCategory.EXECUTIVE, "Quarto executivo", RoomStatus.AVAILABLE, 15);
        room1.setId(createRoomUseCase.createRoom(
                new CreateRoomUseCase.RequestModel(
                        1,
                        2,
                        "King",
                        RoomCategory.EXECUTIVE,
                        RoomStatus.AVAILABLE,
                        "Quarto executivo",
                        15
                )
        ));

        Product product1 = Product.createProduct("Coca-Cola", 12.50);
        product1.setId(createProductUseCase.createProduct(
                new CreateProductUseCase.RequestModel(
                        product1.getDescription(),
                        product1.getPrice()
                )
        ));

        Guest owner1 = Guest.createOwner(
                "Lucio",
                "Ele",
                LocalDate.of(2003, 6, 11),
                new Phone("(11) 91234-1234"),
                new Cpf("123.123.123-12"),
                new Address(
                        "Rua Joaquim Pereira",
                        "São Carlos",
                        State.SAO_PAULO,
                        "13123-012",
                        "São Paulo",
                        "Oficina do Jao"
                )
        );
        owner1.setId(createGuestUseCase.createOwner(
                new CreateGuestUseCase.OwnerRequestModel(
                        owner1.getName(),
                        owner1.getPronouns(),
                        owner1.getBirthdate(),
                        owner1.getPhone().getValue(),
                        owner1.getCpf().getValue(),
                        owner1.getAddress().road(),
                        owner1.getAddress().city(),
                        owner1.getAddress().state(),
                        owner1.getAddress().getCep(),
                        owner1.getAddress().district(),
                        owner1.getAddress().complement()
                )
        ));

        Reservation reservation1 = Reservation.createReservation(
                LocalDate.of(2024, 6, 5),
                LocalDate.of(2024, 7, 10),
                owner1,
                room1
        );


        reservation1.setId(createReservationUseCase.createReservation(
                new CreateReservationUseCase.RequestModel(
                        reservation1.getStartDate(),
                        reservation1.getEndDate(),
                        reservation1.getOwner(),
                        reservation1.getRoom())
                )
        );
        addGuestUseCase.addGuest(new AddGuestUseCase.RequestModel(owner1.getId(), reservation1.getId()));

        checkInUseCase.doCheckIn(new CheckInUseCase.RequestModel(reservation1.getId()));
        addConsumedProductUseCase.addConsumedProduct(new AddConsumedProductUseCase.RequestModel(reservation1.getId(), product1.getId(), 5));

        checkOutUseCase.doCheckOut(new CheckOutUseCase.RequestModel(reservation1.getId(), Payment.DEBIT));

        Exportable dataToExport = createDailyOccupationReportUseCase.createReport(
                new CreateReportUseCase.RequestModel(
                        LocalDate.of(2024, 6, 1),
                        LocalDate.of(2024, 7, 11)
                )
        );
        PdfExportUseCase.RequestModel request = new PdfExportUseCase.RequestModel(dataToExport, simpleFormatter, "relatorio.pdf");
        terminalExporter.export(dataToExport, simpleFormatter);
//        pdfExport.exportPdf(request);

        System.out.println(findAllReservationUseCase.findAll());


    }
}
