package com.anuj.spoorthi.users;

import com.anuj.spoorthi.address.AddressEntity;
import com.anuj.spoorthi.address.AddressRequest;
import com.anuj.spoorthi.customexceptions.UserNotFoundException;
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
        BeanUtils.copyProperties(userRequest,newUser);

        AddressRequest address = userRequest.getAddress();

        if(address !=null) {
            AddressEntity addressEntity = new AddressEntity();
            BeanUtils.copyProperties(address, addressEntity);
            newUser.setAddress(addressEntity);
        }

        System.out.println("in service");
        System.out.println(newUser);

        UserEntity userCreated = null;


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
      return userRepository.findByUsername(username).
                orElseThrow(()-> new UserNotFoundException("No such user exists "+ username));
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
