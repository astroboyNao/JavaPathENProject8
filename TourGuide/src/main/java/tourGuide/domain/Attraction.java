package tourguide.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * The type Attraction.
 */
@Value

@Data
@SuperBuilder(toBuilder = true)
@Getter
public class Attraction {
    private String attractionName;
    private String city;
    private String state;
    private Location location;

}
