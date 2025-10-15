package com.mar.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mar.events.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
