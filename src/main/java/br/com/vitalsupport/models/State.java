package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.StateRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name = "T_VSMVP_ESTADO")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String name;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public State(StateRequestDto stateRequestDto) {
        this.name = stateRequestDto.name();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
