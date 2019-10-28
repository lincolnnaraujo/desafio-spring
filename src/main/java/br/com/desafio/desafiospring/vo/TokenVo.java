package br.com.desafio.desafiospring.vo;

/**
 * TokenVo
 * Author: Lincoln Araujo
 */
public class TokenVo {

    private String token;
    private String tipo;

    public TokenVo(String _token, String _tipo)
    {
       this.token = _token;
       this.tipo = _tipo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}