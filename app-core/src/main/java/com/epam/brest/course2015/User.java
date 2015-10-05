package com.epam.brest.course2015;
import java.util.Date;

/**
 * Created by mikhail on 05.10.15.
 */
public class User {
    private Integer UuserID;
    private String password;
    private String login;
    private Date createdData;

    public Integer getUuserID() {
        return UuserID;
    }

    public void setUuserID(Integer uuserID) {
        UuserID = uuserID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreatedData() {
        return createdData;
    }

    public void setCreatedData(Date createdData) {
        this.createdData = createdData;
    }
}
