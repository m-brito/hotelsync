package br.edu.ifsp.hotelsync.domain.persistence.dao;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.util.Dao;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckInReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckOutReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.FinancialReport;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao extends Dao<Long, Reservation> {
    DailyOccupationReport getDailyOccupationReport(LocalDate initialDate, LocalDate finalDate, RoomDao roomRepository);

    CheckInReport getCheckInReport(LocalDate initialDate, LocalDate finalDate);

    CheckOutReport getCheckOutReport(LocalDate initialDate, LocalDate finalDate);

    FinancialReport getFinancialReport(LocalDate initialDate, LocalDate finalDate);

}
