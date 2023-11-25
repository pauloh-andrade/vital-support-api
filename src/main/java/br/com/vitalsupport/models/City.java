package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.CityRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name="T_VSMVP_CIDADE")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nome")
    private String name;
    @Column(name="id_estado")
    private Long stateId;

    public City() {}

    public City(String name, Long stateId) {
        this.name = name;
        this.stateId = stateId;
    }
    public City(CityRequestDto cityRequestDto) {
        this.name = cityRequestDto.name();
        this.stateId = cityRequestDto.stateId();
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

    public Long getState() {
        return stateId;
    }

    public void setState(Long stateId) {
        this.stateId = stateId;
    }
}

