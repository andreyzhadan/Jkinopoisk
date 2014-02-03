package com.zhadan.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 7/30/13
 * Time: 0:11
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -4106573189153307598L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    @Transient
    private String password2;
    @OneToMany(mappedBy = "user")
    private List<Recommendation> recommendations = new ArrayList<Recommendation>();

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int user_id) {
        this.id = user_id;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getUserName() + " / " + this.getPassword();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String login) {
        this.userName = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
