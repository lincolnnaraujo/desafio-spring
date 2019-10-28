package br.com.desafio.desafiospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 * Author: Lincoln Araujo
 */

@RestController
public class IndexController 
{
    
    @GetMapping("/")
    public String Index()
    {
        return "Desafio Spring Boot";
    }
}