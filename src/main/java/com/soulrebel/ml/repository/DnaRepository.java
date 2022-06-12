package com.soulrebel.ml.repository;

import java.util.Optional;

public interface DnaRepository<T> {

    void save(T entity);

    Optional<String> findAll();
}
