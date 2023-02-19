package com.korolev.wake.repository;

import java.io.Serializable;
import java.util.ArrayList;

public interface IRepository<T,PK extends Serializable> {
    ArrayList<T> findAll();
    T getById(PK id);
    void delete(T t);
    void save(T t);
    void update(T t);
}
