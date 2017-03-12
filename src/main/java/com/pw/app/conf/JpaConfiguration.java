package com.pw.app.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Zach on 3/11/17.
 */

@Configuration
@EntityScan(basePackages = "com.pw.data")
@EnableJpaRepositories(basePackages = {"com.pw.data.repository"})
@EnableTransactionManagement
public class JpaConfiguration {
}
