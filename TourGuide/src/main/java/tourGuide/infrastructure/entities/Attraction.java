package tourguide.infrastructure.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * The type Attraction.
 */
@Data
@SuperBuilder(toBuilder = true)
public class Attraction extends tourguide.domain.Attraction {
    /**
     * The Attraction id.
     */
    public final UUID attractionId;
}
