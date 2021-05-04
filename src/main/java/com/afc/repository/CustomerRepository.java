package com.afc.repository;

import org.springframework.data.repository.CrudRepository;

import com.afc.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
