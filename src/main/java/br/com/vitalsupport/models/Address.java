package br.com.vitalsupport.models;

import br.com.vitalsupport.dtos.AddressRequestDto;
import jakarta.persistence.*;


@Entity
@Table(name="T_VSMVP_ENDERECO")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero")
    private int number;

    @Column(name="logradouro")
    private String streetName;

    @Column(name="cep")
    private String cep;

    @Column(name="id_cidade")
    private Long cityId;

    public Address() { }

    public Address(int number, String streetName, String cep, Long cityId) {
        this.number = number;
        this.streetName = streetName;
        this.cep = cep;
        this.cityId = cityId;
    }

    public Address(AddressRequestDto addressRequestDto) {
        this.number = addressRequestDto.number();
        this.streetName = addressRequestDto.streetName();
        this.cep = addressRequestDto.cep();
        this.cityId = addressRequestDto.cityId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
