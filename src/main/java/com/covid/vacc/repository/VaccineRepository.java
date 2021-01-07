package com.covid.vacc.repository;

import com.covid.vacc.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity,Long> {

    //Optional<VaccineEntity> findBYVaccineId(Long vaccineId);

    @Query("select v from VaccineEntity v where v.vaccineId = :vaccineId")
    VaccineEntity getVaccineDetails(@Param("vaccineId")long vaccineId);
}
