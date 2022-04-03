package com.tourguide.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Provider {
    private UUID tripId;
    public String name;
    public double price;

}