package com.korolev.wake.repository;

import com.korolev.wake.model.MonthTrace;

import java.io.Serializable;

public interface IRepositoryMonth<T,PK extends Serializable> extends IRepository<T,PK>{
    MonthTrace getByMonthYear(String month, String year);
}
