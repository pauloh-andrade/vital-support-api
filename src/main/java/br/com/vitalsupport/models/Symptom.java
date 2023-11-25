package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.SymptomRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name="T_VSMVP_SINTOMAS")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String name;

    @Column(name="id_hitorico_sintomas")
    private Long symptomHistoryId;

    public Symptom() { }

    public Symptom(String name, Long symptomHistoryId) {
        this.name = name;
        this.symptomHistoryId = symptomHistoryId;
    }

    public Symptom(SymptomRequestDto symptomRequestDto) {
        this.name = symptomRequestDto.name();
        this.symptomHistoryId = symptomRequestDto.symptomHistoryId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSymptomHistoryId() {
        return symptomHistoryId;
    }

    public void setSymptomHistoryId(Long symptomHistoryId) {
        this.symptomHistoryId = symptomHistoryId;
    }
}
