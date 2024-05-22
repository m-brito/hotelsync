package br.edu.ifsp.hotelsync.domain.entities.reservation;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Guest owner;
    private Room room;
    private ReservationStatus reservationStatus;
    private List<Guest> guests = new ArrayList<>();
    private List<Product> consumedProducts = new ArrayList<>();
    private Payment payment;

    public Reservation(Long id, LocalDate checkInDate, LocalDate checkOutDate, Guest owner, Room room, ReservationStatus reservationStatus) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.owner = owner;
        this.room = room;
        this.reservationStatus = reservationStatus;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Guest owner, Room room, ReservationStatus reservationStatus) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.owner = owner;
        this.room = room;
        this.reservationStatus = reservationStatus;
    }



    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Guest getOwner() {
        return owner;
    }

    public void setOwner(Guest owner) {
        this.owner = owner;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public List<Guest> getGuests() {
        List<Guest> returnedGuests = new ArrayList<>(guests);
        return returnedGuests;
    }

    public List<Product> getConsumedProducts() {
        List<Product> returnedConsumedProducts = new ArrayList<>(consumedProducts);
        return returnedConsumedProducts;
    }
}
