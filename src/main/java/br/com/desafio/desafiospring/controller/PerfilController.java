package br.com.desafio.desafiospring.controller;

import br.com.desafio.desafiospring.model.Perfil;
import br.com.desafio.desafiospring.repository.PerfilRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
 * PerfilController
 * Author: Lincoln Araujo
 */

@RestController
@RequestMapping("/perfil")
public class PerfilController 
{
    private static Logger LOGGER = LoggerFactory.getLogger(PerfilController.class);

    @Autowired
    private PerfilRepository perfilRepo;

    //Listar todos os Perfis
    @GetMapping("/lista")
    @Cacheable(value = "listaDePerfis")
    public List<Perfil> listarPerfis(){
        return perfilRepo.findAll();
    }

    //Buscar Perfil Por ID
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> detalharPerfil(@PathVariable Long id){
        Optional<Perfil> perfil = perfilRepo.findById(id);

        if(perfil.isPresent()){
            return ResponseEntity.ok(perfil.get());
        }
        return ResponseEntity.notFound().build();
    }

    //Criar um novo Perfil
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<Perfil> cadastrarPerfil(@RequestBody @Valid Perfil _novoPerfil, UriComponentsBuilder uriBuilder)
    {
        perfilRepo.save(_novoPerfil);
        URI uri = uriBuilder.path("/perfil/{id}").buildAndExpand(_novoPerfil.getId()).toUri();
        return ResponseEntity.created(uri).body(_novoPerfil);
    }

    //Alterar Perfil Existente por ID
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable Long id, @RequestBody @Valid Perfil _perfilAtualizado, UriComponentsBuilder uriBuilder)
    {
        Optional<Perfil> perfil = perfilRepo.findById(id);

        if( perfil.isPresent() ){
            try {
                perfilRepo.save(_perfilAtualizado);
                URI uri = uriBuilder.path("/update/{id}").buildAndExpand(id).toUri();

                return ResponseEntity.created(uri).body(_perfilAtualizado);
            } catch (HttpClientErrorException e){
                LOGGER.error("HttpClientErrorException: {}", e);
            } 
            catch (Exception e) {
                LOGGER.error("Exception: {}", e);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //Remover Perfil por ID
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity<?> removerPerfil(@PathVariable Long id)
    {
        Optional<Perfil> perfil = perfilRepo.findById(id);
        if (perfil.isPresent()){
            perfilRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}