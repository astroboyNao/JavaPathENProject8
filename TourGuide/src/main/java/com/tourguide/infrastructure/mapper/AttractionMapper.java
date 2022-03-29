package com.tourguide.infrastructure.mapper;

import com.tourguide.domain.Location;
import org.mapstruct.Mapper;
import com.tourguide.infrastructure.entities.Attraction;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.w3c.dom.Attr;

@Mapper
public interface AttractionMapper {
    @Mappings({
              @Mapping(source = "latitude", target = "location.latitude"),
              @Mapping(source = "longitude", target = "location.longitude")
    })
    com.tourguide.domain.Attraction toAttractionDomain(Attraction attraction);


    @Mappings({
            @Mapping(source = "location.latitude", target = "latitude"),
            @Mapping(source = "location.longitude", target = "longitude")
    })
    Attraction toAttractionEntities(com.tourguide.domain.Attraction attraction);
}
