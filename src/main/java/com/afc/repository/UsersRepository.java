package com.afc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.afc.model.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {

	Optional<Users> findByUsername(String name);

	boolean existsByUsername(String user);

}
