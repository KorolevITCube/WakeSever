package com.korolev.wake.services;

import com.korolev.wake.model.MonthDTO;
import com.korolev.wake.model.MonthTrace;

public interface IMonthService {
    void update(MonthDTO month);
    MonthTrace get(Integer month, Integer year);
}
