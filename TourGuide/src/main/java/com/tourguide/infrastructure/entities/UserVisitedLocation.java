package com.tourguide.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.UUID;

/**
 * The type Visited location.
 */
@Data
@Builder
@Getter
@AllArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVisitedLocation {
    private UUID userId;
    private Location location;

}
