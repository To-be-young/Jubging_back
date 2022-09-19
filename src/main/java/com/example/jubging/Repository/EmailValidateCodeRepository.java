package com.example.jubging.Repository;

import com.example.jubging.Model.EmailValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailValidateCodeRepository extends JpaRepository<EmailValidationCode, Long> {
    Optional<EmailValidationCode> findByEmail(String email);

    void deleteByEmail(String email);

}
