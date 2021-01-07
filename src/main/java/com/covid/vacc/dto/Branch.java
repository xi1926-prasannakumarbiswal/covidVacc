package com.covid.vacc.dto;

import com.covid.vacc.entity.BranchVaccineEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    private Long branchId;
    private String branchName;
    private  String address;
    private Boolean availability;
    private Vaccine vaccine;
    private BranchVaccine branchVaccine;
    private Schedule schedule;

}
