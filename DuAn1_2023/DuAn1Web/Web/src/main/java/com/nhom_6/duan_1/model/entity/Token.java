package com.nhom_6.duan_1.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseEntity  {
    @Column
    private int OTPToken;
    @Column
    private int createAt;
    @Column
    private Date endAt;

    @OneToOne(mappedBy = "token")
    @JoinColumn(name="user_id")
    private User user;
}
