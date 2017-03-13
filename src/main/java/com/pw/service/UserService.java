package com.pw.service;

import com.pw.data.User;
import com.pw.data.repository.UserRepository;
import com.pw.exception.PasswordLengthTooShort;
import com.pw.exception.UsernameLengthTooShort;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by Zach on 3/11/17.
 */
@Service
public class UserService {

    private Log logger = LogFactory.getLog(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${user.init.username}")
    private String initUsername;

    @Value("${user.password.min.length}")
    private int minPasswordLength;

    @Value("${user.username.min.length}")
    private int minUsernameLength;

    @Value("${user.init.password}")
    private String initPassword;

    @PostConstruct
    public void initDatabaseState() throws Exception {
        long userCount = userRepository.count();
        if (userCount == 0) {
            userRepository.save(createNewUser(initUsername, initPassword));
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        username = validateUsernameAndEnforceCaseStorage(username);
        User found = userRepository.findByUsername(username);
        if (found != null && BCrypt.checkpw(password, found.getPasswordHash())) {
            return found;
        }
        logger.warn(String.format("Received invalid get by username, searching for '%s'", username));
        return null;
    }

    public boolean updatePassword(String username, String passwordOld, String passwordNew) {
        User toUpdate = getUserByUsernameAndPassword(username, passwordOld);
        if (toUpdate != null) {
            toUpdate.setPasswordHash(genHash(passwordNew));
            userRepository.save(toUpdate);
            return true;
        }
        return false;
    }

    public User createNewUser(String username, String password) {
        username = validateUsernameAndEnforceCaseStorage(username);
        User user = new User();
        user.setGuid(UUID.randomUUID().toString());
        user.setPasswordHash(genHash(password));

        // we're going to store all usernames in upper case to enforce uniqueness
        user.setUsername(StringUtils.upperCase(username));
        return user;
    }

    private String genHash(String password) {
        if (StringUtils.isEmpty(password) || password.length() < minPasswordLength) {
            throw new PasswordLengthTooShort("Password length too short");
        }
        // see bcrypt
        // https://github.com/jeremyh/jBCrypt

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private String validateUsernameAndEnforceCaseStorage(String username) {
        if (StringUtils.isEmpty(username) || username.length() < minUsernameLength) {
            throw new UsernameLengthTooShort(String.format("Username '%s' too short", username));
        }
        return StringUtils.upperCase(username);
    }
}
