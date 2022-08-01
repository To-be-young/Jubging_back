package com.example.jubging.service;

import com.example.jubging.model.dto.RecordDTO;
import com.example.jubging.model.dto.UserDTO;
import com.example.jubging.model.response.repository.PloggingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final PloggingRepository ploggingRepository;

    @Transactional
    public void record(final RecordDTO recordDTO) {
        ploggingRepository.save(recordDTO.toEntity());
    }
}
