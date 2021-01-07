package com.covid.vacc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private Long scheduleId;
    private String availableTime;
    private Branch branch;
}
