package br.com.vitalsupport.services;

import br.com.vitalsupport.models.Symptom;
import br.com.vitalsupport.repositories.SymptomRepository;

import java.util.List;

public class SymptomService {
    private final SymptomRepository symptomRepository;

    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public void create(Symptom symptom) throws Exception {
        this.symptomRepository.save(symptom);
    }

    public Symptom getById(long id) throws Exception {
        return this.symptomRepository.findOneById(id);
    }

    public void update(Long id, Symptom symptom) throws Exception {
        symptom.setId(id);
        this.symptomRepository.update(symptom);
    }

    public void delete(Symptom symptom) throws Exception {
        this.symptomRepository.delete(symptom);
    }

    public List<Symptom> getAllSymptoms(int page, int pageSize) throws Exception {
        return this.symptomRepository.findAll(page, pageSize);
    }
}
