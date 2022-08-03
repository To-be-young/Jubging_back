package com.example.jubging.model.dto;

import com.example.jubging.model.entity.PloggingRecords;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {
    private Long number;
    private String userId;
    private LocalDateTime date;
    private Double distance;
    private String activity_time;
    public PloggingRecords toEntity(){
        return PloggingRecords.builder()
                .number(number)
                .userId(userId)
                .date(LocalDateTime.now()) // 임시로 넣은 값 나중에 시작시간 종료시간 구현하면 그떄 변경
                .distance(distance)
                .activityTime(activity_time)
                .build();
    }
}