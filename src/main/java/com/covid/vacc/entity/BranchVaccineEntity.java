package com.covid.vacc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Branch_Vaccine")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchVaccineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRANCH_VACCINE_ID")
    private Long branchVaccineId;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchEntity branch;

    @ManyToOne
    @JoinColumn(name = "VACCINE_ID", nullable = false)
    private VaccineEntity vaccine;

}
