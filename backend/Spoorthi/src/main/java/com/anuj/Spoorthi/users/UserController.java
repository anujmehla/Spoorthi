package com.anuj.Spoorthi.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        System.out.println(userRequest);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        String user =userService.addUser(userRequest);

        if (user == null) {
            return new ResponseEntity<>("User not created.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User is created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        UserEntity userFound = userService.getUser(username);
        if (userFound == null) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }

    @PutMapping("update/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username,
                                        @Valid @RequestBody UserRequest userRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        String userUpdated = userService.updateUser(username, userRequest);

        if (userUpdated == null) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User is updated successfully",HttpStatus.OK);
    }

    @PutMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        String deleted = userService.deleteUser(username);

        if (deleted == null) {
            return new ResponseEntity<>("User not found!!!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User is deleted successfully",HttpStatus.OK);
    }

}
