package com.epam.brest.myproject.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

/**
 * Created by bendar on 25.10.15.
 */
public class Kard {

    @JsonDeserialize(using=com.epam.brest.myproject.test.JsonDateDeserializer.class)
    @JsonSerialize(using=com.epam.brest.myproject.test.JsonDateSerializer.class)
    private LocalDate updatedDate = new LocalDate();

    @JsonDeserialize(using=com.epam.brest.myproject.test.JsonDateDeserializer.class)
    @JsonSerialize(using=com.epam.brest.myproject.test.JsonDateSerializer.class)
    private LocalDate createdDate= new LocalDate();

    private String login;
    private Integer balance;
    private Integer kardId;
    private Integer userId;
    private String kardName;

    public Kard(){}

    public Kard(Integer id, String kardName)
    {
        this.kardId=id;
        this.kardName=kardName;
    }

    public Kard(String login, String kardName)
    {
        this.kardName=kardName;
        this.login=login;
    }

    public Kard(Integer userId,String login, String kardName)
    {
        this.userId=userId;
        this.kardName=kardName;
        this.login=login;
    }

    public Kard(Integer kardId,Integer userId,String login, String kardName,Integer balance, LocalDate createdDate, LocalDate updatedDate)
    {
        this.kardId=kardId;
        this.userId = userId;
        this.login=login;
        this.kardName=kardName;
        this.balance=balance;
        this.createdDate=createdDate;
        this.updatedDate=updatedDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getKardId() {
        return kardId;
    }

    public void setKardId(Integer kardId) {
        this.kardId = kardId;
    }

    public String getKardName() {
        return kardName;
    }

    public void setKardName(String kardName) {
        this.kardName = kardName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getUserId() { return userId;}

    public void setUserId(Integer userId) { this.userId = userId; }

    public boolean equals(Object obj) {
        return kardName.equals(((Kard) obj).getKardName());
    }

    @Override
    public String toString() {
        return String.format("Kard: {" +
                "kardId=" + kardId +
                ", userId=" + userId +
                ", login=" +login +
                ", kardName='" + kardName +
                ", balance=" + balance +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}');
    }

}
