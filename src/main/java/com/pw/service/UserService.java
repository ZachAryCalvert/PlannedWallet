package com.pw.service;

import com.pw.data.User;
import com.pw.data.repository.UserRepository;
import com.pw.exception.PasswordLengthTooShort;
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

    @Value("${user.init.password}")
    private String initPassword;

    @PostConstruct
    public void initDatabaseState() throws Exception {
        long userCount = userRepository.count();
        if (userCount == 0) {
            userRepository.save(createNewUser(initUsername, initPassword));
        }
    }

    public User createNewUser(String username, String password) {
        User user = new User();
        user.setGuid(UUID.randomUUID().toString());
        user.setPasswordHash(genHash(password));
        user.setUsername(username);
        return user;
    }

    public String genHash(String password) {
        if (StringUtils.isEmpty(password) || password.length() < minPasswordLength) {
            throw new PasswordLengthTooShort("Password length too short");
        }
        // see bcrypt
        // https://github.com/jeremyh/jBCrypt

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
