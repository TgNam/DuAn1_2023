package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserResponsitory  extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query( " SELECT u from User u where u.email = :email or u.numberPhone = :phone ")
    List<User> findByNumberPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);
}
