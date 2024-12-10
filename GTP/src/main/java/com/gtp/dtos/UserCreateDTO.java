package com.gtp.dtos;

public class UserCreateDTO {
    private String nome;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
