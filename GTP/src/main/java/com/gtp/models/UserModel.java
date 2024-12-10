package com.gtp.models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;


@Entity
@Table(name = "usuarios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsuario;

    private String nome;

    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER ,orphanRemoval = true)
    @JsonManagedReference
    private List<TaskModel> tarefas;

    public UserModel() {
    }

    public UserModel(String nome) {
        this.nome = nome;
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


    public List<TaskModel> getTarefa() {
        return tarefas;
    }


    public void setTarefa(List<TaskModel> tarefas) {
        this.tarefas = tarefas;
    }
    
}
