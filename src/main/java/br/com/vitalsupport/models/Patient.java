package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.PatientRequestDto;
import jakarta.persistence.*;
import oracle.sql.DATE;

@Entity
@Table(name="T_VSMVP_PACIENTE")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String cpf;

    @Column(name="nascimento")
    private DATE birthDate
    ;

    @Column(name="genero")
    private String gender;

    @Column(name="telefone")
    private String phoneNumber;

    @Column(name="grupo_sanquineo")
    private String bloodType;

    @Column(name="estado_civil")
    private String maritalStatus;

    public Patient() { }

    public Patient(PatientRequestDto patientRequestDto) {
        this.cpf = patientRequestDto.cpf();
        this.birthDate = patientRequestDto.birthDate();
        this.gender = patientRequestDto.gender();
        this.phoneNumber = patientRequestDto.phoneNumber();
        this.bloodType = patientRequestDto.bloodType();
        this.maritalStatus = patientRequestDto.maritalStatus();
    }

    public Patient(String cpf, DATE birthDate, String gender, String phoneNumber, String bloodType, String maritalStatus) {
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.maritalStatus = maritalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public DATE getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DATE birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
