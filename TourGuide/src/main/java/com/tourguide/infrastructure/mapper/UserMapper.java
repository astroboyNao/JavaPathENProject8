package com.tourguide.infrastructure.mapper;

import com.tourguide.domain.Preferences;
import com.tourguide.domain.User;
import org.mapstruct.Mapper;

/**
 * The interface User mapper.
 */
@Mapper
public interface UserMapper {
    /**
     * To user domain tourguide . domain . user.
     *
     * @param user the user
     * @return the tourguide . domain . user
     */
    User toUserDomain(com.tourguide.infrastructure.entities.User user);

    Preferences toUserPreferencesDomain(com.tourguide.infrastructure.entities.Preferences preferences);
    /**
     * To user entities user.
     *
     * @param userTracked the user tracked
     * @return the user
     */
    com.tourguide.infrastructure.entities.User toUserEntities(User userTracked);
    com.tourguide.infrastructure.entities.Preferences toUserPreferencesEntities(Preferences preferences);

}
