package br.com.vitalsupport.services;

import br.com.vitalsupport.models.Clinic;
import br.com.vitalsupport.repositories.ClinicRepository;

import java.util.List;

public class ClinicService {
    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public void create(Clinic clinic) throws Exception {
        this.clinicRepository.save(clinic);
    }

    public Clinic getById(long id) throws Exception {
        return this.clinicRepository.findOneById(id);
    }

    public void update(Long id, Clinic clinic) throws Exception {
        clinic.setId(id);
        this.clinicRepository.update(clinic);
    }

    public void delete(Clinic clinic) throws Exception {
        this.clinicRepository.delete(clinic);
    }

    public List<Clinic> getAllClinics(int page, int pageSize) throws Exception {
        return this.clinicRepository.findAll(page, pageSize);
    }
}
