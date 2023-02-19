package com.korolev.wake.repository;

import com.korolev.wake.model.User;

import java.io.Serializable;

public interface IRepositoryUser <T,PK extends Serializable> extends IRepository<T,PK>{
    User getByLogin(String login);
}
