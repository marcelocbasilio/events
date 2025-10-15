package com.mar.events.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.mar.events.entities.Event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventDTO implements Serializable {

    private Long id;

    @NotBlank(message = "The name field is required.")
    private String name;

    @Future(message = "The event date must be in the future.")
    @NotNull(message = "The date field is required.")
    private LocalDate date;

    @NotBlank(message = "The URL field is required.")
    private String url;

    @NotNull(message = "The City field is required.")
    private Long cityId;

    public EventDTO() {
    }

    public EventDTO(Long id, String name, LocalDate date, String url, Long cityId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.url = url;
        this.cityId = cityId;
    }

    public EventDTO(Event entity) {
        id = entity.getId();
        name = entity.getName();
        date = entity.getDate();
        url = entity.getUrl();
        cityId = entity.getCity().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public Long getCityId() {
        return cityId;
    }

}
