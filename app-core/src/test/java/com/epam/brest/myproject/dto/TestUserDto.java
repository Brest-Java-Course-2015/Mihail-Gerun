package com.epam.brest.myproject.dto;

import com.epam.brest.myproject.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bendar on 18.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-spring-core.xml"})
public class TestUserDto {

    private static final Integer totalUsers = 5;
    @Autowired
    private UserDto userDto;
    @Autowired
    private User user;

    @Test
    public void testGetUsers() throws Exception {
        List<User> list = new ArrayList<User>() {};
        list.add(user);
        userDto.setUsers(list);
        assertNotNull(userDto.getUsers());
        assertTrue(userDto.getUsers().size() > 0);
        assertEquals(user.getClass(), userDto.getUsers().get(0).getClass());
    }

    @Test
    public void testGetTotalUsers() throws Exception {
        userDto.setTotal(totalUsers);
        assertNotNull(userDto.getTotal());
        assertTrue(userDto.getTotal() == totalUsers);
    }
}
