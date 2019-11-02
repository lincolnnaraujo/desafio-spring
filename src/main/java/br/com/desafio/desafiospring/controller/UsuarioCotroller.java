package br.com.desafio.desafiospring.controller;

import br.com.desafio.desafiospring.model.Usuario;
import br.com.desafio.desafiospring.repository.UsuarioRepository;
import java.net.URI;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * UsuarioCotroller Author: Lincoln Araujo
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioCotroller {
    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioCotroller.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    @Cacheable(value = "listaDeUsuarios")
    public Page<Usuario> listarUsuarios(
        @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return usuarios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> detalharUsuario(@PathVariable Long id)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid Usuario _novoUsuario, UriComponentsBuilder uriBuilder)
    {
        usuarioRepository.save(_novoUsuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(_novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(_novoUsuario);
    }

    //Atualiza um Usuario
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario _usuarioAtualizado,
    UriComponentsBuilder uriBuilder)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()){
            try {
                usuarioRepository.save(_usuarioAtualizado);
                URI uri = uriBuilder.path("/update/{id}").buildAndExpand(id).toUri();

                return ResponseEntity.created(uri).body(_usuarioAtualizado);
            } catch (HttpClientErrorException e){
                LOGGER.error("HttpClientErrorException: {}", e);
            } 
            catch (Exception e) {
                LOGGER.error("Exception: {}", e);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //Remover Usuario
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<?> removerUsuario(@PathVariable Long id)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}