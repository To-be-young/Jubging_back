package com.example.jubging.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user")
public class User implements UserDetails {
//    자동으로 생성
// 값을 받을 필요 없음
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필수 입력
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private Gender gender;

    @ElementCollection
    @Builder.Default
    @Column(name = "user_roles")
    private List<String> roles = new ArrayList<>();

    public void updateUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return false;
    }

    // TODO
    // 토큰
    // 누적 플로깅 거리, 횟수 정보
    @Column(name = "count")
    @ColumnDefault("0")
    private int count;

    @Column(name = "distance")
    @ColumnDefault("0")
    private Double distance;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean AddDistance(double newDistance) {
        distance +=newDistance;
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean AddCount() {
        count++;
        return true;
    }
}
