package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.AdminRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name="T_VSMVP_ADMINISTRADOR")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="nivel_acesso")
    private String permission;

    public Admin() { }

    public Admin(AdminRequestDto adminRequestDto) {
        this.email = adminRequestDto.email();
        this.permission = adminRequestDto.permission();
    }

    public Admin(String email, String permission) {
        this.email = email;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
