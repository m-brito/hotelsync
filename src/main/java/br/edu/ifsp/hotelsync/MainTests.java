package br.edu.ifsp.hotelsync;

import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryGuestDao;
import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryProductDao;
import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryReservationDao;
import br.edu.ifsp.hotelsync.application.repository.inmemory.InMemoryRoomDao;
import br.edu.ifsp.hotelsync.domain.entities.guest.Address;
import br.edu.ifsp.hotelsync.domain.entities.guest.Cpf;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.guest.Phone;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.Exporter;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.PdfExporterImpl;
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
import br.edu.ifsp.hotelsync.domain.usecases.reports.create.CreateReportUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.CheckInUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation.CheckOutUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckInUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckOutUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCaseImpl;

import java.time.LocalDate;

public class MainTests {
    public static void main(String[] args) {
        GuestDao guestDao = new InMemoryGuestDao();
        ProductDao productDao = new InMemoryProductDao();
        ReservationDao reservationDao = new InMemoryReservationDao();
        RoomDao roomDao = new InMemoryRoomDao();

        CreateGuestUseCase createGuestUseCase = new CreateGuestUseCaseImpl(guestDao);
        CreateProductUseCase createProductUseCase = new CreateProductUseCaseImpl(productDao);
        CreateReservationUseCase createReservationUseCase = new CreateReservationUseCaseImpl(reservationDao);
        CreateRoomUseCase createRoomUseCase = new CreateRoomUseCaseImpl(roomDao);
        CreateDailyOccupationReportUseCase createDailyOccupationReportUseCase = new CreateDailyOccupationReportUseCase(roomDao, reservationDao);
        CheckInUseCaseImpl checkInUseCase = new CheckInUseCaseImpl(reservationDao);
        CheckOutUseCaseImpl checkOutUseCase = new CheckOutUseCaseImpl(reservationDao);

        Exporter exporterPdf = new PdfExporterImpl("relatorio.pdf");
        Formatter<LocalDate, Double, DailyOccupationReport> simpleFormatter = new SimpleTextFormatter<>();

        Room room1 = Room.createRoom(1, 2, "King", RoomCategory.EXECUTIVE, "Quarto executivo", RoomStatus.AVAILABLE, 15);
        createRoomUseCase.createRoom(
                new CreateRoomUseCase.RequestModel(
                        room1.getNumber(),
                        room1.getNumberOfBeds(),
                        room1.getTypeOfBed(),
                        room1.getRoomCategory(),
                        room1.getRoomStatus(),
                        room1.getDescription(),
                        room1.getArea()
                )
        );

        Product product1 = Product.createProduct("Coca-Cola", 12.50);
        createProductUseCase.createProduct(
                new CreateProductUseCase.RequestModel(
                        product1.getDescription(),
                        product1.getPrice()
                )
        );

        Guest owner1 = Guest.createOwner(
                "Mauricio",
                "Ele",
                LocalDate.of(2003, 6, 11),
                new Phone("(11) 91234-1234"),
                new Cpf("123.123.123-12"),
                new Address(
                        "Rua Joaquim Pereira",
                        "São Carlos",
                        "São Paulo",
                        "13123-012",
                        "São Paulo",
                        "Oficina do Jao"
                )
        );
        createGuestUseCase.createOwner(
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
        );
        Payment payment1 = new Payment(20, LocalDate.of(2024, 6, 10), "Debito");
        Reservation reservation1 = Reservation.createReservation(
                LocalDate.of(2024, 6, 5),
                LocalDate.of(2024, 6, 10),
                owner1,
                room1,
                payment1
        );
        createReservationUseCase.createReservation(
                new CreateReservationUseCase.RequestModel(
                        reservation1.getStartDate(),
                        reservation1.getCheckInDate(),
                        reservation1.getEndDate(),
                        reservation1.getCheckOutDate(),
                        reservation1.getOwner(),
                        reservation1.getRoom(),
                        reservation1.getReservationStatus(),
                        reservation1.getGuests(),
                        reservation1.getConsumedProducts(),
                        reservation1.getPayment()
                )
        );
        System.out.println(reservation1.getId());
        checkInUseCase.doCheckIn(new CheckInUseCase.RequestModel(reservation1.getId()));
        checkOutUseCase.doCheckOut(new CheckOutUseCase.RequestModel(reservation1.getId()));

        Exportable dataToExport = createDailyOccupationReportUseCase.createReport(
                new CreateReportUseCase.RequestModel(
                        LocalDate.of(2024, 6, 1),
                        LocalDate.of(2024, 6, 11)
                )
        );

        exporterPdf.export(dataToExport, simpleFormatter);
    }
}