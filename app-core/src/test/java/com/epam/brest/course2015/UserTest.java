package com.epam.brest.course2015;

import org.junit.Before;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by mikhail on 05.10.15.
 */
public class UserTest {
    private User user;
    private final Date testDate=new Date(2015,10,7);
    private final Integer testUserId=1;
    @Before
    public void setUp(){

        user=new User();
    }

    @org.junit.Test
    public void testGetLogin() throws Exception {
        user.setLogin("Login");
        assertEquals("Login", user.getLogin());

    }

    @org.junit.Test
    public void testGetPassword() throws Exception {
        user.setPassword("Password");
        assertEquals("Password", user.getPassword());
    }


    @org.junit.Test
    public void testGetCreatedData() throws Exception {
        user.setCreatedDate(testDate);
        assertEquals(testDate,user.getCreatedDate());
    }
    @org.junit.Test
    public void testGetUserId() throws Exception {
        user.setUserId(testUserId);
        assertEquals(testUserId, user.getUserId());
    }
}