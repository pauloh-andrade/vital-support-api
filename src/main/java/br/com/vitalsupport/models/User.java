package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.UserRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name="T_VSMVP_USUARIO")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String name;

    @Column(name="login")
    private String login;

    @Column(name="senha")
    private String password;

    public User() { }

    public User(UserRequestDto userRequestDto) {
        this.name = userRequestDto.name();
        this.login = userRequestDto.login();
        this.password = userRequestDto.password();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
