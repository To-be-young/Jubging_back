package com.example.jubging.model.response.repository;

import com.example.jubging.model.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
}
