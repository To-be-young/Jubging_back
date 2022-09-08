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
    
//    이름 나이 성별 삭제

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;

    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
    private String phoneNumber;

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .userId(userId)
                .phoneNumber(phoneNumber)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}