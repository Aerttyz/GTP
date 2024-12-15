package com.gtp.repositories;

import org.springframework.stereotype.Repository;
import com.gtp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    // Nenhuma necessidade de sobrescrever findById
}
