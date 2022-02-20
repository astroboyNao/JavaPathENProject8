package tourguide.infrastructure.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * The type User.
 */
@Data
@SuperBuilder(toBuilder = true)
public class User extends tourguide.domain.User {
    private final UUID userId;
}
