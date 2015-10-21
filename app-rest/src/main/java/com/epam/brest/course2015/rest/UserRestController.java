package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.User;
import com.epam.brest.course2015.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bendar on 16.10.15.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers() {
       return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{login}/{password}", method = RequestMethod.POST)
    public @ResponseBody Integer addUser(@PathVariable(value = "login") String login,
                                         @PathVariable(value = "password") String password) {
        return userService.addUser(new User(login, password));
    }

    @RequestMapping(value = "/user/{id}/{password}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(@PathVariable(value = "id") Integer id,
                                         @PathVariable(value = "password") String password) {
        userService.updateUser(new User(id, password));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserById(@PathVariable(value = "id") Integer id){
       return userService.getUserById(id);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserByLogin(@RequestParam(value = "login") String login){
       return userService.getUserByLogin(login);
    }


}
