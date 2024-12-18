package com.gtp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gtp.dtos.TaskCreateDTO;
import com.gtp.dtos.UserCreateDTO;
import com.gtp.dtos.UserDto;
import com.gtp.dtos.TaskDto;
import com.gtp.models.TaskModel;
import com.gtp.models.UserModel;
import com.gtp.repositories.TaskRepository;
import com.gtp.repositories.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GtpController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        List<UserModel> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(UserDto::new).collect(Collectors.toList());
        return userDtos;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.map(u -> ResponseEntity.ok(new UserDto(u)))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @PostMapping("/users")
    public ResponseEntity<UserCreateDTO> createUser(@RequestBody UserCreateDTO UserCreateDTO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(UserCreateDTO, userModel);
        userRepository.save(userModel);
        return new ResponseEntity<>(UserCreateDTO, HttpStatus.CREATED);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody TaskCreateDTO TaskCreateDTO) {
        try {
            Optional<UserModel> usuarioOptional = userRepository
                    .findById(UUID.fromString(TaskCreateDTO.getIdUsuario()));
            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
            }

            TaskModel taskModel = new TaskModel();
            UserModel userModel = new UserModel();

            BeanUtils.copyProperties(TaskCreateDTO, taskModel);
            List<TaskModel> tarefas = new ArrayList<>();
            tarefas.add(taskModel);
            userModel.setTarefa(tarefas);

            taskModel.setUsuario(usuarioOptional.get());

            TaskModel novaTask = taskRepository.save(taskModel);

            return ResponseEntity.status(HttpStatus.CREATED).body(novaTask);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar a tarefa: " + e.getMessage());
        }
    }

    @GetMapping("/tasks")
    public List<TaskDto> getTasks() {
        List<TaskModel> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserCreateDTO userCreateDTO) {
        Optional<UserModel> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserModel userModel = optionalUser.get();
            BeanUtils.copyProperties(userCreateDTO, userModel);
            userRepository.save(userModel);
            return ResponseEntity.ok(new UserDto(userModel));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @RequestBody TaskCreateDTO taskCreateDTO) {
        Optional<TaskModel> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            TaskModel taskModel = optionalTask.get();

            Optional<UserModel> usuarioOptional = userRepository
                    .findById(UUID.fromString(taskCreateDTO.getIdUsuario()));
            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário associado não encontrado.");
            }

            taskModel.setUsuario(usuarioOptional.get());
            BeanUtils.copyProperties(taskCreateDTO, taskModel, "id");
            taskRepository.save(taskModel);

            return ResponseEntity.ok(new TaskDto(taskModel));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    @Transactional
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        Optional<TaskModel> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
    }
}

// usando o id da tarefa
/*
 * exemplo de entrada para atualizar os dados:
 * {
 * "titulo": "teste2",
 * "descricao": "azul2",
 * "dataInicio": "2023-08-30T09:00:00",
 * "dataFim": "2025-08-31T09:02:00",
 * "status": "npendente",
 * "prioridade": "nalta",
 * "idUsuario": "39ac346b-f7d5-4d87-a547-b08f2e7dc9be"//usando o id do usuário
 * }
 */