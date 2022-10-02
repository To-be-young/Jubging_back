package com.example.jubging.Controller;

import com.example.jubging.DTO.PageDTO;
import com.example.jubging.DTO.PathwayDTO;
import com.example.jubging.DTO.RecordDTO;
import com.example.jubging.Model.Pathway;
import com.example.jubging.Model.PloggingRecords;
import com.example.jubging.Response.ListResult;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Repository.PloggingRepository;
import com.example.jubging.Service.RecordService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/plogging")
public class PloggingController {
    private final PloggingRepository ploggingRepository;
    private final ResponseService responseService;

    private final RecordService recordService;
    // 플로깅 완료시 기록 저장
    @PostMapping("/finish")
    public SingleResult<RecordDTO> finish(HttpServletRequest request, @RequestBody RecordDTO recordDTO){
        log.info("[플로깅기록]");
        recordService.record(request, recordDTO);
        return responseService.getSingleResult(recordDTO);
    }

    @GetMapping("/log_list")
    public SingleResult <PageDTO> logList(HttpServletRequest request, @RequestParam(required = false,defaultValue = "0",value = "page")int page){
        log.info("[플로깅기록 리스트]");
        return responseService.getSingleResult(recordService.getPloggingList(request,page));
    }

    // 플로깅 상세 경로
    @GetMapping("/log_pathway")
    public ListResult<PathwayDTO> logPathway(@RequestParam("recordId") Long recordId){
        log.info("[플로깅 경로]");
        return responseService.getListResult(recordService.getPathway(recordId));
    }

}
