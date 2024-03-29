package com.example.jubging.Repository;

import com.example.jubging.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByUserId(String userId);
    Optional<User> findById(Long id);
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    Optional<User> findByUserIdOrNickname(String userId, String nickname);
}