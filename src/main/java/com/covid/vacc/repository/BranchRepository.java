package com.covid.vacc.repository;

import com.covid.vacc.entity.BranchEntity;
import com.covid.vacc.entity.BranchVaccineEntity;
import com.covid.vacc.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity,Long> {

    //Optional<BranchEntity> findBYBranchId(Long branchId);

    @Query("select bve from BranchVaccineEntity bve where bve.branch.branchId = :branchId")
    List<BranchVaccineEntity> findBranchVaccineBYBranchId(@Param("branchId") long branchId);


    @Query("select bve from BranchVaccineEntity bve where bve.branch.branchId = :branchId and bve.vaccine.vaccineId = :vaccineId")
    BranchVaccineEntity findSpecificAvailabilityVaccineByBranch(@Param("branchId") long branchId, @Param("vaccineId") long vaccineId);

    @Query("select b from BranchEntity b where b.branchId = :branchId")
    BranchEntity getBranchDetails(@Param("branchId") long branchId);
}
