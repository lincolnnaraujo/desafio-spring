package br.com.desafio.desafiospring;

import br.com.desafio.desafiospring.model.Cargo;
import br.com.desafio.desafiospring.repository.CargoRepository;
import java.time.LocalDateTime;
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
 * CargoTest Author: Lincoln Araujo
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class CargoTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CargoTest.class);

    @Autowired
    CargoRepository cargoRepository;

    Cargo novoCargo = new Cargo("Cargo Teste");

    @Test
    public void _0_Cadastrar_Novo_Cargo() {
        LOGGER.info("********** Cadastrar Novo Cargo ********** ");
        cargoRepository.save(novoCargo);

        LOGGER.info(novoCargo.toString());

        Assert.notNull(novoCargo, "Objeto não pode ser nulo");
    }

    @Test
    public void _1_Listar_Todos_Os_Cargos() {
        LOGGER.info("********** Listar todos os Cargos ********** ");
        List<Cargo> listaCargos = cargoRepository.findAll();

        for (Cargo c : listaCargos) {
            LOGGER.info(c.toString());
        }

        Assert.notEmpty(listaCargos, "A Lista de Cargos não pode estar vazia");
    }

}