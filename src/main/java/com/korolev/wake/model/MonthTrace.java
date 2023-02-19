package com.korolev.wake.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthTrace {
    private Integer id;
    private String year;
    private String month;
    private String mask;
    private Integer maxDays;
    private Integer startWeekDay;
}
