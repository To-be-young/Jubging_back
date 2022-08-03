package com.example.jubging.controller;

import com.example.jubging.model.dto.RecordDTO;
import com.example.jubging.model.response.SingleResult;
import com.example.jubging.model.response.repository.PloggingRepository;
import com.example.jubging.service.RecordService;
import com.example.jubging.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/plogging")
public class PloggingFinishController {
    private final PloggingRepository ploggingRepository;
    private final ResponseService responseService;

    private final RecordService recordService;

    @PostMapping("/finish")
    public SingleResult<RecordDTO> finish(@RequestBody RecordDTO recordDTO){
        log.info("[플로깅기록]");
        recordService.record(recordDTO);
        return responseService.getSingleResult(recordDTO);
    }
}
