package com.gtp.dtos;

import java.util.UUID;
import java.util.stream.Collectors;

import com.gtp.models.UserModel;

import java.util.List;

public class UserDto {

    private UUID idUsuario;
    private String nome;
    private List<TaskDto> tarefas;

    public UserDto() {
    }

    public UserDto(UserModel user) {
        this.nome = user.getNome();
        this.idUsuario = user.getIdUsuario();
        this.tarefas = user.getTarefa().stream().map(TaskDto::new).collect(Collectors.toList());
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TaskDto> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TaskDto> tarefas) {
        this.tarefas = tarefas;
    }
}
