package com.tourguide.infrastructure.mapper;

import com.tourguide.domain.Preferences;
import com.tourguide.domain.VisitedLocation;
import org.mapstruct.Mapper;

/**
 * The interface User mapper.
 */
@Mapper
public interface VisitedLocationMapper {

    VisitedLocation toVisitedLocationDomain(com.tourguide.infrastructure.entities.VisitedLocation visitedLocation);
    com.tourguide.infrastructure.entities.VisitedLocation toVisitedLocation(VisitedLocation visitedLocation);
    Preferences toUserPreferencesDomain(com.tourguide.infrastructure.entities.Preferences preferences);
}
