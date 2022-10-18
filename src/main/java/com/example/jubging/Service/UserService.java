package com.example.jubging.Service;

import com.example.jubging.DTO.EditUserInfoDTO;
import com.example.jubging.DTO.user.UserInfoDTO;
import com.example.jubging.common.Exception.CUserNotFoundException;
import com.example.jubging.DTO.user.UserPageDTO;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public User getUser(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        User user = (User) authentication.getPrincipal();

        return user;
    }

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

    @Transactional
    public void editUserInfo(HttpServletRequest request, EditUserInfoDTO editUserInfoDTO){
        Long userId = jwtTokenProvider.getUserId(request);

        String nickname = editUserInfoDTO.getNickname();
        String password = editUserInfoDTO.getPassword();

        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);

        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }


    public UserInfoDTO getUserInfo(HttpServletRequest request) {
        Long userId = jwtTokenProvider.getUserId(request);

        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);

        return UserInfoDTO.getUserInfo(user);
    }

    public String getUserNickname(HttpServletRequest request){
        User user = this.getUser(request);
        return user.getNickname();
    }

    public String getUserId(HttpServletRequest request){
        User user = this.getUser(request);
        return user.getUserId();
    }

    public void addPloggingTime(HttpServletRequest request, String activityTime){
        User user = this.getUser(request);
        String[] times = activityTime.split(":");
        int[] values = Arrays.stream(times)
                .mapToInt(Integer::parseInt)
                .toArray();
        long userActivityTime = user.getPloggingTime()+Long.valueOf(values[0]);

        user.setPloggingTime(userActivityTime);
        userRepository.save(user);
    }

    public void addDistance(HttpServletRequest request, double distance){
        User user = this.getUser(request);

        double sumDistance = user.getDistance() + distance;
        // 소수점 두자리수 이하 삭제
        sumDistance = Math.round(sumDistance * 100) / 100.0;
        log.info("sum Distance => " + sumDistance);
        user.setDistance(sumDistance);
        userRepository.save(user);
    }
}