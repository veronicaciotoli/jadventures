package com.generation.jadventures.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class Homepage 
{
    @GetMapping("/")
    public String getMethodName() 
    {
        return "index";
    }
    
}
