package com.example.jubging.model.dto;

import com.example.jubging.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

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
