package tourguide.infrastructure.mapper;

import org.mapstruct.Mapper;
import tourguide.infrastructure.entities.User;

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
    tourguide.domain.User toUserDomain(User user);

    /**
     * To user entities user.
     *
     * @param userTracked the user tracked
     * @return the user
     */
    User toUserEntities(tourguide.domain.User userTracked);
}
