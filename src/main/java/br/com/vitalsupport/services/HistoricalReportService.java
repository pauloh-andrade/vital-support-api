package br.com.vitalsupport.services;

import br.com.vitalsupport.models.HistoricalReport;
import br.com.vitalsupport.repositories.HistoricalReportRepository;

import java.util.List;

public class HistoricalReportService {
    private final HistoricalReportRepository historicalReportRepository;

    public HistoricalReportService(HistoricalReportRepository historicalReportRepository) {
        this.historicalReportRepository = historicalReportRepository;
    }

    public void create(HistoricalReport historicalReport) throws Exception {
        this.historicalReportRepository.save(historicalReport);
    }

    public HistoricalReport getById(long id) throws Exception {
        return this.historicalReportRepository.findOneById(id);
    }

    public void update(Long id, HistoricalReport historicalReport) throws Exception {
        historicalReport.setId(id);
        this.historicalReportRepository.update(historicalReport);
    }

    public void delete(HistoricalReport historicalReport) throws Exception {
        this.historicalReportRepository.delete(historicalReport);
    }

    public List<HistoricalReport> getAllHistoricalReports(int page, int pageSize) throws Exception {
        return this.historicalReportRepository.findAll(page, pageSize);
    }
}
