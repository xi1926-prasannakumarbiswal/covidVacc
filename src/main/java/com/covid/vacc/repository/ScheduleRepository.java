package com.covid.vacc.repository;

import com.covid.vacc.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {

    @Query("select s from ScheduleEntity s where s.branch.branchId = :branchId")
    ScheduleEntity getAvailableTime(@Param("branchId") long branchId);
}
