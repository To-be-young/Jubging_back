package com.example.jubging.Service;

import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.DTO.UserPageDTO;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserPageDTO getUserPage(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(CUserNotFoundException::new);

        return UserPageDTO.getUserPage(user);
    }


}
