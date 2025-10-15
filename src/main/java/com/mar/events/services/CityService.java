package com.mar.events.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mar.events.dtos.CityDTO;
import com.mar.events.entities.City;
import com.mar.events.repositories.CityRepository;
import com.mar.events.services.exceptions.DatabaseException;
import com.mar.events.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> findAllSortedByName() {
        List<City> cities = cityRepository.findAllByOrderByName();
        return cities.stream().map(city -> new CityDTO(city.getId(), city.getName())).collect(Collectors.toList());
    }

    public CityDTO insert(CityDTO dto) {
        City city = new City();
        city.setName(dto.getName());
        city = cityRepository.save(city);

        return new CityDTO(city.getId(), city.getName());
    }

    public void deleteById(Long id) {
        try {
            if (cityRepository.existsById(id)) {
                cityRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("City not found with ID " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Cannot delete city with ID " + id + " because it has dependent entities.");
        }

    }

}
