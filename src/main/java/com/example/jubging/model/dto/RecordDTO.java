package com.example.jubging.model.dto;

import com.example.jubging.model.entity.PloggingRecords;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {
    private Long number;
    private String userId;
    private String date;
    private Double distance;
    private String activity_time;
    public PloggingRecords toEntity(){
        return PloggingRecords.builder()
                .number(number)
                .userId(userId)
                .date(date)
                .distance(distance)
                .activityTime(activity_time)
                .build();
    }
}