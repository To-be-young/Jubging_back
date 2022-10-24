package com.example.jubging.DTO.user;

import com.example.jubging.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInfoDTO {
    private String userId;
    private String nickname;

    public static UserInfoDTO getUserInfo(User user){
        return new UserInfoDTO(user.getUserId(), user.getNickname());
    }
}
