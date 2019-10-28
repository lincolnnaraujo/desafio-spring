package br.com.desafio.desafiospring.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

/**
 * Perfil
 * Author: Lincoln Araujo
 */

@Entity
@Table(name = "TB_PERFIL")
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id;

    @NotNull @NotEmpty @Length(min = 3)
    @Column(name= "descricao")
    private String descricao;

    public Perfil(){}

    public Perfil(String _descricao)
    {
        this.descricao = _descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString()
    {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAuthority() {
        return this.descricao;
    }
}