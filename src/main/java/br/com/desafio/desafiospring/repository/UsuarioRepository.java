package br.com.desafio.desafiospring.repository;

import br.com.desafio.desafiospring.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * UsuarioRepository Author: Lincoln Araujo
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    @Override
    List<Usuario> findAll();

    List<Usuario> findAllBySexoIgnoreCase(String _sexo);

    List<Usuario> findAllByStatusIgnoreCase(String _status);
}