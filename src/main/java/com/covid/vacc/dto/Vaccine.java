package com.covid.vacc.dto;

import com.covid.vacc.entity.BranchVaccineEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    private Long vaccineId;
    private String vaccineName;
    private int dose;
    private Date createdAt;
    private Date modifiedAt;
    private List<BranchVaccineEntity> branchVaccine;


}
