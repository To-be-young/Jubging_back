package com.example.jubging.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PloggingLogDTO {
    private Long recordId;
    private String userId;
    private LocalDateTime date;
    private double distance;
    private String activityTime;

}
