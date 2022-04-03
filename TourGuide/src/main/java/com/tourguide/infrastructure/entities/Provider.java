package com.tourguide.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class Provider {
    private UUID tripId;
    private String name;
    private double price;
}