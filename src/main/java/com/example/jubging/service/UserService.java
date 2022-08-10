package com.example.jubging.service;

import com.example.jubging.advice.exception.CEmailLoginFailedException;
import com.example.jubging.advice.exception.CUserNotFoundException;
import com.example.jubging.model.dto.UserPageDTO;
import com.example.jubging.model.entity.User;
import com.example.jubging.model.response.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
    public UserPageDTO getUserPage(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(CUserNotFoundException::new);

        return UserPageDTO.getUserPage(user);
    }


    // get
    // delete
    // modify
}
