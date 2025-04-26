package com.example.thymeleaf.sprithyme.repository;

import com.example.thymeleaf.sprithyme.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
