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
@Table(name = "branch")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRANCH_ID")
    private long branchId;

    @Column(name = "BRANCH_NAME")
    private String branchName;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "AVAILABILITY")
    private Boolean availability;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "branch")
    private List<BranchVaccineEntity> branchVaccine;

    @OneToMany(mappedBy = "branch")
    private List<ScheduleEntity> schedule;

}
