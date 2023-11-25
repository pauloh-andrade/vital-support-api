package br.com.vitalsupport.services;

import br.com.vitalsupport.models.State;
import br.com.vitalsupport.repositories.StateRepository;

import java.util.List;

public class StateService {
    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public void create(State state) throws Exception {
        this.stateRepository.save(state);
    }

    public State getById(int id) throws Exception {
        return this.stateRepository.findOneById(id);
    }

    public void update(int id, State state) throws Exception {
        state.setId(id);
        this.stateRepository.update(state);
    }

    public void delete(State state) throws Exception {
        this.stateRepository.delete(state);
    }

    public List<State> getAllStates(int page, int pageSize) throws Exception {
        return this.stateRepository.findAll(page, pageSize);
    }
}
