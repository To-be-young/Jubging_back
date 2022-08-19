package com.example.jubging.Repository;

import com.example.jubging.Model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenKey(Long tokenKey);
}
