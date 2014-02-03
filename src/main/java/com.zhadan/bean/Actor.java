package com.zhadan.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:38
 */
@Entity
@Table(name = "actor")
public class Actor implements Serializable {
    private static final long serialVersionUID = 114900396648141282L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private int birthday;
    private String firstName;
    private String lastName;
    private String picture;
    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<Movie>();

    public Actor() {
    }

    public Actor(int id, String firstName, String lastName, int birthday, String country) {
        this.id = id;
        this.lastName = lastName;
        this.birthday = birthday;
        this.firstName = firstName;
        this.country = country;
    }

    public Actor(String firstName, String lastName, int birthday, String country) {
        this.lastName = lastName;
        this.birthday = birthday;
        this.firstName = firstName;
        this.country = country;
    }

    public Actor(String country, int birthday, String firstName, String lastName, String picture, List<Movie> movies) {
        this.country = country;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.movies = movies;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "id = " + this.getId() + " fullName = " + this.getFullName() + " birthday = " + this.getBirthday();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
