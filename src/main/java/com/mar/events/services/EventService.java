package com.mar.events.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mar.events.dtos.EventDTO;
import com.mar.events.entities.City;
import com.mar.events.entities.Event;
import com.mar.events.repositories.EventRepository;
import com.mar.events.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(new City(dto.getCityId(), null)); // Assumindo que City tem um construtor com ID
        event = eventRepository.save(event);
        return new EventDTO(event);
    }

    @Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();

        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(new City(dto.getCityId(), null));

		entity = eventRepository.save(entity);
		return new EventDTO(entity);
	}
}
