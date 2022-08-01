package com.example.jubging.service;

import com.example.jubging.advice.exception.CEmailLoginFailedException;
import com.example.jubging.model.dto.RecordDTO;
import com.example.jubging.model.entity.User;
import com.example.jubging.model.response.repository.PloggingRepository;
import com.example.jubging.model.response.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final PloggingRepository ploggingRepository;

    private final UserRepository userRepository;

    @Transactional
    public void record(final RecordDTO recordDTO) {

        // 플로깅 기록을 저장하려는 아이디가 유효한지 확인
        User  user = userRepository.findByUserId(recordDTO.getUserId())
                .orElseThrow(CEmailLoginFailedException::new);
        // user테이블의 count와 distance 증가
        user.AddCount();
        user.AddDistance(recordDTO.getDistance());

        ploggingRepository.save(recordDTO.toEntity());
    }
}
