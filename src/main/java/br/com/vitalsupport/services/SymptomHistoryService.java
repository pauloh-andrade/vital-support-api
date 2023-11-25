package br.com.vitalsupport.services;

import br.com.vitalsupport.models.SymptomHistory;
import br.com.vitalsupport.repositories.SymptomHistoryRepository;

import java.util.List;

public class SymptomHistoryService {
    private final SymptomHistoryRepository symptomHistoryRepository;

    public SymptomHistoryService(SymptomHistoryRepository symptomHistoryRepository) {
        this.symptomHistoryRepository = symptomHistoryRepository;
    }

    public void create(SymptomHistory symptomHistory) throws Exception {
        this.symptomHistoryRepository.save(symptomHistory);
    }

    public SymptomHistory getById(long id) throws Exception {
        return this.symptomHistoryRepository.findOneById(id);
    }

    public void update(Long id, SymptomHistory symptomHistory) throws Exception {
        symptomHistory.setId(id);
        this.symptomHistoryRepository.update(symptomHistory);
    }

    public void delete(SymptomHistory symptomHistory) throws Exception {
        this.symptomHistoryRepository.delete(symptomHistory);
    }

    public List<SymptomHistory> getAllSymptomHistories(int page, int pageSize) throws Exception {
        return this.symptomHistoryRepository.findAll(page, pageSize);
    }
}
