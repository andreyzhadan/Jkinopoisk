package com.zhadan.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:35
 */
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String name;
    private String slogan;
    private float rating;
    private String russianName;
    private int year;
    private String picture;
    private List<Actor> actors;

    public Movie(String name, String russianName, float rating, String slogan, int year, String country) {
        this.country = country;
        this.name = name;
        this.slogan = slogan;
        this.rating = rating;
        this.russianName = russianName;
        this.year = year;
    }

    public Movie() {

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id = ");
        builder.append(this.getId());
        builder.append(" name = ");
        builder.append(this.getName());
        builder.append(" year = ");
        builder.append(this.getYear());
        return builder.toString();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
