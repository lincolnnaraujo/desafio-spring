package br.com.desafio.desafiospring.controller;

import br.com.desafio.desafiospring.model.Usuario;
import br.com.desafio.desafiospring.repository.UsuarioRepository;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;




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
    @Cacheable(value = "listaDeUsuarios")
    public Page<Usuario> lista(
        @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return usuarios;
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario novo, UriComponentsBuilder uriBuilder)
    {
        usuarioRepository.save(novo);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

}