package com.epam.brest.myproject.rest;

import com.epam.brest.myproject.domain.User;
import com.epam.brest.myproject.dto.UserDto;
import com.epam.brest.myproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bendar on 18.11.15.
 */
@CrossOrigin
@RestController
public class UserRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers()
    {
        LOGGER.debug("getUsers()");
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserById(@PathVariable(value = "id") Integer id){
       return userService.getUserById(id);

    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
    }


    @RequestMapping(value = "/user/login/{login}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody User getUser(@PathVariable(value = "login") String login) {
        LOGGER.debug("getUser: login = {}", login);
        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/userdto", method = RequestMethod.GET)
    public @ResponseBody
    UserDto getUserDto() {
        LOGGER.debug("getUserDto()");
        return userService.getUserDto();
    }
}
