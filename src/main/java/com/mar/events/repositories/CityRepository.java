package com.mar.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mar.events.entities.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByOrderByName();
}
