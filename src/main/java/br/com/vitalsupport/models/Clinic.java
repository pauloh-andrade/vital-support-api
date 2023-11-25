package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.ClinicRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name="T_VSMVP_CLINICA")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String name;

    @Column(name="razao_social")
    private String socialReason;

    @Column(name="cnpj")
    private Long cnpj;

    @Column(name="horario_func")
    private String oppeningHours;

    @Column(name="telefone")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name="id_endereco")
    private Long addressId;

    public Clinic() {
    }

    public Clinic(String name, String socialReason, Long cnpj, String oppeningHours, String phoneNumber, String email, Long addressId) {
        this.name = name;
        this.socialReason = socialReason;
        this.cnpj = cnpj;
        this.oppeningHours = oppeningHours;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressId = addressId;
    }

    public Clinic(ClinicRequestDto clinicRequestDto) {
        this.name = clinicRequestDto.name();
        this.socialReason = clinicRequestDto.socialReason();
        this.cnpj = clinicRequestDto.cnpj();
        this.oppeningHours = clinicRequestDto.oppeningHours();
        this.phoneNumber = clinicRequestDto.phoneNumber();
        this.email = clinicRequestDto.email();
        this.addressId = clinicRequestDto.addressId();
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

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getOppeningHours() {
        return oppeningHours;
    }

    public void setOppeningHours(String oppeningHours) {
        this.oppeningHours = oppeningHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}



