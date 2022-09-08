package com.example.jubging.Controller;

import com.example.jubging.DTO.PathwayDTO;
import com.example.jubging.DTO.PloggingLogDTO;
import com.example.jubging.DTO.RecordDTO;
import com.example.jubging.Model.PloggingRecords;
import com.example.jubging.Response.ListResult;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Repository.PloggingRepository;
import com.example.jubging.Service.RecordService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/log_list")
    public ListResult <PloggingRecords> logList(@RequestParam("userId") String userId){
        log.info("[플로깅기록 리스트]");
        return responseService.getListResult(recordService.getPloggingList(userId));
    }

}
