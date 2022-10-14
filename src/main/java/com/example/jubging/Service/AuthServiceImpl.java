package com.example.jubging.Service;

import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.SignUpDTO;
import com.example.jubging.DTO.TokenDTO;
import com.example.jubging.Model.RefreshToken;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.TokenRepository;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.auth.JwtTokenProvider;
import com.example.jubging.common.Exception.CEmailLoginFailedException;
import com.example.jubging.common.Exception.EmailDuplicatedException;
import com.example.jubging.common.Exception.NicknameDuplicaitedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    // todo
    @Override
    @Transactional
    public void signup(SignUpDTO signUpDTO) {
        Optional<User> findUser = userRepository.findByUserIdOrnOrNickname(signUpDTO.getUserId(), signUpDTO.getNickname());
        if (findUser != null){
            if (findUser.get().getUserId() == signUpDTO.getUserId()){
                throw new EmailDuplicatedException();
            } else {
                throw  new NicknameDuplicaitedException();
            }
        }

        userRepository.save(signUpDTO.toEntity(passwordEncoder));
    }

    @Override
    @Transactional
    public TokenDTO loginUser(LoginDTO loginDTO) {
        // 회원 정보 존재하는지 확인
        User user = userRepository.findByUserId(loginDTO.getUserId())
                .orElseThrow(CEmailLoginFailedException::new);

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

    @Override
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByUserId(email);
    }

    @Override
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public void resetPassword() {
    }
}
