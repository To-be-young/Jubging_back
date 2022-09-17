package com.example.jubging.Service;

import com.example.jubging.Exception.CEmailLoginFailedException;
import com.example.jubging.DTO.RecordDTO;
import com.example.jubging.Model.PloggingRecords;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.PathwayRepository;
import com.example.jubging.Repository.PloggingRepository;
import com.example.jubging.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


        //플로깅 기록저장
        PloggingRecords recordData= recordDTO.toEntity();
        ploggingRepository.save(recordData);

        //플로깅 경로저장
        recordDTO.getPathway().forEach(d->
                pathwayRepository.save(d.toEntity(recordData))
                );

    }

    // 플로깅 리스트
    // return List<PloggingRecords>
    @Transactional
    public List<PloggingRecords> getPloggingList(String userId){
        return ploggingRepository.findByUserId(userId);
    }

    // 플로깅 경로
    // return: Object[]
    @Transactional
    public List<Object[]> getPathway(Long recordId){
        List<Object[]> pathway= pathwayRepository.getPathway(recordId, Sort.by(Sort.Direction.ASC, "time"));
        return  pathway;
    }

}
