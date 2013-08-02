package com.zhadan.bean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:35
 */
public class Movie {
    private int id;
    private String name;
    private String russianName;
    private int year;
    private String country;
    private float rating;
    private String slogan;
    private List<Actor> actors;

    public Movie() {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
