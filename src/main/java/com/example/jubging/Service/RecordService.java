package com.example.jubging.Service;

import com.example.jubging.DTO.PathwayDTO;
import com.example.jubging.Exception.CEmailLoginFailedException;
import com.example.jubging.DTO.RecordDTO;
import com.example.jubging.Model.Pathway;
import com.example.jubging.Model.PloggingRecords;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.PathwayRepository;
import com.example.jubging.Repository.PloggingRepository;
import com.example.jubging.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Math.log;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final PloggingRepository ploggingRepository;

    private final UserRepository userRepository;
    private final PathwayRepository pathwayRepository;

    @Transactional
    public void record(final RecordDTO recordDTO) {

        // 플로깅 기록을 저장하려는 아이디가 유효한지 확인
        User user = userRepository.findByUserId(recordDTO.getUserId())
                .orElseThrow(CEmailLoginFailedException::new);
        // user테이블의 count와 distance 증가
        user.AddCount();
        user.AddDistance(recordDTO.getDistance());


        PloggingRecords recordData= recordDTO.toEntity();
        ploggingRepository.save(recordData);
        recordDTO.getPathway().forEach(d->
                pathwayRepository.save(d.toEntity(recordData))
                );

//        for(PathwayDTO data:recordDTO.getPathway()){
//            log.info("[경로저장]");
//            log.info("[경로데이터]: ",data);
//
//        }
    }

}
