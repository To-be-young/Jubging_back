package com.example.jubging.DTO;

import com.example.jubging.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInfoDTO {
    private String userId;
    private String nickname;
    private String phoneNumber;

    public static UserInfoDTO getUserInfo(User user){
        return new UserInfoDTO(user.getUserId(), user.getNickname(), user.getPhoneNumber());
    }
}
