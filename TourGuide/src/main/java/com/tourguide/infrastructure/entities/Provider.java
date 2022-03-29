package com.tourguide.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class Provider {
    public final String name;
    public final double price;

}