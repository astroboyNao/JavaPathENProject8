package tourguide.infrastructure.mapper;

import org.mapstruct.Mapper;
import tourguide.infrastructure.entities.Attraction;
import tourguide.infrastructure.entities.User;

@Mapper
public interface AttractionMapper {
    tourguide.domain.Attraction toAttractionDomain(Attraction attraction);
}
