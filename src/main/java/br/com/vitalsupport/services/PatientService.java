package br.com.vitalsupport.services;

import br.com.vitalsupport.models.Patient;
import br.com.vitalsupport.repositories.PatientRepository;

import java.util.List;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void create(Patient patient) throws Exception {
        this.patientRepository.save(patient);
    }

    public Patient getById(long id) throws Exception {
        return this.patientRepository.findOneById(id);
    }

    public void update(Long id, Patient patient) throws Exception {
        patient.setId(id);
        this.patientRepository.update(patient);
    }

    public void delete(Patient patient) throws Exception {
        this.patientRepository.delete(patient);
    }

    public List<Patient> getAllPatients(int page, int pageSize) throws Exception {
        return this.patientRepository.findAll(page, pageSize);
    }
}
