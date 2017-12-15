package br.com.oauth.config.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "Hello";
    }
    
}
