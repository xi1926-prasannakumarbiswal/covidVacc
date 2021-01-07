package com.covid.vacc.service.impl;

import com.covid.vacc.dto.Branch;
import com.covid.vacc.dto.Schedule;
import com.covid.vacc.dto.Vaccine;
import com.covid.vacc.entity.BranchEntity;
import com.covid.vacc.entity.BranchVaccineEntity;
import com.covid.vacc.entity.ScheduleEntity;
import com.covid.vacc.entity.VaccineEntity;
import com.covid.vacc.exceptions.ResourceNotFoundException;
import com.covid.vacc.repository.BranchRepository;
import com.covid.vacc.repository.ScheduleRepository;
import com.covid.vacc.repository.VaccineRepository;
import com.covid.vacc.service.AvailabilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Branch> getAllBranch(Integer pageNumber, Integer pageSize, String sortBy, String order) throws ResourceNotFoundException {
        log.info("Fetching all branches from db");

        Pageable pageable;
        if (order.equalsIgnoreCase("desc") || order.equalsIgnoreCase("descending")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        }

        Page<BranchEntity> branchEntity = null;
        List<BranchEntity> branchEntityList = new ArrayList<>();
        List<Branch> branchList = new ArrayList<>();
        branchEntity =  branchRepository.findAll(pageable);
        if(!branchEntity.isEmpty() || branchEntity != null) {
            long totalElements = branchEntity.getTotalElements();
            int totalPages = branchEntity.getTotalPages();
            branchEntityList = branchEntity.getContent();
            for (BranchEntity entity : branchEntityList) {
                branchList.add(getBranch(entity));
            }

        }else{
            throw new ResourceNotFoundException("No branches found");

        }
        return branchList;
    }

    private Branch getBranch(BranchEntity entity) {
        log.info("getBranch method start here");
        Branch branch = new Branch();
        branch.setBranchId(entity.getBranchId());
        branch.setBranchName(entity.getBranchName());
        branch.setAddress(entity.getAddress());
        return branch;

    }

    @Override
    public List<Branch> getAvailableVaccinesPerBranch(long branchId) throws ResourceNotFoundException {
        log.info("Fetching all available vaccine per branch from db :"+ branchId);
        List<Branch> branch = new ArrayList<>();
        List<BranchVaccineEntity> branchVaccineEntityList = new ArrayList<>();
        BranchEntity branchentity =  branchRepository.getBranchDetails(branchId);
           // .orElseThrow(() -> new ResourceNotFoundException("No Branch found for branchId- " + branchId));
        branchVaccineEntityList = branchRepository.findBranchVaccineBYBranchId(branchentity.getBranchId());
        for(BranchVaccineEntity entity : branchVaccineEntityList){
            branch.add(getVaccinePerBranch(entity));
        }

        return  branch;
    }

    @Override
    public Branch getSpecificVaccineAvailabilityByBranch(long branchId, Long vaccineId) throws ResourceNotFoundException {

        log.info("Fetching all get Specific vaccine availability by branch from db :"+ branchId + " and vaccineId: " + vaccineId);
        Branch branch = null;
        BranchVaccineEntity branchVaccineEntity = null;
        BranchEntity branchentity =  branchRepository.getBranchDetails(branchId);
               // .orElseThrow(() -> new ResourceNotFoundException("No Branch found for branchId- " + branchId));
        VaccineEntity vaccineentity =  vaccineRepository.getVaccineDetails(vaccineId);
              //  .orElseThrow(() -> new ResourceNotFoundException("No Branch found for vaccineId- " + vaccineId));

        branchVaccineEntity = branchRepository.findSpecificAvailabilityVaccineByBranch(branchentity.getBranchId(), vaccineentity.getVaccineId());
        branch = getVaccinePerBranch(branchVaccineEntity);

        return branch;
    }

    @Override
    public Branch getAvailableTimeForABranchId(long branchId) throws ResourceNotFoundException {

        log.info("Fetching all available time for a branch from db :"+ branchId);
        Branch branch = null;
        List<BranchVaccineEntity> branchVaccineEntityList = new ArrayList<>();
        BranchEntity branchentity =  branchRepository.getBranchDetails(branchId);
                //.orElseThrow(() -> new ResourceNotFoundException("No Branch found for branchId- " + branchId));
        branch = getScheduleTimeForABranch(branchentity);

        return null;
    }

    private Branch getScheduleTimeForABranch(BranchEntity branchentity) {
        Branch branch = new Branch();
        Vaccine vaccine = new Vaccine();
        Schedule schedule = null;
        BranchEntity branchEntity = null;
        VaccineEntity vaccineEntity = null;
        ScheduleEntity schedulerEntity = null;
        List<BranchVaccineEntity> branchVaccineEntityList = new ArrayList<>();
        branchEntity = branchRepository.getBranchDetails(branchentity.getBranchId());
        branch.setBranchId(branchEntity.getBranchId());
        branch.setBranchName(branchEntity.getBranchName());
        branch.setAddress(branchEntity.getAddress());
        branchVaccineEntityList = branchRepository.findBranchVaccineBYBranchId(branchentity.getBranchId());
        for (BranchVaccineEntity entity : branchVaccineEntityList){
            vaccineEntity = vaccineRepository.getVaccineDetails(entity.getVaccine().getVaccineId());
            vaccine.setVaccineId(vaccineEntity.getVaccineId());
            vaccine.setVaccineName(vaccineEntity.getVaccineName());
            vaccine.setDose(vaccineEntity.getDose());

        }
        branch.setVaccine(vaccine);
        schedulerEntity =  scheduleRepository.getAvailableTime(branchentity.getBranchId());
        schedule.setScheduleId(schedulerEntity.getScheduleId());
        schedule.setAvailableTime(schedulerEntity.getAvailableTime());
        branch.setSchedule(schedule);


        return branch;
    }


    private Branch getVaccinePerBranch(BranchVaccineEntity branchVaccineEntity) {
        Branch branch = new Branch();
        Vaccine vaccine = new Vaccine();
        VaccineEntity vaccineEntity = null;
        BranchEntity branchEntity = null;
        branchEntity = branchRepository.getBranchDetails(branchVaccineEntity.getBranch().getBranchId());
        branch.setBranchId(branchEntity.getBranchId());
        branch.setBranchName(branchEntity.getBranchName());
        branch.setAddress(branchEntity.getAddress());
        vaccineEntity = vaccineRepository.getVaccineDetails(branchVaccineEntity.getVaccine().getVaccineId());
        vaccine.setVaccineId(vaccineEntity.getVaccineId());
        vaccine.setVaccineName(vaccineEntity.getVaccineName());
        vaccine.setDose(vaccineEntity.getDose());
        vaccine.setCreatedAt(vaccineEntity.getCreatedDate());
        vaccine.setModifiedAt(vaccineEntity.getModifiedDate());
        branch.setVaccine(vaccine);

        return branch;


    }


}

