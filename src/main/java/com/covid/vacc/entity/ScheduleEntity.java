package com.covid.vacc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private long ScheduleId;

    @Column(name = "Available_time")
    private String availableTime;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchEntity branch;
}
