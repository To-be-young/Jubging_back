package com.example.jubging.Service;

import com.example.jubging.DTO.PageDTO;
import com.example.jubging.Exception.CEmailLoginFailedException;
import com.example.jubging.DTO.RecordDTO;
import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.Model.PloggingRecords;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.PathwayRepository;
import com.example.jubging.Repository.PloggingRepository;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final PloggingRepository ploggingRepository;

    private final UserRepository userRepository;
    private final PathwayRepository pathwayRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void ploggingRecord(HttpServletRequest request, final RecordDTO recordDTO) {
        Long userId = jwtTokenProvider.getUserId(request);
        // 플로깅 기록을 저장하려는 아이디가 유효한지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(CEmailLoginFailedException::new);
        // user테이블의 count와 distance 증가
        user.AddCount();
        user.AddDistance(recordDTO.getDistance());


        //플로깅 기록저장
        PloggingRecords recordData= recordDTO.toEntity(userId);
        ploggingRepository.save(recordData);

        //플로깅 경로저장
        recordDTO.getPathway().forEach(d->
                pathwayRepository.save(d.toEntity(recordData))
                );

    }

    // 플로깅 리스트
    // return List<PloggingRecords>
    @Transactional
    public PageDTO getPloggingList(HttpServletRequest request, int page){
        Long userId = jwtTokenProvider.getUserId(request);
        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "recordId"));
        Page<PloggingRecords> ploggingPage = ploggingRepository.findByUserId(userId, pageRequest);
        PageDTO pageDTO = new PageDTO(ploggingPage.getTotalPages(),ploggingPage.getTotalElements(),ploggingPage.getSize(),page,ploggingPage.getContent());
        return pageDTO;

    }

    // 플로깅 경로
    // return: Object[]
    @Transactional
    public List<Object[]> getPathway(Long recordId){
        List<Object[]> pathway= pathwayRepository.getPathway(recordId, Sort.by(Sort.Direction.ASC, "time"));
        return  pathway;
    }

}
