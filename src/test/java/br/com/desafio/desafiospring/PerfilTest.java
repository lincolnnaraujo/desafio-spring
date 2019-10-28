package br.com.desafio.desafiospring;

import br.com.desafio.desafiospring.model.Perfil;
import br.com.desafio.desafiospring.repository.PerfilRepository;
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
 * PerfilTest Author: Lincoln Araujo
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PerfilTest {
    private static Logger LOGGER = LoggerFactory.getLogger(PerfilTest.class);

    @Autowired
    private PerfilRepository perfilRepository;

    Perfil novoPerfil = new Perfil("Perfil Teste");

    @Test
    public void _0_Cadastrar_Novo_Perfil() {
        LOGGER.info("********** Cadastrar novo Perfil ********** ");
        perfilRepository.save(novoPerfil);
        LOGGER.info(novoPerfil.toString());
        Assert.notNull(novoPerfil, "Objeto não pode ser nulo");
    }

    @Test
    public void _1_Listar_Todos_Os_Perfis() {
        LOGGER.info("********** Listar todos os Perfis ********** ");
        List<Perfil> listaPerfis = perfilRepository.findAll();

        for (Perfil perfil : listaPerfis) {
            LOGGER.info(perfil.toString());
        }

        Assert.notEmpty(listaPerfis, "A Lista de Perfis não pode estar vazia");
    }

    @Test
    public void _2_Buscar_Perfil_Por_Id() {
        LOGGER.info("********** Buscar Perfil por ID ********** ");
        Perfil perfil = perfilRepository.getById(1L);

        LOGGER.info(perfil.toString());

        Assert.notNull(perfil, "Objeto não pode ser nulo");
    }

    @Test
    public void _3_Buscar_Perfil_Por_Descricao() {
        LOGGER.info("********** Buscar Perfil por Descrição ********** ");
        Perfil perfil = perfilRepository.getByDescricaoIgnoreCase("perfil teste");

        LOGGER.info(perfil.toString());

        Assert.notNull(perfil, "Objeto não pode ser nulo");
    }
}