package com.nunoaac.balldraw_core.balldraw.domain.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Represents an Client, with username, hashPassword, id and list of generated draws
 for him
 *
 * @author support
 */
@Entity
public class Client implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable=false, unique=true)
    private String username;
    @JoinColumn(nullable=false)
    private String hashPassword;
    @OneToMany(mappedBy = "client")
    private List<BallDraw> draws;

    public Client() {
    }

    public Client(String username, String hashPassword) {
        this.username = username;
        this.hashPassword = hashPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BallDraw> getDraws() {
        return draws;
    }

    public void setDraws(List<BallDraw> draws) {
        this.draws = draws;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", username=" + username + ", hashPassword=" + hashPassword + '}';
    }
    
    

}
