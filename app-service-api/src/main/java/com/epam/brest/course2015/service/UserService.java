package com.epam.brest.course2015.service;

import com.epam.brest.course2015.User;


import java.util.List;

/**
 * Created by bendar on 14.10.15.
 */
public interface UserService {

    public List<User> getAllUsers();

    public Integer addUser(User user);

    public User getUserById(Integer userId);

    public User getUserByLogin(String login);

    public void updateUser(User user);

    public void deleteUser(Integer userId);


}
