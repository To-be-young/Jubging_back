package com.example.jubging.DTO;

import com.example.jubging.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collections;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nickname;

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .userId(userId)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}