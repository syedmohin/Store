package com.afc.services;

import com.afc.exception.UserNotFoundException;
import com.afc.model.Users;
import com.afc.repository.UsersRepository;
import com.password4j.Password;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepo;

    public UsersService(UsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Users save(Users users) {
        users.setPassword(Password.hash(users.getPassword()).withBCrypt().getResult());
        return usersRepo.save(users);
    }

    public Users getUser(String name) {
        return usersRepo.findByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("Username not found " + name + "!!"));
    }

    public String getPassword(String username) {
        return getUser(username).getPassword();
    }

    public String getUsername(String username) {
        return getUser(username).getUsername();
    }

    public boolean checkUserAuth(String username, String pass) {
        return Password.check(getPassword(username), pass).withBCrypt();
    }

}
