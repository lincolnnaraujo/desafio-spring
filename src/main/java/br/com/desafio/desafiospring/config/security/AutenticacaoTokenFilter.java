package br.com.desafio.desafiospring.config.security;

import br.com.desafio.desafiospring.model.Usuario;
import br.com.desafio.desafiospring.repository.UsuarioRepository;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * AutenticacaoTokenFilter Author: Lincoln Araujo
 */
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    
    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoTokenFilter(TokenService _tokenService, UsuarioRepository _usuarioRepository){
        this.tokenService = _tokenService;
        this.usuarioRepository = _usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);

        if (valido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        } else {
            return token.substring(7, token.length());
        }
    }

    private void autenticarUsuario(String _token){
        Long idUsuario = tokenService.getIdUsuario(_token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}