package com.zhadan.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/3/13
 * Time: 10:36 AM
 */
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = -747693149770306655L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String roleName;

    public Role() {
    }

    public Role(int userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getUserId() + " / " + this.getRoleName();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
