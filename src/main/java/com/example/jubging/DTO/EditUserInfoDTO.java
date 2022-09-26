package com.example.jubging.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserInfoDTO {
    private String nickname;
    private String phoneNumber;
    private String password;
}
