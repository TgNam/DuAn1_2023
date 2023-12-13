package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressResponsitory  extends JpaRepository<Address,Long> {
}
