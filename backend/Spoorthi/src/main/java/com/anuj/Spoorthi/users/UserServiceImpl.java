package com.anuj.Spoorthi.users;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public String addUser(UserRequest userRequest) {
        UserEntity newUser = new UserEntity();

        UserEntity userCreated;
        BeanUtils.copyProperties(userRequest,newUser);
        try {
            userCreated = repository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User already exists");
        }

        if (userCreated == null) {
            return null;
        }

        return "User Created successfully";
    }
}
