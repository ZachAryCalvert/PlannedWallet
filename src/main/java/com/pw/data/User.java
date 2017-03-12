package com.pw.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Zach on 3/11/17.
 */
@Table(name = "pw_user")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1234345L;

    @Id
    @GeneratedValue
    private long id;

    private String guid;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="hash", unique = false, nullable = false)
    private String passwordHash;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
