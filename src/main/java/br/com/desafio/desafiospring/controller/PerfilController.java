package br.com.desafio.desafiospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PerfilController
 * Author: Lincoln Araujo
 */

@RestController
@RequestMapping("/perfil")
public class PerfilController 
{

    @GetMapping("/info")
    public String index(){
        return "Perfil - Controller";
    }    
}