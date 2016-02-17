package com.epam.brest.myproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bendar on 25.10.15.
 */
public class User {
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    private Integer userId;
    private String login;
    private Integer allBalance;
    private Integer countKardOnUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdUserDate;


    public User()
    {

    }

    public User(String login)
    {
        this.login=login;
    }

    public User(Integer id)
    {
        this.userId=id;
    }

    public User(Integer userId, String login,Integer countKardOnUser, Integer allBalance, Date createdUserDate) {
        this.userId=userId;
        this.login=login;
        this.allBalance = allBalance;
        this.countKardOnUser = countKardOnUser;
        this.createdUserDate = createdUserDate;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreatedUserDate() {
        return createdUserDate;
    }

    public void setCreatedUserDate(Date createdUserDate) {
        this.createdUserDate = createdUserDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAllBalance() {
        return allBalance;
    }

    public void setAllBalance(Integer allBalance) {
        this.allBalance = allBalance;
    }

    public Integer getCountKardOnUser() {
        return countKardOnUser;
    }

    public void setCountKardOnUser(Integer countKardOnUser) {
        this.countKardOnUser = countKardOnUser;
    }

    public boolean equals(Object obj) {
        return login.equals(((User) obj).getLogin());
    }

    @Override
    public String toString() {
        return String.format("User: {" +
                "userId=" + userId +
                ", login='" + login  +
                ", countKardOnUser=" +countKardOnUser +
                ", allBalance=" +allBalance +
                ", createdDate=" + DATE_FORMAT.format(createdUserDate) +
                '}');
    }
}



