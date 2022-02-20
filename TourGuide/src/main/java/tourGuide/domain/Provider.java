package tourguide.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)

public class Provider {
    public final String name;
    public final double price;

}