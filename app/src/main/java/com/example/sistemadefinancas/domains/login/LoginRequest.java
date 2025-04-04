package com.example.sistemadefinancas.domains.login;

public class LoginRequest {
    private final String login;
    private final String senha;

    public LoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
