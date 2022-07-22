package com.tecapro.facebook.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService <T>{
    List<T> findAll();

    T save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);
}
