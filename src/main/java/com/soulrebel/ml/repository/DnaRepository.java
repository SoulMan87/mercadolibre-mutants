package com.soulrebel.ml.repository;

import com.soulrebel.ml.entity.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DnaRepository extends JpaRepository<Dna, Integer> {

    @Query("SELECT COUNT (a) From Dna a WHERE a.isMutant=true")
    Long counterMutants();

    @Query("SELECT COUNT (a) From Dna a WHERE a.isMutant=false")
    Long counterNotMutants();
}
