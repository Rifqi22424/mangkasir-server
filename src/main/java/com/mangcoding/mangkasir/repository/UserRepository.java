package com.mangcoding.mangkasir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mangcoding.mangkasir.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByGmail(String gmail);
}
