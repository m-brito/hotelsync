package br.edu.ifsp.hotelsync.domain.usecases.reports.create;

import br.edu.ifsp.hotelsync.domain.entities.report.records.FinancialReport;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

public class CreateFinancialReportUseCase implements CreateReportUseCase {

    private final ReservationDao reservationRepository;

    public CreateFinancialReportUseCase(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public FinancialReport createReport(RequestModel request) {
        return reservationRepository.getFinancialReport(request.initialDate(), request.finalDate());
    }
}
