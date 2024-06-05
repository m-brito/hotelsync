package br.edu.ifsp.hotelsync.domain.usecases.reports;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

public class CreateCheckInReportUseCase implements CreateReportUseCase {

    private final ReservationDao reservationRepository;

    public CreateCheckInReportUseCase(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Exportable createReport(RequestModel request) {
        return reservationRepository.getCheckInReport(request.initialDate(), request.finalDate());
    }
}
