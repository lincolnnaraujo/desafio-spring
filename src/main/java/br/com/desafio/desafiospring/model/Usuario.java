package br.com.desafio.desafiospring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;





/**
 * Usuaio Author: Lincoln Araujo
 */

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Embedded
    private UsuarioKeys keys;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "email")
    private String email;

    @Column(name= "senha")
    private String senha;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "status_cadastro")
    private String status;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_cargo_usuario")
    private Long idCargo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfil = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(UsuarioKeys _keys, LocalDate _datanascimento, String _email, String _senha, String _sexo, String _status,
            LocalDateTime _datacriacao, LocalDateTime _dataatualizacao, Long _idcargo, List<Perfil> _perfil) {
        this.keys = _keys;
        this.dataNascimento = _datanascimento;
        this.email = _email;
        this.senha = _senha;
        this.sexo = _sexo;
        this.status = _status;
        this.dataCriacao = _datacriacao;
        this.dataAtualizacao = _dataatualizacao;
        this.idCargo = _idcargo;
        this.perfil = _perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioKeys getKeys() {
        return keys;
    }

    public void setKeys(UsuarioKeys keys) {
        this.keys = keys;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public List<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<Perfil> perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.perfil;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}