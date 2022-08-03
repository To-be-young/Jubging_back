package com.example.jubging.model.response.repository;

import com.example.jubging.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenKey(Long tokenKey);
}
