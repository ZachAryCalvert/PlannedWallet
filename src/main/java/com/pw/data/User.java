package com.pw.data;

import com.pw.exception.UsernameLengthTooShort;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Planned Wallet user entity.
 */
@Table(name = "pw_user",
    indexes = {@Index(name = "pwidx_user_username", columnList = "username", unique = true)}
)
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1234345L;

    @Id
    @GeneratedValue
    private long id;

    private String guid;

    /**
     * Unique username, must be case insensitive.
     */
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
        if (StringUtils.isEmpty(username)) {
            throw new UsernameLengthTooShort(String.format("Username '%s' too length", username));
        }

        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
