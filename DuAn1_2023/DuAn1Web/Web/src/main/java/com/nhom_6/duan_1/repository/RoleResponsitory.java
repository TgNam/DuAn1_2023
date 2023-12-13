package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleResponsitory extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.RoleName = :role")
    Role findByRoleName(@Param("role") String role);
}
