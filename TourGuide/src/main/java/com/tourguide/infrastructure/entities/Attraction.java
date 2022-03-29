package com.tourguide.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

/**
 * The type Attraction.
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attraction {
    /**
     * The Attraction id.
     */
    public UUID attractionId;
    private String attractionName;
    private String city;
    private String state;
    private Double longitude;
    private Double latitude;
}