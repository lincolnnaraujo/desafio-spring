package br.com.desafio.desafiospring.controller;

import br.com.desafio.desafiospring.model.Usuario;
import br.com.desafio.desafiospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsuarioCotroller Author: Lincoln Araujo
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioCotroller {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/info")
    public String index() {
        return "Usuário - Controller";
    }

    @GetMapping("/lista")
    public Page<Usuario> lista(
        @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return usuarios;
    }

}