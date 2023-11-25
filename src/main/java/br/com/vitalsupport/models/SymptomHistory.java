package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.SymptomHistoryRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name="T_VSMVP_HISTORICO_SINTOMAS")
public class SymptomHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="status_meningite")
    private boolean statusMeningitis;

    @Column(name="id_paciente")
    private Long patientId;

    public SymptomHistory() { }

    public SymptomHistory(boolean statusMeningitis, Long patientId) {
        this.statusMeningitis = statusMeningitis;
        this.patientId = patientId;
    }
    public SymptomHistory(SymptomHistoryRequestDto symptomHistoryRequestDto) {
        this.statusMeningitis = symptomHistoryRequestDto.statusMeningitis();
        this.patientId = symptomHistoryRequestDto.patientId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatusMeningitis() {
        return statusMeningitis;
    }

    public void setStatusMeningitis(boolean statusMeningitis) {
        this.statusMeningitis = statusMeningitis;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
