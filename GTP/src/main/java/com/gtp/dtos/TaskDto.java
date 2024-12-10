package com.gtp.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gtp.models.TaskModel;


public class TaskDto {

        private UUID idTarefa;
        private String titulo;
        private String descricao;
        private LocalDateTime dataInicio;
        private LocalDateTime dataFim;
        private String status;
        private String prioridade;
        private String nomeUsuario;

        public TaskDto() {
        }

        public TaskDto(TaskModel task) {
            this.idTarefa = task.getIdTarefa();
            this.titulo = task.getTitulo();
            this.descricao = task.getDescricao();
            this.dataInicio = task.getDataInicio();
            this.dataFim = task.getDataFim();
            this.status = task.getStatus();
            this.prioridade = task.getPrioridade();
            this.nomeUsuario = task.getIdUsuario().getNome();
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

        public String getIdUsuario() {
            return nomeUsuario;
        }

        public void setIdUsuario(String nomeUsuario) {
            this.nomeUsuario = nomeUsuario;
        }
}
