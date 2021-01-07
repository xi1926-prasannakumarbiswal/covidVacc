package com.covid.vacc.service;

import com.covid.vacc.dto.Branch;
import com.covid.vacc.dto.Vaccine;
import com.covid.vacc.entity.BranchEntity;
import com.covid.vacc.entity.VaccineEntity;
import com.covid.vacc.exceptions.ResourceNotFoundException;

import java.util.List;

public interface AvailabilityService {
    List<Branch> getAllBranch(Integer pageNumber, Integer pageSize, String sortBy,
                              String order) throws ResourceNotFoundException;

    List<Branch> getAvailableVaccinesPerBranch(long branchId) throws ResourceNotFoundException;

    Branch getSpecificVaccineAvailabilityByBranch(long branchId, Long vaccineId) throws ResourceNotFoundException;

    Branch getAvailableTimeForABranchId(long branchId) throws ResourceNotFoundException;
}
