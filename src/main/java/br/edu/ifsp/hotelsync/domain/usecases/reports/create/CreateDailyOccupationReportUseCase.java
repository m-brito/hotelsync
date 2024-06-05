package br.edu.ifsp.hotelsync.domain.usecases.reports.create;

import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

public class CreateDailyOccupationReportUseCase implements CreateReportUseCase {

    private final RoomDao roomRepository;
    private final ReservationDao reservationRepository;

    public CreateDailyOccupationReportUseCase(RoomDao roomRepository, ReservationDao reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Exportable createReport(RequestModel request) {
        return reservationRepository.getDailyOccupationReport(
                request.initialDate(), request.finalDate(), roomRepository);
    }
}
