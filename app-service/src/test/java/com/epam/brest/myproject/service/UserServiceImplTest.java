package com.epam.brest.myproject.service;

import com.epam.brest.myproject.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bendar on 10.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class UserServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

    private User user = new User("userLogin4");

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("test: getAllUsers()");
        Assert.assertTrue(userService.getAllUsers().size() > 0);
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("test: addUser()");
        int sizeBefore = userService.getAllUsers().size();
        userService.addUser(user);
        Assert.assertTrue(sizeBefore + 1 == userService.getAllUsers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws Exception {
        LOGGER.debug("test: addNullUser()");
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNotNullId() throws Exception {
        LOGGER.debug("test: addUserWithNotNullId()");
        User user = new User();
        user.setUserId(1);
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullLogin() throws Exception {
        LOGGER.debug("test: addUserWithNullLogin()");
        User user = new User();
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNotUniqueLogin() throws Exception {
        LOGGER.debug("test: addUserWithNotUniqueLogin()");
        User user = new User("login");
        userService.addUser(user);
        userService.addUser(user);
    }

    @Test
    public void testGetUserById() throws Exception
    {
        LOGGER.debug("test: getUserByID()");
        Integer id=1;
        userService.getUserById(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByIdWithIdNull() throws Exception
    {
        LOGGER.debug("test: getUserByIDWithIdNull()");
        Integer id=null;
        userService.getUserById(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByIdWithIdZero() throws Exception
    {
        LOGGER.debug("test: getUserByIDWithIdZero()");
        Integer id=0;
        userService.getUserById(id);
    }

    @Test
    public void testGetUserByLogin() throws Exception
    {
        LOGGER.debug("test: getUserByLogin()");
        String login="userLogin1";
        userService.getUserByLogin(login);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLoginWithNullLogin() throws Exception
    {
        LOGGER.debug("test: getUserByLoginWithNullLogin()");
        String login=null;
        userService.getUserByLogin(login);
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        LOGGER.debug("test: deleteUser()");
        userService.deleteUser(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserWithNullId() throws Exception
    {
        LOGGER.debug("test: deleteUserWithNullId()");
        Integer id = null;
        userService.deleteUser(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserWithZeroId() throws Exception
    {
        LOGGER.debug("test: deleteUserWithZeroId()");
        Integer id = 0;
        userService.deleteUser(id);
    }

    @Test
    public void testGetUserDto() throws Exception
    {
        LOGGER.debug("test: getUserDto()");
        userService.getUserDto();
    }
}