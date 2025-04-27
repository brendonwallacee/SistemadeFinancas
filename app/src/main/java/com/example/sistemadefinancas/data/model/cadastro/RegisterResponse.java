package com.example.sistemadefinancas.data.model.cadastro;

@SuppressWarnings("unused")
public class RegisterResponse {

    private String id;
    private String email;
    private String login;
    private String senha;
    private String funcao;


    public RegisterResponse() {
    }

    public String getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

}
