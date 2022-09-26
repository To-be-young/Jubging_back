package com.example.jubging.DTO;

import com.example.jubging.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {
    private String userId;
    private String nickname;
    private String phoneNumber;
    private String password;

    public static UserInfoDTO getUserInfo(User user){
        return new UserInfoDTO(user.getUserId(), user.getNickname(), user.getPhoneNumber(), user.getPassword());
    }
}
