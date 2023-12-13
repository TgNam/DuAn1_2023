package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillDetailsResponsitory extends JpaRepository<BillDetails,Long> {

}
