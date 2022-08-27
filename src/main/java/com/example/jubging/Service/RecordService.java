package com.example.jubging.Service;

import com.example.jubging.Exception.CEmailLoginFailedException;
import com.example.jubging.DTO.RecordDTO;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.PloggingRepository;
import com.example.jubging.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final PloggingRepository ploggingRepository;

    private final UserRepository userRepository;

    @Transactional
    public void record(final RecordDTO recordDTO) {

        // 플로깅 기록을 저장하려는 아이디가 유효한지 확인
        User user = userRepository.findByUserId(recordDTO.getUserId())
                .orElseThrow(CEmailLoginFailedException::new);
        // user테이블의 count와 distance 증가
        user.AddCount();
        user.AddDistance(recordDTO.getDistance());

        ploggingRepository.save(recordDTO.toEntity());
    }
}
