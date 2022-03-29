package com.tourguide.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class RewardTest {

    @Test
    public void instanciateReward() {
      Reward reward = Reward.builder().build();
        Assert.notNull(reward, () -> "instanciate reward");
    }

}
