package com.paopao.web.controller;

import com.paopao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @RequestMapping("/")
    public List findAll(){
        return testService.findAll();
    }
}
