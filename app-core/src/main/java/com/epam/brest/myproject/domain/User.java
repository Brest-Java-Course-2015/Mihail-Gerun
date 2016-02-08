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
    private Integer countKard;
    private Integer balance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdDate;


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

    public User(Integer userId, String login,Integer balance,Integer countKard, Date createdDate) {
        this.userId=userId;
        this.login=login;
        this.countKard=countKard;
        this.balance=balance;
        this.createdDate=createdDate;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCountKard() {
        return countKard;
    }

    public void setCountKard(Integer countKard) {
        this.countKard = countKard;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public boolean equals(Object obj) {
        return login.equals(((User) obj).getLogin());
    }

    @Override
    public String toString() {
        return String.format("User: {" +
                "userId=" + userId +
                ", login='" + login  +
                ", countKard='" + countKard  +
                ", balance='" + balance  +
                ", createdDate=" + DATE_FORMAT.format(createdDate) +
                '}');
    }
}



