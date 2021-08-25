package com.example.atos.helpers;

import java.time.LocalDate;
import java.time.Period;

public class DateHelper {
    /**
     * compute the number of years from a date
     * @param localDate date
     * @return number of years
     */
    public static int computeYears(LocalDate localDate) {
        return Period.between(localDate, LocalDate.now()).getYears();
    }
}
