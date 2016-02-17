package com.epam.brest.myproject.service;

import com.epam.brest.myproject.domain.User;
import com.epam.brest.myproject.dto.UserDto;

import java.util.List;

/**
 * Created by bendar on 10.11.15.
 */
public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Integer userId);

    public User getUserByLogin(String login);

    public Integer addUser(User user);

    public void deleteUser(Integer userId);

//    public Integer getCountKard(String login);
//
//    public Integer getBalance(String login);

    UserDto getUserDto();
}
