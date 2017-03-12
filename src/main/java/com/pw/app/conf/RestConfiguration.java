package com.pw.app.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Zach on 3/11/17.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.pw.rest"
})
public class RestConfiguration {
}
