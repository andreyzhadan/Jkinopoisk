package com.zhadan.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 20.01.14
 * Time: 1:04
 */
@Entity
@Table(name = "taste_preferences")//,uniqueConstraints = {@UniqueConstraint(columnNames = "user_id", "movie_id")})
public class Recommendation implements Serializable {
    private static final long serialVersionUID = -5979958212739833062L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private int preference;

    public Recommendation() {
    }

    public Recommendation(int user_id, int movie_id, int preference) {
        this.user = new User(user_id);
        this.movie = new Movie(movie_id);
        this.preference = preference;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
