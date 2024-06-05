package br.edu.ifsp.hotelsync.domain.usecases.reports;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

public class CreateFinancialReportUseCase implements CreateReportUseCase {

    private final ReservationDao reservationRepository;

    public CreateFinancialReportUseCase(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Exportable createReport(RequestModel request) {
        return reservationRepository.getFinancialReport(request.initialDate(), request.finalDate());
    }
}
