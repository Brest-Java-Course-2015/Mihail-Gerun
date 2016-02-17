package com.epam.brest.myproject.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by bendar on 26.10.15.
 */
public class TestUser {

    private static final Logger LOGGER = LogManager.getLogger();

    private User user;
    private String testLogin;
    private Integer testUserId;
    private Date testDate;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception
    {
        testLogin = "userLogin1";
        user = new User();
        testUserId = 5;
        testDate = new Date();
        user1 = new User(testLogin);
        user2 = new User(testUserId);
        user3 = new User(testUserId, testLogin,1,1, testDate);
    }

    @Test
    public void testGetLogin()
    {
        LOGGER.debug("test: getLogin()");
        user.setLogin("Login");
        assertEquals("Login", user.getLogin());
    }


    @Test
    public void testGetUserId()
    {
        LOGGER.debug("test: getUserId()");
        user.setUserId(testUserId);
        assertEquals(testUserId, user.getUserId());
    }


    @Test
    public void testGetCreatedUserDate()
    {
        LOGGER.debug("test: getCreatedUserDate()");
        user.setCreatedUserDate(testDate);
        assertEquals(testDate, user.getCreatedUserDate());
    }

    @Test
    public void testGetAllBalance()
    {
        LOGGER.debug("test: getAllBalance()");
        user.setAllBalance(1000);
        assertTrue(1000 == user.getAllBalance());
    }

    @Test
    public void testCountKardOnUser()
    {
        LOGGER.debug("test: getCountKardOnUser()");
        user.setCountKardOnUser(1);
        assertTrue(1 == user.getCountKardOnUser());
    }

    @Test
    public void testConst()
    {
        LOGGER.debug("test: Construktor");
        assertEquals(user1.getLogin(), testLogin);
        assertEquals(user3.getLogin(), testLogin);
        assertTrue(user2.getUserId()==testUserId);
        assertTrue(user3.getUserId()==testUserId);
        assertEquals(user3.getCreatedUserDate(),testDate);
    }

    @Test
    public void testEquals()
    {
        LOGGER.debug("test: equals User");
        assertTrue(user1.equals(user1));
        assertTrue(user1.equals(user3));
        user1.setLogin("test");
        assertTrue(!user1.equals(user3));
    }
}
