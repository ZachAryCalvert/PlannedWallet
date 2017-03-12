package com.pw.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zach on 3/11/17.
 */
@RestController
public class HeartBeat {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
