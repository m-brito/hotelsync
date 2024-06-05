package br.edu.ifsp.hotelsync.domain.persistence.dao;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.util.Dao;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.CheckInReport;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.CheckOutReport;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.FinancialReport;

import java.time.LocalDate;

public interface ReservationDao extends Dao<Long, Reservation> {
    DailyOccupationReport getDailyOccupationReport(LocalDate initialDate, LocalDate finalDate, RoomDao roomRepository);

    CheckInReport getCheckInReport(LocalDate initialDate, LocalDate finalDate);

    CheckOutReport getCheckOutReport(LocalDate initialDate, LocalDate finalDate);

    FinancialReport getFinancialReport(LocalDate initialDate, LocalDate finalDate);
}
