package br.com.desafio.desafiospring.repository;

import br.com.desafio.desafiospring.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * UsuarioRepository Author: Lincoln Araujo
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    List<Usuario> findAllBySexoIgnoreCase(String _sexo);

    List<Usuario> findAllByStatusIgnoreCase(String _status);

    Optional<Usuario> findByEmailIgnoreCase(String _email);
}