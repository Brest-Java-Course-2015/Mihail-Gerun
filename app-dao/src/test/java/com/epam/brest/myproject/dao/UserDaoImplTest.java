package com.epam.brest.myproject.dao;

import com.epam.brest.myproject.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bendar on 05.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USER_LOGIN1 = "userLogin1";
    private static final Integer USER_ID=1;
    private static final User user = new User("login");

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("Test: getAllUsers()");
        List<User> users = userDao.getAllUsers();
        assertTrue(users.size() == 2);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        LOGGER.debug("Test: getUserByLogin()");
        User user = userDao.getUserByLogin(USER_LOGIN1);
        assertNotNull(user);
        assertTrue(user.getLogin().equals(USER_LOGIN1));
    }

    @Test
    public void testGetUserById() throws Exception {
        LOGGER.debug("Test: getUserById()");
        User user = userDao.getUserById(USER_ID);
        assertNotNull(user);
        assertTrue(user.getUserId().equals(USER_ID));
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("test: addUser()");
        Integer userId = userDao.addUser(user);
        assertNotNull(userId);
        User newUser = userDao.getUserById(userId);
        assertNotNull(newUser);
        assertTrue(user.getLogin().equals(newUser.getLogin()));
        assertNotNull(newUser.getCreatedUserDate());
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("Test: deleteUser()");
        List<User> users = userDao.getAllUsers();
        assertTrue(users.size() > 0);
        int sizeBefore = users.size();
        userDao.deleteUser(users.get(0).getUserId());
        assertTrue((sizeBefore - 1) == userDao.getAllUsers().size());
    }


    @Test
    public void testCountUsers() throws Exception {
        LOGGER.debug("test: countUsers()");
        String login = userDao.getAllUsers().get(0).getLogin();
        assertNotNull(login);
        Integer usersCount = userDao.getCountUsers(login);
        assertNotNull(usersCount);
        assertTrue(usersCount.equals(1));
    }

    @Test
    public void testZeroCountUsers() throws Exception {
        LOGGER.debug("test: zeroCountUsers()");
        String login = "qweqweqwe";
        Integer usersCount = userDao.getCountUsers(login);
        assertNotNull(usersCount);
        assertTrue(usersCount.equals(0));
    }
}