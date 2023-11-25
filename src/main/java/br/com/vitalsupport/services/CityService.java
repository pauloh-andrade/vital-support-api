package br.com.vitalsupport.services;

import br.com.vitalsupport.models.City;
import br.com.vitalsupport.repositories.CityRepository;

import java.util.List;

public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void create(City city) throws Exception {
        this.cityRepository.save(city);
    }

    public City getById(Long id) throws Exception {
        return this.cityRepository.findOneById(id);
    }

    public void update(Long id, City city) throws Exception {
        city.setId(id);
        this.cityRepository.update(city);
    }

    public void delete(City city) throws Exception {
        this.cityRepository.delete(city);
    }

    public List<City> getAllCities(int page, int pageSize) throws Exception {
        return this.cityRepository.findAll(page, pageSize);
    }
}
