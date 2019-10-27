package br.com.desafio.desafiospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsuarioCotroller
 * Author: Lincoln Araujo
 */


@RestController
@RequestMapping("/usuario")
public class UsuarioCotroller 
{
    @GetMapping("/info")
    public String index(){
        return "Usuário - Controller";
    }
    
}