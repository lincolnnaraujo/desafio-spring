package br.com.desafio.desafiospring.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * UsuarioKeys Author: Lincoln Araujo
 */

@Embeddable
public class UsuarioKeys {

    @Column(name = "numero_cpf")
    private String numCpf;

    @Column(name = "nome_usuario")
    private String nome;

    public UsuarioKeys() {
    }

    public UsuarioKeys(String _cpf, String _nome) {
        this.numCpf = _cpf;
        this.nome = _nome;
    }

    public String getNumCpf() {
        return numCpf;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UsuarioKeys))
            return false;
        UsuarioKeys that = (UsuarioKeys) o;
        return Objects.equals(getNome(), getNumCpf()) && Objects.equals(getNumCpf(), that.getNumCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getNumCpf());
    }
}