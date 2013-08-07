package com.zhadan.bean;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:38
 */
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private int birthday;
    private String firstName;
    private String lastName;
    private String picture;
    private List<Movie> movies;

    public Actor() {
    }

    public Actor(String firstName, String lastName, int birthday, String country) {
        this.country = country;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
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
        StringBuilder builder = new StringBuilder();
        builder.append("id = ");
        builder.append(this.getId());
        builder.append(" name = ");
        builder.append(this.getFirstName());
        builder.append(this.getLastName());
        builder.append(" birthday = ");
        builder.append(this.getBirthday());
        return builder.toString();
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
}
