package com.pw.service;

import com.pw.app.Application;
import com.pw.exception.PasswordLengthTooShort;
import com.pw.exception.UsernameLengthTooShort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

/**
 * Created by Zach on 3/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*
 *  Could do https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
 *  @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes=MyApp.class, loader=SpringApplicationContextLoader.class)
-- tighter target
 */
@ContextConfiguration(classes= Application.class)
public class UserServiceTest {

    @Autowired
    private UserService toTest;

    @Test(expected = PasswordLengthTooShort.class)
    public void testPasswordLengthTooShort() {
        toTest.createNewUser("Zach", "C");
    }

    @Test(expected = UsernameLengthTooShort.class)
    public void testUsernameLengthTooShort() {
        toTest.createNewUser("Z", "iamaratherlengthypassword");
    }

    @Test
    public void findValidUsernameFromInitialLoad() {
        assertNotNull(toTest.getUserByUsernameAndPassword("admin", "plannedWalletStart"));
    }

    @Test
    public void findInvalidUsernameFromInitialLoadReturnsNull() {
        assertNull(toTest.getUserByUsernameAndPassword("admin", "plannedWalletStart1"));
    }
}
