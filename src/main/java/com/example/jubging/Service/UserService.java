package com.example.jubging.Service;

import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.DTO.UserPageDTO;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public boolean checkEmailDuplicate(String userId){
        return userRepository.existsByUserId(userId);
    }
    @Transactional
    public boolean checkNicknameDuplicate(String nickname){
        return userRepository.existsByNickname(nickname);
    }
    // TODO
    // 보내줄 정보 -> total distance, count
    @Transactional
    public UserPageDTO getUserPage(HttpServletRequest request) throws UsernameNotFoundException {
        Long userId = jwtTokenProvider.getUserId(request);


        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);

        return UserPageDTO.getUserPage(user);
    }


}
