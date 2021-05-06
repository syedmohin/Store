package com.afc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.afc.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Optional<Customer> findTopByOrderByIdDesc();
}
