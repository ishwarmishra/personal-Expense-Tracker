package com.personalfinancetracker.repository;

import com.personalfinancetracker.model.IncomeEntity;
import java.util.List;

public interface IRepository<T> {

    T add(T data);

    void delete(int id);

    T update(T data);

    T findById(int id);

    List<T> findAll();
}
