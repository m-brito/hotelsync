package br.edu.ifsp.hotelsync.domain.usecases.reports.create;

import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckOutReport;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

public class CreateCheckOutReportUseCase implements CreateReportUseCase {

    private final ReservationDao reservationRepository;

    public CreateCheckOutReportUseCase(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public CheckOutReport createReport(RequestModel request) {
        return reservationRepository.getCheckOutReport(request.initialDate(), request.finalDate());
    }
}
