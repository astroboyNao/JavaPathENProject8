package com.tourguide.infrastructure.mapper;

import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.Reward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface RewardMapper {
    com.tourguide.domain.Reward toRewardDomain(Reward reward);

    Reward toRewardEntities(com.tourguide.domain.Reward reward);
}
