package com.epam.brest.myproject.service;

import com.epam.brest.myproject.dao.KardDao;
import com.epam.brest.myproject.dao.UserDao;
import com.epam.brest.myproject.domain.User;
import com.epam.brest.myproject.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Created by bendar on 10.11.15.
 */
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return userDao.getAllUsers();
    }

    @Override
    public Integer addUser(User user) {
        LOGGER.debug("addUser(): user = {} ", user);
        Assert.notNull(user, "User should not be null.");
        LOGGER.debug("addUser(): user login = {} ", user.getLogin());
        Assert.isNull(user.getUserId(), "User Id should be null.");
        Assert.hasText(user.getLogin(), "User login should not be null.");
        if (userDao.getCountUsers(user.getLogin()) > 0) {
            throw new IllegalArgumentException("User login should be unique.");
        }
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(Integer userId) {
        LOGGER.debug("getUserByLogin(): user id = {} ", userId);
        Assert.notNull(userId, "User Id should not be null.");
        Assert.isTrue(userId > 0);
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin(): user login = {} ", login);
        Assert.notNull(login, "User login should not be null.");
        Assert.hasText(login, "User login should be text.");
        return userDao.getUserByLogin(login);
    }

    @Override
    public void deleteUser(Integer userId) {
        LOGGER.debug("deleteUser(): user id = {} ", userId);
        Assert.notNull(userId, "User Id should not be null.");
        Assert.isTrue(userId > 0);
        userDao.deleteUser(userId);
    }

    @Override
    public Integer getCountKard(String login) {
        LOGGER.debug("getCountKard(): login = {} ", login);
        Assert.notNull(login, "User login should not be null.");
        Assert.hasText(login, "User login should be text.");
        return userDao.getCountKard(login);
    }

    @Override
    public Integer getBalance(String login) {
        LOGGER.debug("getBalance(): login = {} ", login);
        Assert.notNull(login, "User login should not be null.");
        Assert.hasText(login, "User login should be text.");
        return userDao.getBalance(login);
    }

    @Override
    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        List<User> users = userDao.getAllUsers();
        userDto.setTotal(users.size());
        if (userDto.getTotal() > 0) {
            for(int i=0;i<users.size();i++)
            {
                String login = users.get(i).getLogin();
                userDao.setCountKardAndBalance(login,getBalance(login),getCountKard(login));
            }
            userDto.setUsers(userDao.getAllUsers());
        } else {
            userDto.setUsers(Collections.<User>emptyList());
        }
        return userDto;
    }
}