package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BillResponsitory extends JpaRepository<Bill,Long> {

    @Query(" select b from Bill b where b.status = '-1' and b.user.id = :idUser ")
    Optional<Bill> getCartByUserId(@Param("idUser") Long idUser);
}
