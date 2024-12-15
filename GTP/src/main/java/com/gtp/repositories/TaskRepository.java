package com.gtp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtp.models.TaskModel;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    Optional<TaskModel> findById(UUID idTarefa);
}
