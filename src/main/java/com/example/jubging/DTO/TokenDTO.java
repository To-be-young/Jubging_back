package com.example.jubging.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireDate;
}
