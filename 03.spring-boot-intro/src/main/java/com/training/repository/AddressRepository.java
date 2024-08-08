package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
