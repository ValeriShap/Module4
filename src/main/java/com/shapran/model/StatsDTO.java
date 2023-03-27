package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class StatsDTO {
    private long allDetails;
    private long allBrokenMicrochips;
    private long allPreparedFuel;
    private long allUsedFuel;

    public StatsDTO( long allBrokenMicrochips, long allPreparedFuel, long allUsedFuel) {
        this.allBrokenMicrochips = allBrokenMicrochips;
        this.allPreparedFuel = allPreparedFuel;
        this.allUsedFuel = allUsedFuel;
    }
}
