package com.gtp.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "tarefas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTarefa")
public class TaskModel implements Serializable{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tarefa")
    private UUID idTarefa;

    private String titulo;
    private String descricao;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private String status;
    private String prioridade;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    @JsonBackReference
    private UserModel usuario;

    public TaskModel() {
    }

    public TaskModel(String titulo, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim, UserModel usuario, String status, String prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuario = usuario;
        this.status = status;
        this.prioridade = prioridade;
    }

    public UserModel getUsuario() {
        return usuario;
    }
    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }
    public UUID getIdTarefa() {
        return idTarefa;
    }
    public void setIdTarefa(UUID idTarefa) {
        this.idTarefa = idTarefa;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDateTime getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }



}
