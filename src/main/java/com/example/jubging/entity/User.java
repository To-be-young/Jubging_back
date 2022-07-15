package com.example.jubging.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String user_id;
    private String password;
    private String phone_number;
    private int age;
    // TODO
    // 성별
    // 토큰
    // 누적 플로깅 거리, 횟수 정보
}
