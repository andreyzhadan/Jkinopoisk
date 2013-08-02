package com.zhadan.bean;

import javax.persistence.Entity;

/**
 * Created by azhadan on 7/30/13.
 */
@Entity
public class User {
    private String login;

    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.getLogin() + " / " + this.getPassword();
    }
}
