package com.tourguide.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Preferences {

    private int attractionProximity = Integer.MAX_VALUE;
    private CurrencyUnit currency = Monetary.getCurrency("USD");
    private Money lowerPricePoint = Money.of(0, currency);
    private Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);
    private int tripDuration = 1;
    private int ticketQuantity = 1;
    private int numberOfAdults = 1;
    private int numberOfChildren = 0;

}
