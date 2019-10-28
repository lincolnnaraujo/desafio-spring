package br.com.desafio.desafiospring;

import br.com.desafio.desafiospring.model.Perfil;
import br.com.desafio.desafiospring.model.Usuario;
import br.com.desafio.desafiospring.model.UsuarioKeys;
import br.com.desafio.desafiospring.repository.PerfilRepository;
import br.com.desafio.desafiospring.repository.UsuarioRepository;
import br.com.desafio.desafiospring.utils.ValidaCpf;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


/**
 * UsuarioTest Author: Lincoln Araujo
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {
    private static Logger LOGGER = LoggerFactory.getLogger(Usuario.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String aniversario = "16/08/2016";
    LocalDate aniversarioConvertido = LocalDate.parse(aniversario, formatter);
    LocalDateTime hoje = LocalDateTime.now();

    @Test
    public void _0_Cadastrar_Novo_Usuario() {
        Perfil adm = perfilRepository.getById(1L);
        List<Perfil> listaPerfis = new ArrayList<Perfil>();
        listaPerfis.add(adm);
    
        UsuarioKeys keys = new UsuarioKeys("09374970023", "Stephen Vincent Strange");
        Usuario dr = new Usuario(keys, aniversarioConvertido, "stephen.strange@marvel.com", "123", "M", "A", hoje, hoje, 1L, listaPerfis);

        LOGGER.info("********** Cadastrar Novo Usuario ********** ");
        if (ValidaCpf.isCPF(dr.getKeys().getNumCpf().trim())) {
            usuarioRepository.save(dr);
        }

        LOGGER.info(dr.toString());
        Assert.notNull(dr, "O Objeto não pode ser nulo");
        Assert.isTrue(ValidaCpf.isCPF(dr.getKeys().getNumCpf().trim()), "O CPF precisa ser válido");
    }

    @Test
    public void _1_Listar_Todos_Os_Usuarios() {
        LOGGER.info("********** Listar Todos Os Usuários ********** ");
        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        for (Usuario usuario : listaUsuarios) {
            LOGGER.info(usuario.toString());
        }
    }
}