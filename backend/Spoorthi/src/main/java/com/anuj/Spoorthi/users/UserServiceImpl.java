package com.anuj.Spoorthi.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public String addUser(UserRequest userRequest) {
        UserEntity newUser = new UserEntity();

        UserEntity userCreated = null;
        BeanUtils.copyProperties(userRequest,newUser);
        try {
            userCreated = userRepository.save(newUser);
            log.info(userCreated.toString());
        } catch (DataIntegrityViolationException e) {
            log.error("Database constraint violation while saving entity: {}", userCreated, e);
            // Handle database constraint violation
        } catch (Exception e) {
            log.error("Exception occurred while saving the user!! " , e);
        }

        if (userCreated == null) {
            return null;
        }

        return "User Created successfully";
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String updateUser(String username, UserRequest userRequest) {
        UserEntity existingUser = getUser(username);
        if (existingUser == null) {
            return null;
        }
        String existingEmail = existingUser.getEmail();

        BeanUtils.copyProperties(userRequest,existingUser);
        existingUser.setEmail(existingEmail);

        try {
            userRepository.save(existingUser);
        } catch (DataIntegrityViolationException dive) {
            log.info("Database constraint violation while saving entity: {}", existingUser, dive);
            return null;
        } catch (Exception e) {
            log.info("Exception occurred while saving the user!! " , e);
            return null;
        }

        return "updated";
    }

    @Override
    public String deleteUser(String username) {
        UserEntity existingUser = getUser(username);
        if (existingUser == null) {
            return null;
        }
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
        return "deleted";
    }
}
