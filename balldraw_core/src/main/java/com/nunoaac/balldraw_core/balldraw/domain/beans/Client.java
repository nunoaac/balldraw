package com.nunoaac.balldraw_core.balldraw.domain.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long id = null;
    @Column(nullable=false, unique=true)   //we will remove the username setter method to make impossible to edit a client's username on the db
    private String username;
    @Column(nullable=false)
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

    /*   
    //commenting this method will forbid the system to change the username on the DB
    public void setUsername(String username) {
        this.username = username;
    }
    */

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

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.hashPassword == null) ? (other.hashPassword != null) : !this.hashPassword.equals(other.hashPassword)) {
            return false;
        }
        return true;
    }
}
