package com.example.sistemadefinancas.data.model.cadastro;

public class RegisterRequest {

    private String email, login, senha;
    @SuppressWarnings("unused")
    private final String funcao = "USUARIO";

    public RegisterRequest(String email, String login, String senha) {
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
