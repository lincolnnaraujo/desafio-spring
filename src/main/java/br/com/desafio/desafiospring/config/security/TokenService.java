package br.com.desafio.desafiospring.config.security;

import br.com.desafio.desafiospring.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * TokenService
 * Author: Lincoln Araujo
 */
@Service
public class TokenService 
{

    @Value("${desafio.jwt.expiration}")
    private String expiration;

    @Value("${desafio.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Usuario usuLogado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        
        return Jwts.builder()
        .setIssuer("API Desafio Spring")
        .setSubject(usuLogado.getId().toString())
        .setIssuedAt(hoje)
        .setExpiration(dataExpiracao)
        .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String _token){
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(_token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String _token)
    {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(_token).getBody();
        return Long.parseLong(claims.getSubject());
    }
    
}