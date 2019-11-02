package br.com.desafio.desafiospring.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.desafio.desafiospring.model.Cargo;
import br.com.desafio.desafiospring.repository.CargoRepository;

/**
 * CargoController Author: Lincoln Araujo
 */

@RestController
@RequestMapping("/cargo")
public class CargoController {

    private static Logger LOGGER = LoggerFactory.getLogger(CargoController.class);

    @Autowired
    CargoRepository cargorepository;

    @GetMapping("/info")
    public String index() {
        return "Cargo - Controller";
    }

    @GetMapping("/lista")
    public List<Cargo> lista() {
        List<Cargo> cargos = cargorepository.findAll();
        return cargos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cargo> cadastrar(@RequestBody @Valid Cargo novo, UriComponentsBuilder uriBuilder) {
        cargorepository.save(novo);

        URI uri = uriBuilder.path("/cargos/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> detalhar(@PathVariable Long id) {
        Optional<Cargo> cargo = cargorepository.findById(id);

        if (cargo.isPresent()) {
            return ResponseEntity.ok(cargo.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Cargo> atualizar(@PathVariable Long id, @RequestBody Cargo cargoAtualizado,
            UriComponentsBuilder uriBuilder) throws HttpRequestMethodNotSupportedException {
        Optional<Cargo> optional = cargorepository.findById(id);
        if (optional.isPresent()) {
            try {
                cargorepository.save(cargoAtualizado);
                URI uri = uriBuilder.path("/update/{id}").buildAndExpand(id).toUri();

                return ResponseEntity.created(uri).body(cargoAtualizado);
            } catch (HttpClientErrorException e) {
                LOGGER.error("HttpClientErrorException: " + e);
            } catch (Exception e) {
                LOGGER.error("Exception: " + e);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Cargo> optional = cargorepository.findById(id);
        if (optional.isPresent()) {
            cargorepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}