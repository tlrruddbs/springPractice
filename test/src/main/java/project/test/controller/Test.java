package project.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("test1")
    public String test1(){
        System.out.println("tttt");
        return "ok";
    }
    @GetMapping("test2")
    public String test2(){
        return "ok";
    }
}
