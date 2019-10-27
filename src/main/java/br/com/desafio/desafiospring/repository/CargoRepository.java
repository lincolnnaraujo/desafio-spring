package br.com.desafio.desafiospring.repository;

import br.com.desafio.desafiospring.model.Cargo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * CargoRepository Author: Lincoln Araujo
 */
public interface CargoRepository extends JpaRepository<Cargo, Long>
{
    @Override
    List<Cargo> findAll();

    Cargo getById(Long _id);

    Cargo getByDescricaoIgnoreCase(String _descricao);
}