package br.com.desafio.desafiospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CargoController
 * Author: Lincoln Araujo
 */

@RestController
@RequestMapping("/cargo")
public class CargoController 
{
    
    @GetMapping("/info")
    public String index(){
        return "Cargo - Controller";
    }

}