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

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user.get(), userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            Optional<UserModel> usuarioOptional = userRepository.findById(UUID.fromString(TaskCreateDTO.getIdUsuario()));
            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
            }
    
            TaskModel taskModel = new TaskModel();
            UserModel userModel = new UserModel();
            
            BeanUtils.copyProperties(TaskCreateDTO, taskModel);
            List<TaskModel> tarefas = new ArrayList<>();
            tarefas.add(taskModel);
            userModel.setTarefa(tarefas);
           
            taskModel.setIdUsuario(usuarioOptional.get());
    
            
            TaskModel novaTask = taskRepository.save(taskModel);
    
        
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTask);
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar a tarefa: " + e.getMessage());
        }
    }

    @GetMapping("/tasks")
    public List<TaskDto> getTasks() {
        List<TaskModel> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskDto::new).collect(Collectors.toList());
    }

}
