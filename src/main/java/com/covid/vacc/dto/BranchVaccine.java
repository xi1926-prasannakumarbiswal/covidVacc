package com.covid.vacc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchVaccine {
    private long branchId;
    private Long vaccineId;
}
