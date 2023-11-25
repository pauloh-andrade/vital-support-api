package br.com.vitalsupport.services;

import br.com.vitalsupport.models.User;
import br.com.vitalsupport.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String login, String password) {
        User user = this.userRepository.findByLogin(login);

        return user != null && user.getPassword().equals(password);
    }

    public void create(User user) throws Exception {
        this.userRepository.save(user);
    }

    public User getById(long id) throws Exception {
       return this.userRepository.findOneById(id);
    }

    public void update(Long id, User user) throws Exception {
        user.setId(id);
        this.userRepository.update(user);
    }

    public void delete(User user) throws Exception {
        this.userRepository.delete(user);
    }

    public List<User> getAllUsers(int page, int pageSize) throws Exception {
        return this.userRepository.findAll(page, pageSize);
    }
}
