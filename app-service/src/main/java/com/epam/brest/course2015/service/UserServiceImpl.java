package com.epam.brest.course2015.service;

import com.epam.brest.course2015.User;
import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Collections;
import java.util.List;

/**
 * Created by bendar on 14.10.15.
 */
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void logUser(User user) {
        LOGGER.debug("logUser(): user id = {} ", user.getUserId());
    }

    @Override
    public Integer getCountUsers(String login) {
        LOGGER.debug("getCountUsers()");
        Assert.notNull(login, "Login should not be null");
        Assert.hasText(login, "Login should be text");
        Assert.isTrue(login.length()>3, "Login length should be more than 3");
        return userDao.getCountUsers(login);

    }

    @Override
    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setTotal(userDao.getTotalUsersCount());
        if (userDto.getTotal() > 0) {
            userDto.setUsers(userDao.getAllUsers());
        } else {
            userDto.setUsers(Collections.<User>emptyList());
        }
        return userDto;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return userDao.getAllUsers();

    }

    @Override
    public Integer addUser(User user) {
        LOGGER.debug("addUser(user):{}",user.getLogin(),user.getPassword(),user.getUserId(),user.getUpdatedDate());
        Assert.notNull(user, "User should not be null.");
        Assert.isNull(user.getUserId(), "Id should be null");
        Assert.notNull(user.getLogin(), "Login should not be null");
        Assert.hasText(user.getLogin(), "Login should be text");
        Assert.isTrue(user.getLogin().length()>3, "Login length should be more than 3");
        Assert.notNull(user.getPassword(), "Password should not be null");
        Assert.hasText(user.getPassword(), "Password should be text");
        Assert.isTrue(user.getPassword().length()>3, "Password length should be more than 3");
        Assert.notNull(user.getUpdatedDate(), "UpdatedDate should not be null");
        if (userDao.getCountUsers(user.getLogin()) > 0) {
            throw new IllegalArgumentException("User login should be unique.");
        }
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(Integer userId) {
        LOGGER.debug("getUserById({}):",userId);
        Assert.notNull(userId, "Id should not be null");
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        LOGGER.debug("getUserBylogin({}):",login);
        Assert.notNull(login, "Login should not be null");
        Assert.hasText(login, "Login should be text");
        Assert.isTrue(login.length()>3, "Login lenght should be > 3");
        return userDao.getUserByLogin(login);
    }

    @Override
    public void updateUser(User user) {
        LOGGER.debug("updateUser(user):{}",user.getUserId(),user.getPassword(),user.getUpdatedDate());
        Assert.notNull(user, "User should not be null.");
        Assert.notNull(user.getUserId(), "Id should not be null");
        Assert.notNull(user.getPassword(), "Password should not be null");
        Assert.hasText(user.getPassword(), "Password should be text");
        Assert.isTrue(user.getPassword().length()>3, "Password length should be more than 3");
        Assert.notNull(user.getUpdatedDate(), "UpdatedDate should not be null");
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        LOGGER.debug("deleteUser():{}",userId);
        Assert.notNull(userId, "Id should not be null");
        userDao.deleteUser(userId);
    }
}
