package com.tourguide.infrastructure.mapper;

import com.tourguide.infrastructure.entities.Provider;
import com.tourguide.infrastructure.entities.Reward;
import org.mapstruct.Mapper;

@Mapper
public interface ProviderMapper {
    com.tourguide.domain.Provider toProviderDomain(Provider provider);

    Provider toProviderEntities(com.tourguide.domain.Provider provider);
}
