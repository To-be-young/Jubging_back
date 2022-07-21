package com.example.jubging.model.dto;

import com.example.jubging.model.entity.Gender;
import com.example.jubging.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String name;

    private String userId;

    private String password;

    private String phoneNumber;

    private int age;

    private Gender gender;


    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .id(id)
                .name(name)
                .password(passwordEncoder.encode(password))
                .userId(userId)
                .phoneNumber(phoneNumber)
                .age(age)
                .gender(gender)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

}