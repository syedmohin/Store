package com.afc.repository;

import com.afc.model.Users;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findByUsername(String name);

}
