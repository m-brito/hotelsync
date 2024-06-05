package br.edu.ifsp.hotelsync.domain.usecases.reports;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

public class CreateCheckOutReportUseCase implements CreateReportUseCase {

    private final ReservationDao reservationRepository;

    public CreateCheckOutReportUseCase(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Exportable createReport(RequestModel request) {
        return reservationRepository.getCheckOutReport(request.initialDate(), request.finalDate());
    }
}
