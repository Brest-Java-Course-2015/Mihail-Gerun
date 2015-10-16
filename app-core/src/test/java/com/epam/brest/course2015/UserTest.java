package com.epam.brest.course2015;

import org.junit.Before;

import java.util.Date;

import static com.epam.brest.course2015.User.UserFields.*;
import static org.junit.Assert.*;


/**
 * Created by mikhail on 05.10.15.
 */
public class UserTest {
    private User user;
    private final Date testDate=new Date(2015,10,7);
    private final Date testUpdatedDate=new Date(2015,10,16);
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

    @org.junit.Test
    public void testGetUpdatedDate() throws Exception {
        user.setUpdatedDate(testDate);
        assertEquals(testDate,user.getUpdatedDate());
    }

    @org.junit.Test
    public void testUser() throws Exception {
        User userTest = new User(testUserId,"Bendar","BenPas",testDate,testUpdatedDate);
        assertNotNull(userTest);
        assertEquals(testUserId, userTest.getUserId());
        assertEquals("Bendar",userTest.getLogin());
        assertEquals("BenPas", userTest.getPassword());
        assertEquals(testDate,userTest.getCreatedDate());
        assertEquals(testUpdatedDate,userTest.getUpdatedDate());
    }

    @org.junit.Test
    public void testUserFields() throws Exception{
        assertEquals(USER_ID.getValue(),"userId");
        assertEquals(LOGIN.getValue(),"login");
        assertEquals(PASSWORD.getValue(),"password");
        assertEquals(CREATED_DATE.getValue(),"createdDate");
        assertEquals(UPDATED_DATE.getValue(),"updatedDate");
    }

}