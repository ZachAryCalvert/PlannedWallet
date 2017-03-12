package com.pw.data.repository;

import com.pw.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zach on 3/11/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // TODO - see later
    // http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html
}
