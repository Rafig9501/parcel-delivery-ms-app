package com.parcel.customer_service.repository;

import com.parcel.customer_service.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    boolean existsByUsernameOrPhone(String username, String phone);
}
