package com.example.jubging.Service;

import com.example.jubging.Exception.CEmailLoginFailedException;
import com.example.jubging.config.security.JwtTokenProvider;
import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.TokenDTO;
import com.example.jubging.DTO.SignUpDTO;
import com.example.jubging.Model.RefreshToken;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.TokenRepository;
import com.example.jubging.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    // 로그인
    @Transactional
    public TokenDTO login(LoginDTO loginDTO) {
        // 회원 정보 존재하는지 확인
        User user = userRepository.findByUserId(loginDTO.getUserId())
                .orElseThrow(CEmailLoginFailedException::new);

        log.info("user id: "+user.getUserId());
        log.info("dto user id: "+loginDTO.getUserId());

        log.info("DTO pwd: "+loginDTO.getPassword());
        log.info("user pwd: "+user.getPassword());

        // 회원 패스워드 일치 여부 확인
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new CEmailLoginFailedException();

        // AccessToken, RefreshToken 발급
        TokenDTO tokenDTO = jwtTokenProvider.createToken(user.getId(), user.getRoles());

        // Refresh Token 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(user.getId())
                .token(tokenDTO.getRefreshToken())
                .build();
        tokenRepository.save(refreshToken);

        return tokenDTO;
    }

    @Transactional
    public void signUp(final SignUpDTO singUpDTO) {
        userRepository.save(singUpDTO.toEntity(passwordEncoder));
    }
}
