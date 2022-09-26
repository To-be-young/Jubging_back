package com.example.jubging.Service;

import com.example.jubging.DTO.EditUserInfoDTO;
import com.example.jubging.DTO.UserInfoDTO;
import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.DTO.UserPageDTO;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean checkEmailDuplicate(String userId){
        return userRepository.existsByUserId(userId);
    }
    @Transactional
    public boolean checkNicknameDuplicate(String nickname){
        return userRepository.existsByNickname(nickname);
    }
    @Transactional
    public UserPageDTO getUserPage(HttpServletRequest request) throws UsernameNotFoundException {
        Long userId = jwtTokenProvider.getUserId(request);


        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);

        return UserPageDTO.getUserPage(user);
    }

    @Transactional
    public void editUserInfo(HttpServletRequest request, EditUserInfoDTO editUserInfoDTO){
        Long userId = jwtTokenProvider.getUserId(request);

        String nickname = editUserInfoDTO.getNickname();
        String phoneNumber = editUserInfoDTO.getPhoneNumber();
        String password = editUserInfoDTO.getPassword();

        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);

        user.setNickname(nickname);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }


    public UserInfoDTO getUserInfo(HttpServletRequest request) {
        Long userId = jwtTokenProvider.getUserId(request);

        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);


        return UserInfoDTO.getUserInfo(user);
    }
}
