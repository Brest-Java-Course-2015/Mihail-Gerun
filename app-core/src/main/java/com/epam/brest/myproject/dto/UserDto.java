package com.epam.brest.myproject.dto;

import com.epam.brest.myproject.domain.User;

import java.util.List;

/**
 * Created by bendar on 05.12.15.
 */
public class UserDto {

    private List<User> users;

    private int total;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
