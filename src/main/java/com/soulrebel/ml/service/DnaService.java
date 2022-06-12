package com.soulrebel.ml.service;

import com.soulrebel.ml.entity.Dna;
import com.soulrebel.ml.repository.GenericRepository;

import java.util.Optional;

public class DnaService implements GenericRepository<Dna> {


    @Override
    public void save(Dna entity) {

    }

    @Override
    public Optional<String> findAll() {
        return Optional.empty();
    }
}
