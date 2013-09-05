package com.zhadan.bean;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:35
 */
@Entity
public class Movie implements Serializable {
    private static final long serialVersionUID = 6010503502743285804L;
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ACTOR_ID")})
    private List<Actor> actors;

    public Movie(String name, String russianName, float rating, String slogan, int year, String country) {
        this.country = country;
        this.name = name;
        this.slogan = slogan;
        this.rating = rating;
        this.russianName = russianName;
        this.year = year;
    }

    public Movie(String name, String russianName, float rating, String slogan, int year, String country, List<Actor> actors) {
        this.country = country;
        this.name = name;
        this.slogan = slogan;
        this.rating = rating;
        this.russianName = russianName;
        this.year = year;
        this.actors = actors;
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
