package com.example.jubging.DTO;

import com.example.jubging.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPageDTO {
    private int count;
    private Double distance;

    public static UserPageDTO getUserPage(User user){
        return new UserPageDTO(user.getCount(), user.getDistance());
    }
}
