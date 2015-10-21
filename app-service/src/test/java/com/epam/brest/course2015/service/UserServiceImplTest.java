package com.epam.brest.course2015.service;

import com.epam.brest.course2015.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by bendar on 14.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class UserServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("Test: getAllUsers()");
        Assert.assertTrue(userService.getAllUsers().size() > 0);
        userService.getAllUsers();
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("test: addUser()");
        User user = new User();
        user.setLogin("Vasia");
        user.setUserId(null);
        user.setPassword("Password");
        userService.addUser(user);
    }

    @Test
    public void testGetUserById() throws Exception {
        LOGGER.debug("test: getUserById()");
        userService.getUserById(2);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        LOGGER.debug("test: GetUserByLogin()");
        testAddUser();
        userService.getUserByLogin("Vasia");
    }

    @Test
    public void testUpdateUser() throws Exception {
        LOGGER.debug("test: updateUser()");
        User user = new User();
        user.setPassword("Password");
        user.setUpdatedDate(new Date(2015,10,15,18,8,0));
        user.setUserId(1);
        userService.updateUser(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("test: deleteUser()");
        userService.deleteUser(1);
    }
}