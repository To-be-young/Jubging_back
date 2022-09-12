package com.example.jubging.DTO;

import com.example.jubging.Model.Pathway;
import com.example.jubging.Model.PloggingRecords;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PathwayDTO {
    private String time;
    private double latitude;
    private double longitude;

    public Pathway toEntity(PloggingRecords ploggingRecord){
        return Pathway.builder()
                .recordId(ploggingRecord)
                .time(time)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
