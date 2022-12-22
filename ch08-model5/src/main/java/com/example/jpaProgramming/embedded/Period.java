package com.example.jpaProgramming.embedded;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Embeddable
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    public boolean isWork(LocalDate date) {
        return true;
    }
}
