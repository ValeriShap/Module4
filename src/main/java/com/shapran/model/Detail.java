package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private LocalDate localDate;
    private int amountFuel;
    private int usedFuel;
    private int brokenMicrochips;
    private int usedTime;

    public Detail() {
    }

    public Detail(String id, LocalDate localDate, int amountFuel, int usedFuel,
                  int brokenMicrochips, long usedTime) {
        this.id = id;
        this.localDate = localDate;
        this.amountFuel = amountFuel;
        this.usedFuel = usedFuel;
        this.brokenMicrochips = brokenMicrochips;
        this.usedTime = (int) usedTime;
    }

}
