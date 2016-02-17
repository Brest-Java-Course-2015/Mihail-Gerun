package com.epam.brest.myproject.rest;

import com.epam.brest.myproject.domain.User;
import com.epam.brest.myproject.dto.UserDto;
import com.epam.brest.myproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by bendar on 19.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-mock.xml"})
public class UserControllerMockTest {

    @Resource
    private UserRestController userRestController;

    private MockMvc mockMvc;


    @Autowired
    private UserService userService;

    @Before
    public void setUp()
    {
        mockMvc = standaloneSetup(userRestController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown()
    {
        verify(userService);
        reset(userService);
    }

    @Test
    public void addUserTest() throws Exception {

        expect(userService.addUser(anyObject(User.class))).andReturn(3);
        replay(userService);

        String user =new ObjectMapper().writeValueAsString(new User("login2"));

        mockMvc.perform(post("/user").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(user)).andDo(print()).andExpect(status().isCreated()).andExpect(content().string("3"));
    }


    @Test
    public void getUserTest() throws Exception {

        expect(userService.getAllUsers()).andReturn(Arrays.asList(new User()));
        replay(userService);
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getUserByLoginTest() throws Exception {

        expect(userService.getUserByLogin(EasyMock.anyObject(String.class))).andReturn(new User());
        replay(userService);
        mockMvc.perform(get("/user/login/userLogin1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void getUserByIdTest() throws Exception {

        expect(userService.getUserById(anyInt())).andReturn(new User());
        replay(userService);
        mockMvc.perform(get("/user/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception {

        userService.deleteUser(anyInt());
        replay(userService);
        mockMvc.perform(delete("/user/delete/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getUserDto() throws Exception {
        expect(userService.getUserDto()).andReturn(new UserDto());
        replay(userService);
        mockMvc.perform(get("/userdto").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }
}
