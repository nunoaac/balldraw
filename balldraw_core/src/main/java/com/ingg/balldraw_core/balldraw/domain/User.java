package com.ingg.balldraw_core.balldraw.domain;

import java.util.List;

/**
 * Represents an User, with username, password, id and list of generated draws
 * for him
 *
 * @author support
 */
public class User {

    private String username;
    private String password;
    private long id;
    private List<BallDraw> draws;

    public User(String username, String password, long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BallDraw> getDraws() {
        return draws;
    }

    public void setDraws(List<BallDraw> draws) {
        this.draws = draws;
    }

}
