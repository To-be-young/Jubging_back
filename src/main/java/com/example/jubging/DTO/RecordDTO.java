package com.example.jubging.DTO;

import com.example.jubging.Model.PloggingRecords;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {
    private String userId;
    private LocalDateTime date;
    private double distance;
    private String activity_time;
    private String pace;
    private List<PathwayDTO> pathway;
    public PloggingRecords toEntity(Long userId){
        return PloggingRecords.builder()
                .userId(userId)
                .date(LocalDateTime.now()) // 임시로 넣은 값 나중에 시작시간 종료시간 구현하면 그떄 변경
                .distance(distance)
                .activityTime(activity_time)
                .pace(pace)
                .build();
    }

}