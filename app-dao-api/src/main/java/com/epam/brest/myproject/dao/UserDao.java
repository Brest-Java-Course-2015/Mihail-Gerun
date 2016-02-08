package com.epam.brest.myproject.dao;
import com.epam.brest.myproject.domain.User;

import java.util.List;

/**
 * Created by bendar on 28.10.15.
 */
public interface UserDao {

    public List<User> getAllUsers();

    public User getUserById(Integer userId);

    public User getUserByLogin(String login);

    public Integer addUser(User user);

    public void deleteUser(Integer userId);

    public Integer getCountUsers(String login);

    public Integer getCountKard(String login);

    public Integer getBalance(String login);

    void setCountKardAndBalance(String login, Integer balance, Integer countKard);
}
