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
    private Integer testCountKard;
    private Integer testBalance;
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
        testBalance=5000;
        testCountKard=3;
        user1 = new User(testLogin);
        user2 = new User(testUserId);
        user3 = new User(testUserId, testLogin, testBalance, testCountKard, testDate);
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
    public void testGetCreatedDate()
    {
        LOGGER.debug("test: getCreatedDate()");
        user.setCreatedDate(testDate);
        assertEquals(testDate, user.getCreatedDate());
    }

    @Test
    public void testGetCountKard()
    {
        LOGGER.debug("test: getCountKard()");
        user.setCountKard(testCountKard);
        assertEquals(testCountKard,user.getCountKard());
    }

    @Test
    public void testGetBalance()
    {
        LOGGER.debug("test: getBalance()");
        user.setBalance(testBalance);
        assertEquals(testBalance,user.getBalance());
    }

    @Test
    public void testConst()
    {
        LOGGER.debug("test: Construktor");
        assertEquals(user1.getLogin(), testLogin);
        assertEquals(user3.getLogin(), testLogin);
        assertTrue(user2.getUserId()==testUserId);
        assertTrue(user3.getUserId()==testUserId);
        assertTrue(user3.getBalance()==testBalance);
        assertTrue(user3.getCountKard()==testCountKard);
        assertEquals(user3.getCreatedDate(),testDate);
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
