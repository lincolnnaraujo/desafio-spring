package br.com.desafio.desafiospring.repository;

import br.com.desafio.desafiospring.model.Perfil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * PerfilRepository
 * Author: Lincoln Araujo
 */

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

    @Override
    List<Perfil> findAll();

    Perfil getByDescricaoIgnoreCase(String _descricao);

    Perfil getById(Long _id);
}