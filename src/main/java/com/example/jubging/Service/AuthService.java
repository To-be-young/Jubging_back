package com.example.jubging.Service;

import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.SignUpDTO;
import com.example.jubging.DTO.TokenDTO;

/**
 * 인증서비스
 */
public interface AuthService {
    /**
     * 회원가입
     * @param signUpDTO
     */
    void signup(SignUpDTO signUpDTO);

    /**
     * 로그인 기능
     * @param loginDTO
     * @return TokenDTO-
     *  accessToken, refreshToken제공
     */
    TokenDTO loginUser(LoginDTO loginDTO);

}
