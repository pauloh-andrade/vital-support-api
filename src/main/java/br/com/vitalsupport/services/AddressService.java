package br.com.vitalsupport.services;

import br.com.vitalsupport.models.Address;
import br.com.vitalsupport.repositories.AddressRepository;

import java.util.List;

public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void create(Address address) throws Exception {
        this.addressRepository.save(address);
    }

    public Address getById(long id) throws Exception {
        return this.addressRepository.findOneById(id);
    }

    public void update(Long id, Address address) throws Exception {
        address.setId(id);
        this.addressRepository.update(address);
    }

    public void delete(Address address) throws Exception {
        this.addressRepository.delete(address);
    }

    public List<Address> getAllAddresses(int page, int pageSize) throws Exception {
        return this.addressRepository.findAll(page, pageSize);
    }
}
