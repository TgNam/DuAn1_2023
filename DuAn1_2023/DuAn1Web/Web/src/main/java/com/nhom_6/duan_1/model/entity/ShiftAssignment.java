package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="ShiftAssignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftAssignment extends BaseEntity {

    @Column
    private Date startAt;
    @Column
    private Date endAt;
    @Column
    private String description;
    @Column
    private String status;
    @Column
    private double startingAmount;
    @Column
    private double finalAmount;

    @ManyToOne
    @JoinColumn(name = "workSchedules_id")
    private WorkSchedule workSchedules;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
