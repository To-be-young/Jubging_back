package com.example.jubging.DTO;

import com.example.jubging.Model.EmailValidationCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmailValidateDTO {
    private LocalDateTime createTime;

    public EmailValidationCode toEntity(String email, String emailCode){
        return EmailValidationCode.builder()
                .code(emailCode)
                .email(email)
                .createTime(LocalDateTime.now())
                .build();
    }

}
