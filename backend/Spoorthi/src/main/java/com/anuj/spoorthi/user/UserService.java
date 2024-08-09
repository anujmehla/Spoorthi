package com.anuj.spoorthi.users;


public interface UserService {

    String addUser(UserRequest userRequest);

    UserEntity getUser(String username);

    String updateUser(String username, UserRequest userRequest);

    String deleteUser(String username);

}
