package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="WorkSchedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSchedule extends BaseEntity{
    @Column
    private String nameWorkSchedule;
    @Column
    private Time startAt;
    @Column
    private Time endAt;
    @Column
    private String description;
    @Column
    private boolean status;

    @OneToMany(mappedBy = "workSchedules")
    private List<ShiftAssignment> shiftAssignment;
}
