package com.anuj.spoorthi.users;

import com.anuj.spoorthi.address.AddressEntity;
import com.anuj.spoorthi.address.AddressRepository;
import com.anuj.spoorthi.address.AddressRequest;
import com.anuj.spoorthi.customexceptions.ResourceNotFoundException;
import com.anuj.spoorthi.customexceptions.UserNotFoundException;
import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;


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

//    @Transactional
    @Override
    public String updateUser(String username, UserRequest userRequest) {
        UserEntity existingUser = getUser(username);
        if (existingUser == null) {
            return null;
        }

        AddressEntity addressEntity = addressRepository.findById(existingUser.getAddress().getId())
                .orElseThrow(()-> new ResourceNotFoundException("No Such Address found "));

        BeanUtils.copyProperties(userRequest.getAddress(),addressEntity);

        existingUser.setAddress(addressEntity);
        BeanUtils.copyProperties(userRequest,existingUser);

        UserEntity save = userRepository.save(existingUser);
        log.info(existingUser.toString());


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
