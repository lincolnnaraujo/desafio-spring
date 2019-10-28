package br.com.desafio.desafiospring.controller;

import br.com.desafio.desafiospring.config.security.TokenService;
import br.com.desafio.desafiospring.model.LoginForm;
import br.com.desafio.desafiospring.vo.TokenVo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AutenticacaoController
 * Author: Lincoln Araujo
 */
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form)
    {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenVo(token, "Bearer"));
        }
        catch(AuthenticationException ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }
    
}