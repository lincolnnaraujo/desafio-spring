package br.com.desafio.desafiospring.config.security;

import br.com.desafio.desafiospring.model.Usuario;
import br.com.desafio.desafiospring.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * AutenticacaoService Author: Lincoln Araujo
 */

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmailIgnoreCase(username);

        if(usuario.isPresent()){
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados inválidos");
    }
}