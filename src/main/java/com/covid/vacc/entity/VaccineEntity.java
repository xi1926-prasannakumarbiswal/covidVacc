package com.covid.vacc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vaccine")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINE_ID")
    private long vaccineId;

    @Column(name = "VACCINE_NAME")
    private String vaccineName;

    @Column(name = "DOSE")
    private int dose;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedDate;


    @OneToMany(mappedBy = "vaccine")
    private List<BranchVaccineEntity> branchVaccine;

}
